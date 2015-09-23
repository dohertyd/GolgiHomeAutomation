namespace java com.openmindnetworks.golgihomeautomation.gen

struct DeviceDesc
{
    1: required i32 deviceId,
    2: required string deviceName,
    3: required i32 switchState
}

struct RoomTemp
{
    1: required i32 temp,
}

// This struct definition will result in a Article.java file being generated in the "gen"
// directory. In this file you will find the definition of the class "Article as well as
// methods for getting and setting the field outReach

service golgiHomeAutomation
{
    void updateLightDevice(1:DeviceDesc deviceDesc),
    void updateBoilerDevice(1:DeviceDesc deviceDesc, 2:i32 temp),
    RoomTemp  getRoomTemp(),
}


// This service defintion will result in a GolgiHomeAutomation.java file being generated
// in the "gen" directory. 
// In this file you will find all the API methods you need to use
// Golgi to implement this simple service.
//
// There are four key methods of interest:
//
//
// Client Side:
// sentTo - You send your Greeting struct here
// ResultReceiver - You get your response here with the Greeting struct
//
//
// Server Side:
// ReceiveFrom - You get the Greeting struct given to you here
// ResultSender - You send back your response in a Greeting struct here
