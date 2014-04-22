//
//  main.m
//  Temperature Converter
//
//  Created by Osgood Concklin on 9/3/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import <AppleScriptObjC/AppleScriptObjC.h>

int main(int argc, char *argv[])
{
	[[NSBundle mainBundle] loadAppleScriptObjectiveCScripts];

	return NSApplicationMain(argc, (const char **) argv);
}
