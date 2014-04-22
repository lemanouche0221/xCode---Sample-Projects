//
//  main.m
//  HelloWorld
//
//  Created by Ben Waldie on 7/30/12.
//  Copyright (c) 2012 Ben Waldie. All rights reserved.
//

#import <Cocoa/Cocoa.h>

#import <AppleScriptObjC/AppleScriptObjC.h>

int main(int argc, char *argv[])
{
	[[NSBundle mainBundle] loadAppleScriptObjectiveCScripts];
	return NSApplicationMain(argc, (const char **)argv);
}
