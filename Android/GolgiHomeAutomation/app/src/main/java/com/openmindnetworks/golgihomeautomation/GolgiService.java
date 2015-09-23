package com.openmindnetworks.golgihomeautomation;

/**
 * Created by derekdoherty on 24/10/2014.
 */

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.openmindnetworks.golgi.api.GolgiAPI;
import com.openmindnetworks.golgi.api.GolgiAPIHandler;
import com.openmindnetworks.golgi.api.GolgiException;

import io.golgi.apiimpl.android.GolgiAbstractService;
//import com.openmindnetworks.golgihomeautomation.gen.;
import com.openmindnetworks.golgihomeautomation.gen.DeviceDesc;
import com.openmindnetworks.golgihomeautomation.gen.GolgiKeys;
import com.openmindnetworks.golgihomeautomation.gen.RoomTemp;
import com.openmindnetworks.golgihomeautomation.gen.golgiHomeAutomationService;
import com.openmindnetworks.golgihomeautomation.*;


/**
 * Created by GolgiDev on 9/05/2014.
 */
public class GolgiService extends GolgiAbstractService
{

    private static String appInstanceId = null;
    private static GolgiService theInstance;

    public static void setAppInstanceId(String appInstanceId)
    {
        GolgiService.appInstanceId = appInstanceId;
    }

/*
    private reachOut.RequestReceiver reachOutReceiver = new reachOut.RequestReceiver(){

        @Override
        public void receiveFrom(reachOut.ResultSender resultSender, Greeting myMsg2u) {
            DBG("Message Received is: " + myMsg2u.getMyMsg());
            GolgiGreetingActivity.golgiRequestReceived(myMsg2u.getMyMsg());
            resultSender.success();
        }
    };
*/

    private static void DBG(String str){
        DBG.write(str);
    }


    public void readyForRegister() {
        SharedPreferences settings = getSharedPreferences("GolgiHomeAutomationPrefs", 0);

        if (appInstanceId == null) {
            //Attempt to retrieve appInstanceId from Shared Preferences
            appInstanceId = settings.getString("appInstanceId", "");
        }

        if (appInstanceId.length() > 0 ) {
            DBG("Registering with Golgi as '" + appInstanceId + "'");

            //reachOut.registerReceiver(reachOutReceiver);

            registerGolgi(new GolgiAPIHandler() {
                              @Override
                              public void registerSuccess() {
                                  DBG("Golgi registration Success");
                                  System.out.println("Golgi registration Success");
                              }

                              @Override
                              public void registerFailure() {
                                  DBG("Golgi registration Failure");
                                  System.out.println("Golgi registration Failure");
                              }
                          },
                    GolgiKeys.DEV_KEY,
                    GolgiKeys.APP_KEY,
                    appInstanceId
            );
        }
        else{
            DBG("No App Instance Id Set, cannot register");
        }
    }


    private static SharedPreferences getSharedPrefs(Context context)
    {
        return context.getSharedPreferences("GolgiHomeAutomationPrefs", Context.MODE_PRIVATE);
    }
}
