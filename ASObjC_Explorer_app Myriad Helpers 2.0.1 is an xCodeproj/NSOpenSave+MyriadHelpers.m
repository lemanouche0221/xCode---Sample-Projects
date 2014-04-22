//
//  NSOpenSave+MyriadHelpers.m
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
//  v2.0 is for use in either ARC or garbage collected projects

#import "NSOpenSave+MyriadHelpers.h"

@interface NSSavePanel (MyriadHelpersPrivate) 

-(NSString *)fordNSURLToHFSPath:(NSURL *)url;
-(NSURL *)fordHFSPathToNSURL:(NSString *)path;
-(NSURL *)fordAnyToURL:(id)item;
-(NSArray *)privateVerifySelector:(id)selOrArray;

@end	 

@implementation NSSavePanel (MyriadHelpers)

+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types {
	return [self makeSaveAt:location types:types name:@"Untitled" prompt:nil title:nil];
}

+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types name:(NSString *)name {
	return [self makeSaveAt:location types:types name:name prompt:nil title:nil];
}

+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types name:(NSString *)name prompt:(NSString *)prompt title:(NSString *)title {
	NSSavePanel *panel = [self savePanel];
	[panel setAllowedFileTypes:types];
	[panel setAllowsOtherFileTypes:NO];
	[panel setShowsHiddenFiles:NO];
	[panel setTreatsFilePackagesAsDirectories:NO];
	[panel setCanCreateDirectories:YES];
	[panel setNameFieldStringValue:name];
	[panel setDirectoryURL:[panel fordAnyToURL:location]];
	if (prompt) {
	[panel setMessage:prompt];
	}
	if (title) {
	[panel setTitle:title];
	}
	return panel;
}

-(NSString *)directoryHFS {
	NSURL *url = [self directoryURL];
	return [[self fordNSURLToHFSPath:url] stringByAppendingString:@":"];
}

-(void)setDirectoryHFS:(id)item {
	[self setDirectoryURL:[self fordAnyToURL:item]];
}

-(NSString *)HFSFilepath {
	NSURL *url = [self URL];
	return [self fordNSURLToHFSPath:url];	
}	

-(void)showOver:(NSWindow *)window calling:(id)selOrArray URL:(BOOL)url {
	NSArray *result = [self privateVerifySelector:selOrArray];
	if (!result) return;
		NSString *sel = [result objectAtIndex:0];
		id object = [result objectAtIndex:1];
    [self beginSheetModalForWindow:window completionHandler:^(NSInteger result) {
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
		if (result == NSFileHandlingPanelCancelButton) {
			[object performSelector:NSSelectorFromString(sel) withObject:nil];
			return;
		}			
		if ([self isMemberOfClass:[NSOpenPanel class]]) {
			if (url == YES) {
				if([(NSOpenPanel *)self allowsMultipleSelection] == NO) {
					[object performSelector:NSSelectorFromString(sel) withObject:[[(NSOpenPanel *)self URLs] objectAtIndex:0]];
					return;	
				}
				[object performSelector:NSSelectorFromString(sel) withObject:[(NSOpenPanel *)self URLs]];
				return;	
			}
			if([(NSOpenPanel *)self allowsMultipleSelection] == NO) {
				[object performSelector:NSSelectorFromString(sel) withObject:[self fordNSURLToHFSPath:[[(NSOpenPanel *)self URLs] objectAtIndex:0]]];
				return;
			}
			NSMutableArray *paths = [NSMutableArray arrayWithCapacity:1];
			for (NSURL *url in [(NSOpenPanel *)self URLs]) {
				[paths addObject:[self fordNSURLToHFSPath:url]];
			}
			[object performSelector:NSSelectorFromString(sel) withObject:paths];
			return;
		}
		if (url == YES) {
			[object performSelector:NSSelectorFromString(sel) withObject:[self URL]];
			return;
		}
		[object performSelector:NSSelectorFromString(sel) withObject:[self fordNSURLToHFSPath:[self URL]]];
#pragma clang diagnostic pop
	}
	 ];
}

-(void)showOver:(NSWindow *)window calling:(id)selOrArray {
	[self showOver:window calling:selOrArray URL:NO];
}

-(id)showModalURL {
	NSInteger result = [self runModal];
	if (result == NSFileHandlingPanelCancelButton) {
		return nil;
	}
	if ([self isMemberOfClass:[NSOpenPanel class]]) {
		if([(NSOpenPanel *)self allowsMultipleSelection] == NO) {
			return [[(NSOpenPanel *)self URLs] objectAtIndex:0];	
		} else {
			return [(NSOpenPanel *)self URLs];	
		} 
	} else {
		return [self URL];
	}
	return nil;
}

-(id)showModal {
	NSInteger result = [self runModal];
	if (result == NSFileHandlingPanelCancelButton) {
		return nil;
	}
	if ([self isMemberOfClass:[NSOpenPanel class]]) {
		if([(NSOpenPanel *)self allowsMultipleSelection] == NO) {
			return [self fordNSURLToHFSPath:[[(NSOpenPanel *)self URLs] objectAtIndex:0]];	
		} else {
			NSMutableArray *paths = [NSMutableArray arrayWithCapacity:1];
			for (NSURL *url in [(NSOpenPanel *)self URLs]) {
				[paths addObject:[self fordNSURLToHFSPath:url]];
			}
			return paths;
		}
	} else {
		return [self fordNSURLToHFSPath:[self URL]];	
	}
	return nil;
}

-(NSString *)fordNSURLToHFSPath:(NSURL *)url {
    CFURLRef cfurl = CFBridgingRetain(url);
    CFStringRef cfpathHFS = CFURLCopyFileSystemPath(cfurl, kCFURLHFSPathStyle);
    if (cfurl) CFRelease(cfurl);
    if (!cfpathHFS) { // can't call CFBridgingRelease on NULL
        return nil;
    }
	NSString *pathHFS = (NSString *)CFBridgingRelease(cfpathHFS);
	if (!pathHFS) return nil;
		// add trailing colon if a folder but not package
	NSNumber *result;
	if ([url getResourceValue:&result forKey:NSURLIsDirectoryKey error:nil] == NO) {
		return pathHFS; 
	}
	if ([result isEqualTo:[NSNumber numberWithInt:1]]) {
		if ([url getResourceValue:&result forKey:NSURLIsPackageKey error:nil] == NO) {
			return pathHFS;
		}
		if ([result isEqualTo:[NSNumber numberWithInt:0]]) {
			return [pathHFS stringByAppendingString:@":"]; // directory, not package
		} 
	}
	return pathHFS;
}

-(NSURL *)fordHFSPathToNSURL:(NSString *)path {
    CFStringRef cfpath = CFBridgingRetain(path);
    CFURLRef cfurl = CFURLCreateWithFileSystemPath (NULL, cfpath, kCFURLHFSPathStyle, (Boolean)[path hasSuffix:@":"]);
    if (cfpath) CFRelease(cfpath);
    if (!cfurl) { // can't call CFBridgingRelease on NULL
        return nil;
    }
	NSURL *url = (NSURL *)CFBridgingRelease(cfurl);
	return url;
}

-(NSURL *)fordAnyToURL:(id)item {
	if ([item isKindOfClass:[NSAppleEventDescriptor class]]) {
			// it's an AS class; 
		DescType type = [item descriptorType];
		if (type == typeAlias) {	//alias
			item = [item coerceToDescriptorType: typeFileURL];				
			NSString *string = [[NSString alloc] initWithData:[item data] encoding:NSUTF8StringEncoding];
			return [NSURL URLWithString: string];	
		}
		if (type == typeFileURL) {	//file
			NSString *string = [[NSString alloc] initWithData:[item data] encoding:NSUTF8StringEncoding];
			return [NSURL URLWithString: string];	
		}
		return nil; // fall back; none of the above
	} else {
			// it's not an AS class; 
		if ([item isKindOfClass:[NSURL class]]) {
			return item;
		}
		if ([item isKindOfClass:[NSString class]]) {
			if ([item hasPrefix:@"~"]) {
				return [NSURL fileURLWithPath:[item stringByExpandingTildeInPath]];
			}
				if ([item hasPrefix:@"/"]) {
				return [NSURL fileURLWithPath:item];
			}
			return [self fordHFSPathToNSURL:item];	
		}
		return nil; // fall back; none of the above
	}	
}

-(NSArray *)privateVerifySelector:(id)selOrArray {
		// if selOrArray is string it's the selector name and the object is app delegate, 
		// else it's an array of selector name plus object
	NSString *sel;
	id object;
	if ([selOrArray isKindOfClass:[NSString class]]) {
		sel = selOrArray;
		object = [NSApp delegate];
	} else if (([selOrArray isKindOfClass:[NSArray class]]) && ([(NSArray *)selOrArray count] == 2)) {
		sel = [(NSArray *)selOrArray objectAtIndex:0];
		object = [(NSArray *)selOrArray objectAtIndex:1];
	} else {
		NSLog(@"The calling: argument is invalid; should be selector name, or {selector name, its object} list");
		return nil;
	}
		//verify selectors
	NSUInteger byColon = [[sel componentsSeparatedByString:@":"] count];
	if ([[sel componentsSeparatedByString:@"_"] count] >= 2) {
		NSLog(@"Invalid selector '%@', do not use underscores in selector name.", sel);
		return nil;
	} else if (byColon == 1) { //
		NSLog(@"Invalid selector '%@', should be single colon at end.", sel);
		return nil;
	} else if (byColon == 2) {
		if (![sel hasSuffix:@":"]) {
			NSLog(@"Invalid selector '%@', colon should be at end.", sel);
			return nil;
		}
	}else {
		NSLog(@"Invalid selector '%@'; takes a single argument.", sel);
		return nil;
	}
	if (![object isKindOfClass:[NSObject class]]) {
		NSLog(@"The object argument '%@' is not a valid object.", object);
		return nil;
	}
	if (![object respondsToSelector:NSSelectorFromString(sel)]) {
		NSLog(@"No selector called '%@' found in object '%@'.", sel, object);
		return nil;
	}
	return [NSArray arrayWithObjects:sel, object, nil];		
}

@end

@implementation NSOpenPanel (MyriadHelpers)

+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types {
	return [self makeOpenAt:location types:types files:YES multiples:NO prompt:nil title:nil];
}

+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files {
	return [self makeOpenAt:location types:types files:files multiples:NO prompt:nil title:nil];
}

+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files multiples:(BOOL)multiples {
	return [self makeOpenAt:location types:types files:files multiples:multiples prompt:nil title:nil];
}

+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files multiples:(BOOL)multiples prompt:(NSString *)prompt title:(NSString *)title {
	NSOpenPanel *panel = [self openPanel];
	[panel setAllowedFileTypes:types];
	[panel setAllowsOtherFileTypes:NO];
	if (files == YES) {
		[panel setCanChooseFiles:YES];
		[panel setCanChooseDirectories:NO];
	} else {
		[panel setCanChooseFiles:NO];
		[panel setCanChooseDirectories:YES];	  
	}
	if (multiples == YES) {
		[panel setAllowsMultipleSelection:YES];
	} else {
		[panel setAllowsMultipleSelection:NO];		   
	}
	[panel setShowsHiddenFiles:NO];
	[panel setTreatsFilePackagesAsDirectories:NO];
	[panel setCanCreateDirectories:NO];
	[panel setDirectoryURL:[panel fordAnyToURL:location]];	
	if (prompt) {
		[panel setMessage:prompt];
	}
	if (title) {
		[panel setTitle:title];
	}
	return panel;
}

@end
