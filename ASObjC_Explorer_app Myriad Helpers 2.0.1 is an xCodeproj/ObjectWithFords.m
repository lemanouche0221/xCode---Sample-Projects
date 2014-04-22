//
//  ObjectWithFords.m
//
//  Created by Shane Stanley. v2.0.1
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
// 
//  v2.0.1 is for use in either ARC or garbage collected projects


#import "ObjectWithFords.h"

@implementation ObjectWithFords

-(id)ford:(id)item {
	if ([item isKindOfClass:[NSAppleEventDescriptor class]]) {
			// it's an AS class; 
		switch ([item descriptorType]) {
			case typeLongDateTime:	//date
				return [self fordASDateToNSDate:item];
				break;
			case typeAlias:	//alias
				return [self fordAliasToNSURL:item];
				break;				
			case typeFileURL:	//file
				return [self fordFileToNSURL:item];
				break;				
			case typeData:	//data
				return [self fordASDataToNSData:item];
				break;
		}
		return item; // fall back; none of the above
	} else {
			// it's not an AS class; 
		if ([item isKindOfClass:[NSDate class]]) 
			return [self fordNSDateToASDate:item];
		if ([item isKindOfClass:[NSURL class]]) 
			return [self fordNSURLToHFSPath:item];
		if ([item isKindOfClass:[NSData class]]) 
			return [self fordNSDataToASData:item];
		return item; // text/lists/records will have been converted by bridge already
	}
}

-(id)fordDeep:(id)item {
	if ([item isKindOfClass:[NSArray class]]) 
		return [self fordListItems:item];
	if ([item isKindOfClass:[NSDictionary class]]) 
		return [self fordRecordValues:item];
		// fall back to ford:
	return [self ford:item];
}

-(NSMutableArray *)fordListItems:(NSArray *)list {
	NSMutableArray * result = [NSMutableArray array];
	for (id object in list) {
		[result addObject: [self fordDeep:object]];
	}
	return result;
}

-(NSMutableDictionary *)fordRecordValues:(NSDictionary *)record {
	NSArray *keys = [record allKeys];
	NSArray *values = [record allValues];
	int i, length;
	length = [keys count];
	NSMutableDictionary *result = [NSMutableDictionary dictionaryWithCapacity:length];
	for (i = 0;i < length;i++) {
		[result setObject:[self fordDeep:[values objectAtIndex:i]] 
				   forKey:[keys objectAtIndex:i]];
	}
	return result;
}

-(NSAppleEventDescriptor *)fordNSDataToASData:(NSData *)data {
    return [NSAppleEventDescriptor descriptorWithDescriptorType:typeData
														   data:data];        
}

-(NSData *)fordASDataToNSData:(NSAppleEventDescriptor *)data {
	return [data data];
}


-(NSURL *)fordAliasToNSURL:(NSAppleEventDescriptor *)alias {
	alias = [alias coerceToDescriptorType: typeFileURL];				
	NSString *string = [[NSString alloc] initWithData:[alias data] 
											 encoding:NSUTF8StringEncoding];
	return [NSURL URLWithString: string];	
}

-(NSURL *)fordFileToNSURL:(NSAppleEventDescriptor *)furl {
	NSString *string = [[NSString alloc] initWithData:[furl data] 
											 encoding:NSUTF8StringEncoding];
	return [NSURL URLWithString: string];	
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

-(NSString *)fordNSURLToHFSPathFull:(NSURL *)url {
    CFURLRef cfurl = CFBridgingRetain(url);
    CFStringRef cfpathHFS = CFURLCopyFileSystemPath(cfurl, kCFURLHFSPathStyle);
    if (cfurl) CFRelease(cfurl);
    if (!cfpathHFS) { // can't call CFBridgingRelease on NULL
        return nil;
    }
	NSString *pathHFS = (NSString *)CFBridgingRelease(cfpathHFS);
	if (!pathHFS) return nil;
		// add trailing colon if a directory
	NSNumber *result;
	if ([url getResourceValue:&result forKey:NSURLIsDirectoryKey error:nil] == NO) {
		return pathHFS; 
	}
		if ([result isEqualTo:[NSNumber numberWithInt:1]]) {
			return [pathHFS stringByAppendingString:@":"];
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
			return [self fordAliasToNSURL:item];	
		}
		if (type == typeFileURL) {	//file
			return [self fordFileToNSURL:item];	
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

-(NSAppleEventDescriptor *)fordNSDateToASDate:(NSDate *)date {
    LongDateTime ldt;
    CFDateRef cfdate = CFBridgingRetain(date);
    UCConvertCFAbsoluteTimeToLongDateTime(CFDateGetAbsoluteTime(cfdate), &ldt);
    CFRelease(cfdate);
    return [NSAppleEventDescriptor descriptorWithDescriptorType:typeLongDateTime
                                                          bytes:&ldt length:sizeof(ldt)];        
}

-(NSDate *)fordASDateToNSDate:(NSAppleEventDescriptor *)date {
    CFAbsoluteTime absTime;
    LongDateTime ldt;
	NSData *data = [date data];
    [data getBytes:&ldt length:[data length]];
    UCConvertLongDateTimeToCFAbsoluteTime(ldt, &absTime);
    return [NSDate dateWithTimeIntervalSinceReferenceDate:absTime];
}

-(NSAppleEventDescriptor *)fordDateString:(NSString *)string {
	int result, i, list[6];
		// y, m and d default to 1; could set other values as defaults
	for (i = 0;i <3;i++) list[i] = 1;
	for (i = 3;i <6;i++) list[i] = 0;
	i = 0;
	NSScanner *scanner = [NSScanner scannerWithString:string];
		// scan skipping all but decimals
	[scanner setCharactersToBeSkipped:[[NSCharacterSet decimalDigitCharacterSet] invertedSet]];
	while (([scanner scanInt:&result]) && (i <6)) {
		if ((i <3) && (result == 0)) {
			list[i++] = 1; // don't want 0 for y, m and d or it goes to previous
		} else {
			list[i++] = result;
		}
	}
	CFAbsoluteTime at;
	LongDateTime ldt;
	CFCalendarRef cal = CFCalendarCopyCurrent();
	CFCalendarComposeAbsoluteTime(cal, &at, "yMdHms", list[0], list[1], list[2], list[3], list[4], list[5]);
	UCConvertCFAbsoluteTimeToLongDateTime(at, &ldt);
   CFRelease(cal);
    return [NSAppleEventDescriptor descriptorWithDescriptorType:typeLongDateTime
                                                          bytes:&ldt length:sizeof(ldt)];        
}

-(void)fordEvent {
	NSEvent *event;
	while ((event = [NSApp nextEventMatchingMask:NSAnyEventMask untilDate:nil inMode:NSEventTrackingRunLoopMode dequeue:YES])) {
		[NSApp sendEvent: event];
	}		
}

-(id)theClass:(NSString *)name {
	return NSClassFromString(name);
}

-(id)fordTrig:(NSArray *)params {
   if ([params count] != 3 
       || !([[params objectAtIndex:0] isKindOfClass:[NSString class]]) 
       || !([[params objectAtIndex:2] isKindOfClass:[NSNumber class]])
       || !([[params objectAtIndex:1] isKindOfClass:[NSNumber class]])
       ){
      return nil;
   } 
   double result;
   double val = [[params objectAtIndex:1] doubleValue];
   if ([[params objectAtIndex:2] boolValue]) {
      val = val / 360 * (2 *M_PI);
   }
   NSString *action = [[params objectAtIndex:0] lowercaseString];
   if ([action isEqualToString:@"tan"]) {
      result = tan(val);
   } else if ([action isEqualToString:@"sin"]) {
      result = sin(val);
   } else if ([action isEqualToString:@"cos"]) {
      result = cos(val);
   } else  if ([action isEqualToString:@"atan"]) {
      result = atan(val);
   } else if ([action isEqualToString:@"asin"]) {
      result = asin(val);
   } else if ([action isEqualToString:@"acos"]) {
      result = acos(val);
   } else  if ([action isEqualToString:@"tanh"]) {
      result = tanh(val);
   } else if ([action isEqualToString:@"sinh"]) {
      result = sinh(val);
   } else if ([action isEqualToString:@"cosh"]) {
      result = cosh(val);
   } else  if ([action isEqualToString:@"atanh"]) {
      result = atanh(val);
   } else if ([action isEqualToString:@"asinh"]) {
      result = asinh(val);
   } else if ([action isEqualToString:@"acosh"]) {
      result = acosh(val);
   } else if ([action isEqualToString:@"log"]) {
      result = log(val);
   } else if ([action isEqualToString:@"log10"]) {
      result = log10(val);
   }
   if (isnan(result)) {
      return nil;
   }
   return [NSNumber numberWithDouble:result];
}

@end
