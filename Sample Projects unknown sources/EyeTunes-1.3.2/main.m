//
//  main.m
//  iTunesCLT
//
//  Created by AnkhoD on 03/04/2014.
//  Copyright (c) 2014 AnkhoD. All rights reserved.
//#import "Appscript/Appscript.h"


#import <Foundation/Foundation.h>
#import "ITApplicationGlue.h"
#import "ITCommandGlue.h"
#import "ITConstantGlue.h"
#import "ITReferenceGlue.h"
#import "ITReferenceRendererGlue.h"


int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        [ITReference *album ] {
            return [ITReference referenceWithAppData: AS_appData
                                        aemReference: [AS_aemReference property: 'pAlb']];
        }
        
        - (ITReference *)albumArtist {
            return [ITReference referenceWithAppData: AS_appData
                                        aemReference: [AS_aemReference property: 'pAlA']];
        }
    case 'pAlb': return [self album];
    case 'pAlA': return [self albumArtist];
        
    case 'pAlb': return @"album";
    case 'pAlA': return @"albumArtist";
        
           kITAlbums = 'kSrL',
        NSLog(@"Hello, World!" @"pAlA");
        
    }
    return 0;
}

