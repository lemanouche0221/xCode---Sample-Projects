//
//  main.m
//  iTunesListener - Cocoa - v2.0
//
//  Created by AnkhoD on 14/04/2014.
//  Copyright (c) 2014 AnkhoD. All rights reserved.
//

#import <Cocoa/Cocoa.h>

#import <AppleScriptObjC/AppleScriptObjC.h>

int main(int argc, const char * argv[])
{
    [[NSBundle mainBundle] loadAppleScriptObjectiveCScripts];
    return NSApplicationMain(argc, argv);
}
