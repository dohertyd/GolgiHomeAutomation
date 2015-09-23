//
//  ViewController.m
//  GolgiHomeAutomation
//
//  Created by Derek Doherty on 07/10/2014.
//  Copyright (c) 2014 Derek Doherty. All rights reserved.
//

#import "ViewController.h"
#import "GOLGI_KEYS.h"
#import "DeviceDetailViewController.h"
#import "GolgiHomeAutomationSvcGen.h"

@interface ViewController ()  <UITableViewDelegate, UITableViewDataSource>

@end

@implementation ViewController 
- (void)viewDidLoad
{
    [super viewDidLoad];
    self.navigationItem.rightBarButtonItem = self.editButtonItem;
    // Do any additional setup after loading the view, typically from a nib.
    

    // Registering with new API
    [Golgi registerWithDevId:GOLGI_DEV_KEY
                       appId:GOLGI_APP_KEY
                      instId:@"IOS_GHA_CLIENT"
            andResultHandler:^void(NSString *errText)
     {
         if(errText == nil)
         {
             NSLog(@"Golgi Registration: PASSED");
         }
         else
         {
             NSLog(@"Golgi Registration: FAIL => '%s'", [errText UTF8String]);
         }
     }];
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

-(NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section
{
    return @"Devices";
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 2;
}

- (UITableViewCell * )tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Create the cell
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"DeviceCell" forIndexPath:indexPath];
    
    if (indexPath.row == 0)
    {
        cell.textLabel.text = @"Light Switch";
        cell.imageView.image = [UIImage imageNamed:@"desk-lamp"];
    }
    else if (indexPath.row == 1)
    {
        cell.textLabel.text = @"Boiler";
        cell.imageView.image = [UIImage imageNamed:@"sun"];
    }
    
    return cell;
    
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"GoToDeviceSettings"])
    {
        DeviceDetailViewController * dvc = (DeviceDetailViewController *)segue.destinationViewController;
        NSIndexPath * indexPath = [self.tableView indexPathForCell:sender];
        
        // Set the property on the destination viewcontroller
        dvc.device = indexPath.row;
    }
}





@end
