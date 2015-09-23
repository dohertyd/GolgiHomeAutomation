package com.openmindnetworks.golgihomeautomation;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.openmindnetworks.golgi.api.GolgiException;
import com.openmindnetworks.golgihomeautomation.gen.DeviceDesc;
import com.openmindnetworks.golgihomeautomation.gen.golgiHomeAutomationService.*;


public class DeviceDetailFragment extends Fragment {

    Switch lightswitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_lightswitch_detail);

        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_lightswitch_detail, container, false);
    }

    public void onResume() {
        super.onResume();

        lightswitch = (Switch) getView().findViewById(R.id.lightswitch);
        lightswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    Log.i("OMN", "I be checkeded");
                } else {
                    Log.i("OMN", "I be uncheckeded");
                }
            }
        });


        final Button button = (Button) getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Log.i("OMN", "I be clicked");
                // Need to send device settings to home Server

                DeviceDesc myMsg2u = new DeviceDesc();
                myMsg2u.setDeviceId(0);
                myMsg2u.setSwitchState(lightswitch.isChecked() ? 1:0);
                myMsg2u.setDeviceName("Light Switch");

                updateLightDevice.sendTo(new updateLightDevice.ResultReceiver() {
                                    @Override
                                    public void failure(GolgiException ex) {
                                        Log.i("OMN","Send Failed: " + ex.getErrText());
                                    }

                                    @Override
                                    public void success() {
                                        Log.i("OMN", "Received a Response");
                                        //GolgiGreetingActivity.golgiResponseReceived();
                                    }
                                },
                        "HOMESERVER",
                        myMsg2u
                );

            }
        });



    }
}