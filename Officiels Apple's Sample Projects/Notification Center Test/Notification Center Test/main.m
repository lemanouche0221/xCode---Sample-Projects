//
//  main.m
//  Notification Center Test
//
//  Created by Donald Southard on 8/14/12.
//  Copyright (c) 2012 MacStories. All rights reserved.
//

#import <Cocoa/Cocoa.h>

#import <AppleScriptObjC/AppleScriptObjC.h>

int main(int argc, char *argv[])
{
	[[NSBundle mainBundle] loadAppleScriptObjectiveCScripts];
	return NSApplicationMain(argc, (const char **)argv);
}
