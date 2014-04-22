//
//  ObjectWithFords.h
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//  v1.0.2 adds fordTrig: method
//  ******************************************************************
//  v2.0 is for use in either ARC or garbage collected projects
//  ******************************************************************
//
/* 
 Designed to be used as a superclass for AppleScript classes in AppleScriptObjC projects,
 or you can instantiate it and call its methods that way.
 Assumes garbage collection is on (the default).
 Add .h and .m files to your project, and change parent property of scripts 
 from "NSObject" to "ObjectWithFords".
 
 The main method is ford:, which is a catch-all conversion method:
 Pass an AS date and it returns an NSDate
 Pass an NSDate and it returns an AS date
 Pass AS data and it returns NSData
 Pass NSData and it returns AS data
 Pass an alias or file and it returns an NSURL
 Pass an NSURL and it returns an HFS path
 All other objects are simply returned. Because the AppleScript bridge automatically converts 
 text, numbers, lists and records to their NS equivalents, ford: can be used as a quick way 
 of doing the equivalent of NSString's stringWithString:, NSArray's arrayWithArray: and 
 NSDictionary's dictionaryWithDictionary:.
 
 The ford: method calls a bunch of other methods that you can call individually if you find
 the catch-all idea too ickey. But because they're designed to be called from ford:, they
 don't do any sanity-checking of arguments.
 
 The fordDeep: method is the same as ford:, except that in the case of lists/arrays and 
 records/dictionaries it also does ford:'s conversions on their contents, and returns a 
 mutable array/dictionary. So if, for example, you pass it a list of AppleScript dates, 
 you will get back an NSMutableArray of NSDates.
 
 The fordDateString: method takes a string and returns an AS date. Using the keyword "date"
 as a specifier doesn't work in AppleScriptObjC, so this is a kind of replacement.
 The method looks for numbers in year, month, date, hour, min, sec order. 
 You can use whatever delimiters you like. Missing hours/minutes/seconds values default to 0; 
 missing year/month/day values default to 1.
 **The command doesn't return any errors**; it makes do with what it finds. So if you pass it
 "nonsense", you'll get back a date of January 1, 0001, 12:00:00 AM. Pass it 3/3 and you'll get
 March 1, 0003 12:00:00 AM.
 
 The fordEvent method can be called repeatedly in time-consuming code to keep the UI of 
 your app responsive.
 
 The theClass: method takes the name of a class as a string, and returns the class.
 So 'theClass_("NSString")' is the same as 'current application's NSString'.
 A little less typing, and hopefully a little more readbility.
 
 The fordTrig: method takes a list of three values: A string containing the function to call
 (tan, sin, cos, atan, asin, acos, atanh, asinh, acosh, log, log10); the number; and
 a boolean true/false, indicating whether the number is in degrees (true) or radians (false).
 
 Obviously you need to avoid name clashes with both handlers and variables.
 
 There are no guarantees or warranties whatsoever. Use it at your own risk. Feedback welcome.
 */
//
// Requires garbage collection


#import <Cocoa/Cocoa.h>
#import <Foundation/Foundation.h>

@interface ObjectWithFords : NSObject {
	
}
/* The main method */
-(id)ford:(id)input;

/* This method also converts classes inside lists/arrays and records/dictionaries by calling ford: on them */
-(id)fordDeep:(id)item;

/* Goes through array calling deepFord: on all items; called by deepFord: if it's a list/array */
-(NSMutableArray *)fordListItems:(NSArray *)list;

/* Goes through dictionary calling deepFord: on all items; called by deepFord: if it's a record/dictionary */
-(NSMutableDictionary *)fordRecordValues:(NSDictionary *)record;

/* Translation methods called by ford: */
-(NSAppleEventDescriptor *)fordNSDateToASDate:(NSDate *)date;
-(NSDate *)fordASDateToNSDate:(NSAppleEventDescriptor *)date;
-(NSAppleEventDescriptor *)fordNSDataToASData:(NSData *)data;
-(NSData *)fordASDataToNSData:(NSAppleEventDescriptor *)data;
-(NSURL *)fordAliasToNSURL:(NSAppleEventDescriptor *)alias;
-(NSURL *)fordFileToNSURL:(NSAppleEventDescriptor *)furl;
-(NSURL *)fordHFSPathToNSURL:(NSString *)path;
-(NSString *)fordNSURLToHFSPath:(NSURL *)url;

/* As above, but leaves trailing colons on packages */
-(NSString *)fordNSURLToHFSPathFull:(NSURL *)url;

/* Converts alias, file, HFS path or POSIX path to NSURL (if it starts with ~ or /, it's assumed to be POSIX) */
-(NSURL *)fordAnyToURL:(id)item;

/* Method for making AS date from string */
-(NSAppleEventDescriptor *)fordDateString:(NSString *)string;

/* Call repeatedly in time-consuming code to keep UI active */
-(void)fordEvent;

/* Method for referring to a class */
-(id)theClass:(NSString *)name;

/* Method for calling trig functions plus log and log10
 Pass a list of three values:
 • A string containing the function to call: tan, sin, cos, atan, asin, acos, atanh, asinh, acosh, log, log10
 • The number
 • A boolean true/false, indicating whether the number is in degrees
 */
-(id)fordTrig:(NSArray *)params;

@end
