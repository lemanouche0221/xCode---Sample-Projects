//
//  NSPathControl+MyriadHelpers.m
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
//  v2.0 is for use in either ARC or garbage collected projects

#import "NSPathControl+MyriadHelpers.h"


@implementation NSPathControl (MyriadHelpers)

-(NSString *)HFSFilepath {
	NSURL *url = [self URL];
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

-(void)setHFSFilepath:(NSString *)path {
    CFStringRef cfpath = CFBridgingRetain(path);
    CFURLRef cfurl = CFURLCreateWithFileSystemPath (NULL, cfpath, kCFURLHFSPathStyle, (Boolean)[path hasSuffix:@":"]);
    if (cfpath) CFRelease(cfpath);
    if (!cfurl) { // can't call CFBridgingRelease on NULL
        return;
    }
	NSURL *url = (NSURL *)CFBridgingRelease(cfurl);
	[self setURL:url];
}

-(void)setPathAny:(id)item {
	if ([item isKindOfClass:[NSAppleEventDescriptor class]]) {
			// it's an AS class; 
		DescType type = [item descriptorType];
		if (type == typeAlias) {	//alias
			item = [item coerceToDescriptorType: typeFileURL];				
			NSString *string = [[NSString alloc] initWithData:[item data] encoding:NSUTF8StringEncoding];
			[self setURL:[NSURL URLWithString: string]];
			return;
		}
		if (type == typeFileURL) {	//file
			NSString *string = [[NSString alloc] initWithData:[item data] encoding:NSUTF8StringEncoding];
			[self setURL:[NSURL URLWithString: string]];	
			return;
		}
		return; // fall back; none of the above
	} else {
			// it's not an AS class; 
		if ([item isKindOfClass:[NSURL class]]) {
			[self setURL:item];
			return;
		}
		if ([item isKindOfClass:[NSString class]]) {
			if ([item hasPrefix:@"~"]) {
				[self setURL:[NSURL fileURLWithPath:[item stringByExpandingTildeInPath]]];
				return;
			}
			if ([item hasPrefix:@"/"]) {
				[self setURL:[NSURL fileURLWithPath:item]];
				return;
			}
			[self setHFSFilepath:item];	
		}
		return; // fall back; none of the above
	}	
}

@end
