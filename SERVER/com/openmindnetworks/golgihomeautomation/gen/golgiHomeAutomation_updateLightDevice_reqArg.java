/* IS_AUTOGENERATED_SO_ALLOW_AUTODELETE=YES */
/* The previous line is to allow auto deletion */

package com.openmindnetworks.golgihomeautomation.gen;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import com.openmindnetworks.golgi.JavaType;
import com.openmindnetworks.golgi.GolgiPayload;
import com.openmindnetworks.golgi.B64;
import com.openmindnetworks.golgi.api.GolgiException;
import com.openmindnetworks.golgi.api.GolgiAPI;

public class golgiHomeAutomation_updateLightDevice_reqArg
{

    private boolean corrupt = false;

    public boolean isCorrupt() {
        return corrupt;
    }

    public void setCorrupt() {
        corrupt = true;
    }

    private boolean deviceDescIsSet = false;
    private DeviceDesc deviceDesc;

    public DeviceDesc getDeviceDesc(){
        return deviceDesc;
    }
    public boolean deviceDescIsSet(){
        return deviceDescIsSet;
    }
    public void setDeviceDesc(DeviceDesc deviceDesc){
        this.deviceDesc = deviceDesc;
        this.deviceDescIsSet = true;
    }

    public StringBuffer serialise(){
        return serialise(null);
    }

    public StringBuffer serialise(StringBuffer sb){
        return serialise("", sb);
    }

    public StringBuffer serialise(String prefix, StringBuffer sb){
        if(sb == null){
            sb = new StringBuffer();
        }

        if(this.deviceDescIsSet){
            deviceDesc.serialise(prefix + "" + 1 + "." , sb);
        }

        return sb;
    }

    public void deserialise(String str){
        deserialise(JavaType.createPayload(str));
    }

    private void deserialise(GolgiPayload payload){
        if(!isCorrupt() && payload.containsNestedKey("1")){
            DeviceDesc inst = new DeviceDesc(payload.getNested("1"));
            setDeviceDesc(inst);
               if(inst == null || inst.isCorrupt()){
                   setCorrupt();
               }
        }
        else {
            setCorrupt();
        }
    }

    public golgiHomeAutomation_updateLightDevice_reqArg(){
        this(true);
    }

    public golgiHomeAutomation_updateLightDevice_reqArg(boolean isSetDefault){
        super();
        deviceDescIsSet = isSetDefault;
        deviceDesc = new DeviceDesc(isSetDefault);
    }

    public golgiHomeAutomation_updateLightDevice_reqArg(GolgiPayload payload){
        this(false);
        deserialise(payload);
    }

    public golgiHomeAutomation_updateLightDevice_reqArg(String payload){
        this(JavaType.createPayload(payload));
    }

}
