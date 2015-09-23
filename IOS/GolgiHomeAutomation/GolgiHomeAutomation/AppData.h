//
//  AppData.h
//  GolgiHomeAutomation
//
//  Created by Derek Doherty on 18/10/2014.
//  Copyright (c) 2014 Derek Doherty. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AppData : NSObject
{
    NSString *instanceId;
    NSData *pushId;
}

+ (NSString *)getInstanceId;
+ (void)setInstanceId:(NSString *)instanceId;
+ (NSData *)getPushId;
+ (void)setPushId:(NSData *)pushId;


@end
