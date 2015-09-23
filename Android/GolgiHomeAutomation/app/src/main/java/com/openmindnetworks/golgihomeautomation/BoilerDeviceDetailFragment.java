package com.openmindnetworks.golgihomeautomation;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.openmindnetworks.golgi.api.GolgiException;
import com.openmindnetworks.golgihomeautomation.gen.DeviceDesc;
import com.openmindnetworks.golgihomeautomation.gen.golgiHomeAutomationService;

import com.openmindnetworks.golgihomeautomation.gen.golgiHomeAutomationService.*;


public class BoilerDeviceDetailFragment extends Fragment
{

    private Switch boilerswitch;
    private int thermostatValue = 0;
    private SeekBar seekBar;
    private TextView textView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_boiler_detail, container, false);
    }


    public void onResume()
    {
        super.onResume();

        boilerswitch = (Switch)getView().findViewById(R.id.boilerswitch);
        boilerswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    Log.i("OMN", "I be checkeded");
                } else {
                    Log.i("OMN", "I be uncheckeded");
                }
            }
        });


        seekBar = (SeekBar)getView().findViewById(R.id.boiler_temp_slider);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                thermostatValue = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView = (TextView)getView().findViewById(R.id.boiler_temp_text);
                textView.setText("Boiler Thermostat is: " + progress);
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });


        final Button button = (Button) getView().findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    Log.i("OMN", "I be clicked");
                    // Need to send device settings to home Server

                    DeviceDesc myMsg2u = new DeviceDesc();
                    myMsg2u.setDeviceId(1);
                    myMsg2u.setSwitchState(boilerswitch.isChecked() ? 1:0);
                    myMsg2u.setDeviceName("Boiler Switch");

                    updateBoilerDevice.sendTo
                            (new updateBoilerDevice.ResultReceiver()
                             {
                                @Override
                                 public void failure(GolgiException ex) {
                                    Log.i("OMN", "Send Failed: " + ex.getErrText());
                                 }

                                 @Override
                                  public void success() {
                                    Log.i("OMN", "Received a Response");

                                   }
                             },
                            "HOMESERVER",
                            myMsg2u,
                            thermostatValue
                    );

                }
            });



    }
}