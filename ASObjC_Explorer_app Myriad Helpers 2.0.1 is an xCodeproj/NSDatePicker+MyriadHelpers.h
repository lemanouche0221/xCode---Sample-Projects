//
//  NSDatePicker+MyriadHelpers.h
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
// This category adds two methods to the date picker: 
// * dateAS returns the date as an AppleScript date
// * setDateAS: sets the picker's date to an AppleScript date
//
//  ******************************************************************
//  v2.0 is for use in either ARC or garbage collected projects
//  ******************************************************************

#import <Cocoa/Cocoa.h>

@interface NSDatePicker (MyriadHelpers)

-(NSAppleEventDescriptor *)dateAS;
-(void)setDateAS:(NSAppleEventDescriptor *)date;

@end
