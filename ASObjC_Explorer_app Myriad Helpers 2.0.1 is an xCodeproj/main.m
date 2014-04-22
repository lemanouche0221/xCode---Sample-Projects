//
//  main.m
//  Spanner
//
//  Created by Shane Stanley on 30/9/10.
//  Copyright Myriad Communications 2010. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import <AppleScriptObjC/AppleScriptObjC.h>

int main(int argc, char *argv[])
{
    [[NSBundle mainBundle] loadAppleScriptObjectiveCScripts];
    return NSApplicationMain(argc, (const char **) argv);
}
