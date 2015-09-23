package com.openmindnetworks.golgihomeautomation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;

import com.openmindnetworks.golgi.api.GolgiAPI;

public class MainActivity extends Activity implements MyListFragment.OnItemSelectedListener{

    private String appInstanceId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate Called");

        SharedPreferences settings = getSharedPreferences("GolgiHomeAutomationPrefs", 0);
        appInstanceId = settings.getString("appInstanceId", "");

        // Need this check so as not to create multiple fragments
        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MyListFragment fragment = new MyListFragment();
            fragmentTransaction.add(R.id.fragment_placeholder, fragment);

            //fragmentTransaction.addToBackStack("Back To devices");

            fragmentTransaction.commit();

            getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    int stackHeight = getFragmentManager().getBackStackEntryCount();
                    if (stackHeight > 0) { // if we have something on the stack (doesn't include the current shown fragment)
                        getActionBar().setHomeButtonEnabled(true);
                        getActionBar().setDisplayHomeAsUpEnabled(true);
                    } else {
                        getActionBar().setDisplayHomeAsUpEnabled(false);
                        getActionBar().setHomeButtonEnabled(false);
                    }
                }
            });
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences("GolgiHomeAutomationPrefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("appInstanceId", appInstanceId);
    }

    @Override
    public void onPause() {
        DBG.write("onPause()");
        // When being put into the background we no longer want to maintain the
        // persistent connection as it may have an adverse effect on the battery.
        GolgiAPI.useEphemeralConnection();
        super.onPause();
    }

    // This handles the back button in the Action Bar
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm= getFragmentManager();
                if(fm.getBackStackEntryCount()>0){
                    fm.popBackStack();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDeviceItemSelected(int rowNr)
    {
       // MyListFragment fragmentOld = (MyListFragment) getFragmentManager()
       //         .findFragmentById(R.id.fragment_placeholder);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragmentNew;
        if (rowNr == 0) {
            fragmentNew = new DeviceDetailFragment();
        }
        else if (rowNr == 1)
        {
            fragmentNew = new BoilerDeviceDetailFragment();
        }
        else{
            return;
        }


        fragmentTransaction.replace(R.id.fragment_placeholder, fragmentNew);
        fragmentTransaction.addToBackStack("Back To devices");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

       // if (fragment != null && fragment.isInLayout())
        //{
        //    fragment.setText(link);
       // }
    }



    @Override
    public void onResume() {
        DBG.write("onResume()");
        super.onResume();
        // When we are running in the foreground it is ok to keep TCP/IP
        // connections alive.
        GolgiAPI.usePersistentConnection();

        //If we have already set an appInstanceId Start the Golgi Service
        if (appInstanceId.length() > 0) {
            GolgiService.startService(this);
        }

        appInstanceId = "ANDROID_GHA_CLIENT";

        DBG.write("Registered Name = " + appInstanceId);

        //Set the appInstanceId and start (or restart) the GolgiService
        GolgiService.setAppInstanceId(appInstanceId);
        GolgiService.startService(MainActivity.this);


    }


}
