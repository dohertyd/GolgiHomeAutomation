//
//  AppDelegate.m
//  GolgiHomeAutomation
//
//  Created by Derek Doherty on 07/10/2014.
//  Copyright (c) 2014 Derek Doherty. All rights reserved.
//

#import "AppDelegate.h"
#import "GolgiHomeAutomationSvcGen.h"

@interface AppDelegate ()

@end

@implementation AppDelegate

- (void)application:(UIApplication*)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData*)deviceToken
{

    NSLog(@"My token is: %@", deviceToken);
    
#ifdef DEBUG
    [Golgi setDevPushToken:deviceToken];
#else
    [Golgi setProdPushToken:deviceToken];
#endif
    
}


- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
    NSLog(@"Failed to register for remote notifications: %@", error);
}

- (void)application:(UIApplication*)application didReceiveRemoteNotification:(NSDictionary*)userInfo  fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler
{
    
    if([Golgi isGolgiPushData:userInfo]){
        NSLog(@"Golgi Received notification(1): %@", userInfo);
        
        [Golgi pushReceived:userInfo withCompletionHandler:completionHandler];
    }
    else{
        //
        // Not a PUSH for Golgi, handle as normal in the application
        //
        NSLog(@"Application received a Remote Notification NOT for Golgi");
        completionHandler(UIBackgroundFetchResultNoData);
    }
}


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    NSLog(@"applicationDidFinishLaunching()");
    if(launchOptions != nil)
    {
        // Launched from push notification
        NSDictionary *d = [launchOptions objectForKey:UIApplicationLaunchOptionsRemoteNotificationKey];
        if(d != nil){
            //
            // Ok, launched into the background, setup Golgi
            //
            
            /*
             UILocalNotification* localNotification = [[UILocalNotification alloc] init];
             localNotification.alertBody = @"Launching into BG";
             [[UIApplication sharedApplication] cancelAllLocalNotifications];
             [[UIApplication sharedApplication] scheduleLocalNotification:localNotification];
             */
            
            [Golgi enteringBackground];
            [Golgi useEphemeralConnection];
        }
    }
    

//    [[UIApplication sharedApplication] registerUserNotificationSettings:[UIUserNotificationSettings settingsForTypes:(UIUserNotificationTypeSound | UIUserNotificationTypeAlert | UIUserNotificationTypeBadge ) categories:nil]];
//    [[UIApplication sharedApplication] registerForRemoteNotifications];

    
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    
    //
    // GOLGI: Tell the framework that we are going into the background
    //
    NSLog(@"applicationDidEnterBackground()");
    [Golgi enteringBackground];
    [Golgi useEphemeralConnection];
    
    
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    //
    // GOLGI: Tell the framework that we are active again
    //
    
    NSLog(@"applicationDidBecomeActive()");
    [Golgi enteringForeground];
    [Golgi usePersistentConnection];
}

- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
