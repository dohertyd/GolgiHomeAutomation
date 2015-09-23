/* IS_AUTOGENERATED_SO_ALLOW_AUTODELETE=YES */
/* The previous line is to allow auto deletion */

#import <Foundation/Foundation.h>
#import <Foundation/NSString.h>
#import "libGolgi.h"



@interface DeviceDesc : NSObject
{
    NSInteger deviceId;
    BOOL deviceIdIsSet;
    NSString *deviceName;
    BOOL deviceNameIsSet;
    NSInteger switchState;
    BOOL switchStateIsSet;
}

@property (readonly) BOOL deviceIdIsSet;
- (NSInteger)getDeviceId;
- (void)setDeviceId:(NSInteger )deviceId;
@property (readonly) BOOL deviceNameIsSet;
- (NSString *)getDeviceName;
- (void)setDeviceName:(NSString *)deviceName;
@property (readonly) BOOL switchStateIsSet;
- (NSInteger)getSwitchState;
- (void)setSwitchState:(NSInteger )switchState;

+ (DeviceDesc *)deserialiseFromString: (NSString *)string;
+ (DeviceDesc *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface RoomTemp : NSObject
{
    NSInteger temp;
    BOOL tempIsSet;
}

@property (readonly) BOOL tempIsSet;
- (NSInteger)getTemp;
- (void)setTemp:(NSInteger )temp;

+ (RoomTemp *)deserialiseFromString: (NSString *)string;
+ (RoomTemp *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface golgiHomeAutomation_updateLightDevice_reqArg : NSObject
{
    DeviceDesc *deviceDesc;
    BOOL deviceDescIsSet;
}

@property (readonly) BOOL deviceDescIsSet;
- (DeviceDesc *)getDeviceDesc;
- (void)setDeviceDesc:(DeviceDesc *)deviceDesc;

+ (golgiHomeAutomation_updateLightDevice_reqArg *)deserialiseFromString: (NSString *)string;
+ (golgiHomeAutomation_updateLightDevice_reqArg *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface golgiHomeAutomation_updateLightDevice_rspArg : NSObject
{
    NSInteger internalSuccess_;
    BOOL internalSuccess_IsSet;
    GolgiException *golgiException;
    BOOL golgiExceptionIsSet;
}

@property (readonly) BOOL internalSuccess_IsSet;
- (NSInteger)getInternalSuccess_;
- (void)setInternalSuccess_:(NSInteger )internalSuccess_;
@property (readonly) BOOL golgiExceptionIsSet;
- (GolgiException *)getGolgiException;
- (void)setGolgiException:(GolgiException *)golgiException;

+ (golgiHomeAutomation_updateLightDevice_rspArg *)deserialiseFromString: (NSString *)string;
+ (golgiHomeAutomation_updateLightDevice_rspArg *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface golgiHomeAutomation_updateBoilerDevice_reqArg : NSObject
{
    DeviceDesc *deviceDesc;
    BOOL deviceDescIsSet;
    NSInteger temp;
    BOOL tempIsSet;
}

@property (readonly) BOOL deviceDescIsSet;
- (DeviceDesc *)getDeviceDesc;
- (void)setDeviceDesc:(DeviceDesc *)deviceDesc;
@property (readonly) BOOL tempIsSet;
- (NSInteger)getTemp;
- (void)setTemp:(NSInteger )temp;

+ (golgiHomeAutomation_updateBoilerDevice_reqArg *)deserialiseFromString: (NSString *)string;
+ (golgiHomeAutomation_updateBoilerDevice_reqArg *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface golgiHomeAutomation_updateBoilerDevice_rspArg : NSObject
{
    NSInteger internalSuccess_;
    BOOL internalSuccess_IsSet;
    GolgiException *golgiException;
    BOOL golgiExceptionIsSet;
}

@property (readonly) BOOL internalSuccess_IsSet;
- (NSInteger)getInternalSuccess_;
- (void)setInternalSuccess_:(NSInteger )internalSuccess_;
@property (readonly) BOOL golgiExceptionIsSet;
- (GolgiException *)getGolgiException;
- (void)setGolgiException:(GolgiException *)golgiException;

+ (golgiHomeAutomation_updateBoilerDevice_rspArg *)deserialiseFromString: (NSString *)string;
+ (golgiHomeAutomation_updateBoilerDevice_rspArg *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end


@interface golgiHomeAutomation_getRoomTemp_rspArg : NSObject
{
    NSInteger internalSuccess_;
    BOOL internalSuccess_IsSet;
    RoomTemp *result;
    BOOL resultIsSet;
    GolgiException *golgiException;
    BOOL golgiExceptionIsSet;
}

@property (readonly) BOOL internalSuccess_IsSet;
- (NSInteger)getInternalSuccess_;
- (void)setInternalSuccess_:(NSInteger )internalSuccess_;
@property (readonly) BOOL resultIsSet;
- (RoomTemp *)getResult;
- (void)setResult:(RoomTemp *)result;
@property (readonly) BOOL golgiExceptionIsSet;
- (GolgiException *)getGolgiException;
- (void)setGolgiException:(GolgiException *)golgiException;

+ (golgiHomeAutomation_getRoomTemp_rspArg *)deserialiseFromString: (NSString *)string;
+ (golgiHomeAutomation_getRoomTemp_rspArg *)deserialiseFromPayload: (GolgiPayload *)payload;
- (NSString *)serialiseWithPrefix:(NSString *)prefix;
- (NSString *)serialise;
- (id)initWithIsSet:(BOOL)defIsSet;
@end
//
// updateLightDevice
//
@protocol golgiHomeAutomationUpdateLightDeviceResultSender
- (NSString *)getRequestSenderId;
- (void)success;
@end
@protocol golgiHomeAutomationUpdateLightDeviceResultReceiver
- (void)success;
- (void)failureWithGolgiException:(GolgiException *)golgiException;
@end
@protocol golgiHomeAutomationUpdateLightDeviceRequestReceiver
- (void)updateLightDeviceWithResultSender:(id<golgiHomeAutomationUpdateLightDeviceResultSender>)resultSender andDeviceDesc:(DeviceDesc *)deviceDesc;
@end
@interface golgiHomeAutomationUpdateLightDeviceExceptionBundle: NSObject
{
    GolgiException * golgiException;
}
@property GolgiException * golgiException;
@end

//
// updateBoilerDevice
//
@protocol golgiHomeAutomationUpdateBoilerDeviceResultSender
- (NSString *)getRequestSenderId;
- (void)success;
@end
@protocol golgiHomeAutomationUpdateBoilerDeviceResultReceiver
- (void)success;
- (void)failureWithGolgiException:(GolgiException *)golgiException;
@end
@protocol golgiHomeAutomationUpdateBoilerDeviceRequestReceiver
- (void)updateBoilerDeviceWithResultSender:(id<golgiHomeAutomationUpdateBoilerDeviceResultSender>)resultSender andDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
@end
@interface golgiHomeAutomationUpdateBoilerDeviceExceptionBundle: NSObject
{
    GolgiException * golgiException;
}
@property GolgiException * golgiException;
@end

//
// getRoomTemp
//
@protocol golgiHomeAutomationGetRoomTempResultSender
- (NSString *)getRequestSenderId;
- (void)successWithResult:(RoomTemp *)result;
@end
@protocol golgiHomeAutomationGetRoomTempResultReceiver
- (void)successWithResult:(RoomTemp *)result;
- (void)failureWithGolgiException:(GolgiException *)golgiException;
@end
@protocol golgiHomeAutomationGetRoomTempRequestReceiver
- (void)getRoomTempWithResultSender:(id<golgiHomeAutomationGetRoomTempResultSender>)resultSender;
@end
@interface golgiHomeAutomationGetRoomTempExceptionBundle: NSObject
{
    GolgiException * golgiException;
}
@property GolgiException * golgiException;
@end

@interface golgiHomeAutomationSvc : NSObject
+ (void)sendUpdateLightDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateLightDeviceResultReceiver>)resultReceiver andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc;
+ (void)sendUpdateLightDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateLightDeviceResultReceiver>)resultReceiver withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc;
+ (void)sendUpdateLightDeviceUsingResultHandler:(void (^)(golgiHomeAutomationUpdateLightDeviceExceptionBundle *))resultHandler andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc;
+ (void)sendUpdateLightDeviceUsingResultHandler:(void (^)(golgiHomeAutomationUpdateLightDeviceExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc;
+ (void)sendUpdateLightDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateLightDeviceResultReceiver>)resultReceiver orResultHandler:(void (^)(golgiHomeAutomationUpdateLightDeviceExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc;
+ (void)registerUpdateLightDeviceRequestReceiver:(id<golgiHomeAutomationUpdateLightDeviceRequestReceiver>)requestReceiver;
+ (void)registerUpdateLightDeviceRequestHandler:(void (^)(id<golgiHomeAutomationUpdateLightDeviceResultSender> resultSender, DeviceDesc * deviceDesc))requestHandler;

+ (void)sendUpdateBoilerDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateBoilerDeviceResultReceiver>)resultReceiver andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
+ (void)sendUpdateBoilerDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateBoilerDeviceResultReceiver>)resultReceiver withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
+ (void)sendUpdateBoilerDeviceUsingResultHandler:(void (^)(golgiHomeAutomationUpdateBoilerDeviceExceptionBundle *))resultHandler andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
+ (void)sendUpdateBoilerDeviceUsingResultHandler:(void (^)(golgiHomeAutomationUpdateBoilerDeviceExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
+ (void)sendUpdateBoilerDeviceUsingResultReceiver:(id<golgiHomeAutomationUpdateBoilerDeviceResultReceiver>)resultReceiver orResultHandler:(void (^)(golgiHomeAutomationUpdateBoilerDeviceExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst withDeviceDesc:(DeviceDesc *)deviceDesc andTemp:(NSInteger)temp;
+ (void)registerUpdateBoilerDeviceRequestReceiver:(id<golgiHomeAutomationUpdateBoilerDeviceRequestReceiver>)requestReceiver;
+ (void)registerUpdateBoilerDeviceRequestHandler:(void (^)(id<golgiHomeAutomationUpdateBoilerDeviceResultSender> resultSender, DeviceDesc * deviceDesc, NSInteger temp))requestHandler;

+ (void)sendGetRoomTempUsingResultReceiver:(id<golgiHomeAutomationGetRoomTempResultReceiver>)resultReceiver andDestination:(NSString *)_dst;
+ (void)sendGetRoomTempUsingResultReceiver:(id<golgiHomeAutomationGetRoomTempResultReceiver>)resultReceiver withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst;
+ (void)sendGetRoomTempUsingResultHandler:(void (^)(RoomTemp *, golgiHomeAutomationGetRoomTempExceptionBundle *))resultHandler andDestination:(NSString *)_dst;
+ (void)sendGetRoomTempUsingResultHandler:(void (^)(RoomTemp *, golgiHomeAutomationGetRoomTempExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst;
+ (void)sendGetRoomTempUsingResultReceiver:(id<golgiHomeAutomationGetRoomTempResultReceiver>)resultReceiver orResultHandler:(void (^)(RoomTemp *, golgiHomeAutomationGetRoomTempExceptionBundle *))resultHandler withTransportOptions:(GolgiTransportOptions *)options andDestination:(NSString *)_dst;
+ (void)registerGetRoomTempRequestReceiver:(id<golgiHomeAutomationGetRoomTempRequestReceiver>)requestReceiver;
+ (void)registerGetRoomTempRequestHandler:(void (^)(id<golgiHomeAutomationGetRoomTempResultSender> resultSender))requestHandler;

@end