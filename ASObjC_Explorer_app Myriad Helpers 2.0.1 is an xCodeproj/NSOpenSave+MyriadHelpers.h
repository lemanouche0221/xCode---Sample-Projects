//
//  NSOpenSave+MyriadHelpers.h
//
//  Created by Shane Stanley. v2.0
//  <sstanley@myriad-com.com.au>.
//  'AppleScriptObjC Explored' <http://www.macosxautomation.com/applescript/apps/>
//
// This category adds methods to the open and save panels. See sample for typical usage.
//
//  ******************************************************************
//  v2.0 is for use in either ARC or garbage collected projects
//  ******************************************************************

#import <Cocoa/Cocoa.h>

@interface NSSavePanel (MyriadHelpers)

/* NSOpenPanel is a subclass of NSSavePanel, so the save panels inherits these methods. Its own methods are defined separately at the end. */

/* Creates and returns an NSSavePanel. The first argument can be an alias/file/HFS path/POSIX path/NSURL, types is a list of file extensions (missing value = any). */
+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types name:(NSString *)name prompt:(NSString *)prompt title:(NSString *)title;

/* As above, providing defaults for missing parameters. */
+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types name:(NSString *)name;
+(NSSavePanel *)makeSaveAt:(id)location types:(NSArray *)types;

/* Shows a save or open panel modally. Returns a list of HFS paths if multiple selections allowed, otherwise a single HFS path. Cancel returns missing value. */
-(id)showModal;

/* Shows a save or open panel modally. Returns a list of NSURLs if multiple selections allowed, otherwise a single NSURL. Cancel returns missing value. */
-(id)showModalURL;

/* Shows a save or open panel as a sheet over a window, calling a selector when closed. If the selector is in the app delegate, you can just pass the selector (which takes a single argument) as a string for calling:, otherwise pass it a list of the selector plus the script it's in (usually *me*).  The argument returned to the selector is a list of HFS paths if multiple selections allowed, otherwise a single HFS path. Cancel will return missing value. */
-(void)showOver:(NSWindow *)window calling:(id)selOrArray;

/* As above, but returning an NSURL or list of NSURLs if the the URL: argument is true. */
-(void)showOver:(NSWindow *)window calling:(id)selOrArray URL:(BOOL)url;

/* directoryHFS returns the starting directory as a colon-delimited path. */
-(NSString *)directoryHFS;

/* setDirectoryHFS: lets you set the starting directory using a colon-delimited path. */
-(void)setDirectoryHFS:(NSString *)path;

/* HFSFilepath returns the colon-delimited path of the chosen file or folder. If a folder or a bundle other than an .app, it will append a colon to the end. */
-(NSString *)HFSFilepath;

@end

@interface NSOpenPanel (MyriadHelpers)

/* Creates and returns an NSOpenPanel. The first argument can be an alias/file/HFS path/POSIX path/NSURL, types is a list of file extensions (missing value = any), files is a boolean (true = files, false = folders). */
+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files multiples:(BOOL)multiples prompt:(NSString *)prompt title:(NSString *)title;

/* As above with defaults. */
+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files multiples:(BOOL)multiples;

/* As above, disallowing multiple selection. */
+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types files:(BOOL)files;

/* As above, allowing files only. */
+(NSOpenPanel *)makeOpenAt:(id)location types:(NSArray *)types;

@end