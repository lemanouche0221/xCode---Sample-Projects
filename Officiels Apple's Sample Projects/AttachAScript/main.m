//
//  main.m
//  iTunesCLT
//
//  Created by AnkhoD on 03/04/2014.
//  Copyright (c) 2014 AnkhoD. All rights reserved.
//

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        NSString *filePath = NSHomeDirectory();
        filePath = [filePath stringByAppendingPathComponent:@"myPathTest.txt"];
        
        NSFileManager *dfm = [NSFileManager defaultManager];
        
        if (![dfm fileExistsAtPath:filePath]) {
            [@"" writeToFile:filePath
                  atomically:NO
                    encoding:NSUTF8StringEncoding
                       error:nil];
        }
        NSLog(@"Hello, World!");
        
    }
    return 0;
}

