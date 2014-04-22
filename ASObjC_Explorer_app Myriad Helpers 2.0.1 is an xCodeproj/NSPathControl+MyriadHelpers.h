//
//  NSPathControl+MyriadHelpers.h
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
// This category adds three methods to the path control: 
// * HFSFilepath returns a string containing the colon-delimited path
// * setHFSFilepath: lets you pass the control a colon-delimited path
// * setPathAny: lets you pass an alias, a file, a colon-delimited path, a POSIX path, or an NSURL
//
// If the control is showing a folder (but not a bundle),
// HFSFilepath will append a colon to the end of the path.
//
//  ******************************************************************
//  v2.0 is for use in either ARC or garbage collected projects
//  ******************************************************************

#import <Cocoa/Cocoa.h>

@interface NSPathControl (MyriadHelpers)

-(NSString *)HFSFilepath;
-(void)setHFSFilepath:(NSString *)path;
-(void)setPathAny:(id)item;

@end
