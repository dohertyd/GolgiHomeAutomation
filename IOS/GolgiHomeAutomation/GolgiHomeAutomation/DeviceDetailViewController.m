//
//  DeviceDetailViewController.m
//  GolgiHomeAutomation
//
//  Created by Derek Doherty on 10/10/2014.
//  Copyright (c) 2014 Derek Doherty. All rights reserved.
//

#import "DeviceDetailViewController.h"
#import "GolgiHomeAutomationSvcGen.h"


@interface DeviceDetailViewController ()

@property (weak, nonatomic) IBOutlet UISwitch *onOffSwitch;
@property (weak, nonatomic) IBOutlet UISlider *tempSlider;
@property (weak, nonatomic) IBOutlet UIButton *setDeviceButton;
@property (weak, nonatomic) IBOutlet UILabel *tempValueLabel;

@property (weak, nonatomic) IBOutlet UILabel *roomTempNumberLabel;
@property (weak, nonatomic) IBOutlet UILabel *roomTempTitleLabel;

@property (strong, nonatomic) GolgiTransportOptions *stdGto;

@end

@implementation DeviceDetailViewController

- (IBAction)sliderValueChanged:(UISlider *)sender
{
    self.tempValueLabel.text = [NSString  stringWithFormat:@"%i", (int)(sender.value) ];
}



- (IBAction)setDevicePressed:(id)sender
{
    // Set DeviceDesc structure contents for sending
    DeviceDesc * deviceDesc = [[DeviceDesc alloc] init];
    
    // Light Switch
    if (_device == 0)
    {
        [deviceDesc setDeviceId:0];
        [deviceDesc setDeviceName:@"Light Switch"];
        [deviceDesc setSwitchState:[self.onOffSwitch isOn]];
        
        [golgiHomeAutomationSvc sendUpdateLightDeviceUsingResultHandler:^void( golgiHomeAutomationUpdateLightDeviceExceptionBundle *errBundle)
         {
             if(errBundle == nil)
             {
                 NSLog(@"Message successfully sent!");
             }
             else
             {
                 NSLog(@"Failed to send message ");
             }
         }
         withTransportOptions:self.stdGto
         andDestination:@"HOMESERVER"
         withDeviceDesc:deviceDesc];
    }
    // Boiler device
    else if (_device == 1)
    {
        [deviceDesc setDeviceId:1];
        [deviceDesc setDeviceName:@"Boiler"];
        [deviceDesc setSwitchState:[self.onOffSwitch isOn] ];
        
        // Send parameters to HOMESERVER server via Golgi
        [golgiHomeAutomationSvc sendUpdateBoilerDeviceUsingResultHandler:^void( golgiHomeAutomationUpdateBoilerDeviceExceptionBundle *errBundle)
         {
             if(errBundle == nil)
             {
                 NSLog(@"Message successfully sent!");
             }
             else
             {
                 NSLog(@"Failed to send message ");
             }
         }
         withTransportOptions:self.stdGto
         andDestination:@"HOMESERVER"
         withDeviceDesc:deviceDesc
         andTemp:self.tempSlider.value];
    }
}


- (int)getRoomTemp
{
    __block int remoteRoomTemp;
    
    // Send request to HOMESERVER server via Golgi
    [golgiHomeAutomationSvc sendGetRoomTempUsingResultHandler:^void ( RoomTemp * roomTemp , golgiHomeAutomationGetRoomTempExceptionBundle *errBundle)
     {
         if(errBundle == nil)
         {
             NSLog(@" Get Room Temperature Message successfully sent!");
             remoteRoomTemp = (int)roomTemp.getTemp;
             //NSLog(@"Room Temperature is %d", remoteRoomTemp);
             
             self.roomTempNumberLabel.text = [NSString  stringWithFormat:@"%i", remoteRoomTemp ];
         }
         else
         {
             NSLog(@"Failed to send message ");
         }
     }
     withTransportOptions:self.stdGto
     andDestination:@"HOMESERVER" ];
    
    return remoteRoomTemp;
}


- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Setting validity period for golgi messages to 10 seconds
    self.stdGto = [[GolgiTransportOptions alloc] init];
    [self.stdGto setValidityPeriodInSeconds:20];
        [self.stdGto setHighPriority];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    
    // Light Switch
    if (_device == 0)
    {
        // No Temp slider for Light Switch
        self.tempSlider.hidden = YES;
        self.tempValueLabel.hidden = YES;
        self.roomTempNumberLabel.hidden = YES;
        self.roomTempTitleLabel.hidden = YES;
    }
    // Boiler device
    else if (_device == 1)
    {
        self.tempSlider.hidden = NO;
        self.tempValueLabel.hidden = NO;
        self.roomTempNumberLabel.hidden = NO;
        self.roomTempTitleLabel.hidden = NO;
        
        self.tempValueLabel.text = [NSString  stringWithFormat:@"%i", (int)(self.tempSlider.value) ];
        
        // Update the remote room temp
        [self getRoomTemp];
    }

}



@end
