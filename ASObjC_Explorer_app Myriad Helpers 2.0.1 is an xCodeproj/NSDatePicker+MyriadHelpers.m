//
//  NSDatePicker+MyriadHelpers.m
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
//  v2.0 is for use in either ARC or garbage collected projects

#import "NSDatePicker+MyriadHelpers.h"


@implementation NSDatePicker (MyriadHelpers)

-(NSAppleEventDescriptor *)dateAS {
    NSTimeInterval interval = [[self dateValue] timeIntervalSinceReferenceDate];
    LongDateTime ldt;
    UCConvertCFAbsoluteTimeToLongDateTime((CFAbsoluteTime)interval, &ldt);
    return [NSAppleEventDescriptor descriptorWithDescriptorType:typeLongDateTime
                                                          bytes:&ldt length:sizeof(ldt)];
}

-(void)setDateAS:(NSAppleEventDescriptor *)date {
	CFAbsoluteTime absTime;
    LongDateTime ldt;
	NSData *data = [date data];
    [data getBytes:&ldt length:[data length]];
    UCConvertLongDateTimeToCFAbsoluteTime(ldt, &absTime);
    [self setDateValue:[NSDate dateWithTimeIntervalSinceReferenceDate:absTime]];	
}

@end
