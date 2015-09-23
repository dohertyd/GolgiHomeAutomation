//
// This Software (the “Software”) is supplied to you by Openmind Networks
// Limited ("Openmind") your use, installation, modification or
// redistribution of this Software constitutes acceptance of this disclaimer.
// If you do not agree with the terms of this disclaimer, please do not use,
// install, modify or redistribute this Software.
//
// TO THE MAXIMUM EXTENT PERMITTED BY LAW, THE SOFTWARE IS PROVIDED ON AN
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, EITHER
// EXPRESS OR IMPLIED INCLUDING, WITHOUT LIMITATION, ANY WARRANTIES OR
// CONDITIONS OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// Each user of the Software is solely responsible for determining the
// appropriateness of using and distributing the Software and assumes all
// risks associated with use of the Software, including but not limited to
// the risks and costs of Software errors, compliance with applicable laws,
// damage to or loss of data, programs or equipment, and unavailability or
// interruption of operations.
//
// TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW OPENMIND SHALL NOT
// HAVE ANY LIABILITY FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, WITHOUT LIMITATION,
// LOST PROFITS, LOSS OF BUSINESS, LOSS OF USE, OR LOSS OF DATA), HOWSOEVER 
// CAUSED UNDER ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
// WAY OUT OF THE USE OR DISTRIBUTION OF THE SOFTWARE, EVEN IF ADVISED OF
// THE POSSIBILITY OF SUCH DAMAGES.
//

package com.openmindnetworks.golgihomeautomation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStreamWriter;

import com.openmindnetworks.golgi.JavaType;
import com.openmindnetworks.golgi.api.GolgiAPI;
import com.openmindnetworks.golgi.api.GolgiAPIHandler;
import com.openmindnetworks.golgi.api.GolgiAPIImpl;
import com.openmindnetworks.golgi.api.GolgiAPINetworkImpl;
import com.openmindnetworks.golgi.api.GolgiException;
import com.openmindnetworks.golgi.api.GolgiTransportOptions;
import com.openmindnetworks.slingshot.ntl.NTL;
import com.openmindnetworks.slingshot.tbx.TBX;

import com.openmindnetworks.golgihomeautomation.gen.*;



public class Server  implements GolgiAPIHandler{
    private String devKey = null;
    private String appKey = null;

    private GolgiTransportOptions stdGto;
    private GolgiTransportOptions hourGto;
    private GolgiTransportOptions dayGto;



    @Override
    public void registerSuccess() {
        System.out.println("");
        System.out.println("Registered successfully with Golgi API");
    }


    @Override
    public void registerFailure() {
        System.err.println("Failed to register with Golgi API");
        System.exit(-1);
    }

    static void abort(String err) {
        System.err.println("Error: " + err);
        System.exit(-1);
    }



    private golgiHomeAutomationService.updateBoilerDevice.RequestReceiver inboundUpdateBoilerDevice = new golgiHomeAutomationService.updateBoilerDevice.RequestReceiver()
    {
        public void receiveFrom(golgiHomeAutomationService.updateBoilerDevice.ResultSender resultSender, DeviceDesc deviceDesc, int temp)
        {
            System.out.println("RECIEVED MESSAGE IN UpdateBoilerDevice Transaction =>" + "<=" 
                              + "temp = " + temp + ", Device id = " + deviceDesc.getDeviceId() + ", Device name = " + deviceDesc.getDeviceName() + ", Device switch state = " + deviceDesc.getSwitchState());

            resultSender.success();
        }
    };



    private golgiHomeAutomationService.updateLightDevice.RequestReceiver inboundUpdateLightDevice = new golgiHomeAutomationService.updateLightDevice.RequestReceiver()
    {
        public void receiveFrom(golgiHomeAutomationService.updateLightDevice.ResultSender resultSender, DeviceDesc deviceDesc)
        {
            System.out.println("RECIEVED MESSAGE IN UpdateLightDevice Transaction =>" + "<=" 
                             + "Device id = " + deviceDesc.getDeviceId() + ", Device name = " + deviceDesc.getDeviceName() + ", Device switch state = " + deviceDesc.getSwitchState());


            // Here we determine wether it is an on or off command coming from the app
            // and set the PUT request accordingly
            // The wemoserver has predefined events and listens on 4030 port

            String switchState = new String();
            if (deviceDesc.getSwitchState() == 0)
            {
               // switch on
               switchState = "off";
            }
            else if (deviceDesc.getSwitchState() == 1)
            {
               // switch off
               switchState = "on";
            }

            try
            {
               URL url = new URL("http://localhost:4030/event/" + switchState);
               HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
               httpCon.setDoOutput(true);
               httpCon.setRequestMethod("PUT");
               OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
               out.write("");
               out.close();
               httpCon.getInputStream();
             }
             catch (IOException e)
             {
                System.out.println("Error occurred during sending httpPut request to wemoServer, error is => " +  e + "<="); 
             } 
  
            resultSender.success();
        }
    };

    private golgiHomeAutomationService.getRoomTemp.RequestReceiver inboundGetRoomTemp = new golgiHomeAutomationService.getRoomTemp.RequestReceiver()
    {
        public void receiveFrom(golgiHomeAutomationService.getRoomTemp.ResultSender resultSender)
        {
            System.out.println("RECIEVED MESSAGE IN GetRoomTemp Transaction =>" + "<=");

            RoomTemp rt = new RoomTemp();
            rt.setTemp(17);

            resultSender.success(rt);
        }
    };
   

    public void looper(){
        Class<GolgiAPI> apiRef = GolgiAPI.class;
        GolgiAPINetworkImpl impl = new GolgiAPINetworkImpl();
        GolgiAPI.setAPIImpl(impl);


        // Register the following receivers to handle inbound requests
        golgiHomeAutomationService.updateLightDevice.registerReceiver(inboundUpdateLightDevice);
        golgiHomeAutomationService.updateBoilerDevice.registerReceiver(inboundUpdateBoilerDevice);
        golgiHomeAutomationService.getRoomTemp.registerReceiver(inboundGetRoomTemp);

        GolgiAPI.register(devKey,
                          appKey,
                          "HOMESERVER",
                          this);
        
        
    }
    
    public Server(String[] args){
        for(int i = 0; i < args.length; i++){
	    if(args[i].compareTo("-devKey") == 0){
		devKey = args[i+1];
		i++;
	    }
	    else if(args[i].compareTo("-appKey") == 0){
		appKey = args[i+1];
		i++;
	    }
	    else{
		System.err.println("Zoikes, unrecognised option '" + args[i] + "'");
		System.exit(-1);
	    }
        }
	if(devKey == null){
	    System.out.println("No -devKey specified");
	    System.exit(-1);
	}
	else if(appKey == null){
	    System.out.println("No -appKey specified");
	    System.exit(-1);
	}
        System.out.println("");
        System.out.println("Server Started, Calling Registration to Golgi");
    }
        
    public static void main(String[] args) {
        (new Server(args)).looper();
    }
}
