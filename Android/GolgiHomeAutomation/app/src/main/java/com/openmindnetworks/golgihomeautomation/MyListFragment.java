package com.openmindnetworks.golgihomeautomation;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.app.ListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.ListView;
import android.widget.SimpleAdapter;



public class MyListFragment extends ListFragment {


    String [] devices = new String[]{
            "Light Switch",
            "Boiler",
            "Pair Device",
            "Add a New Device"
    };

    int [] devImages = new int[]
    {
            R.drawable.desklamp,
            R.drawable.sun,
            R.drawable.splash,
            R.drawable.splash
    };


    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<4;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt",  devices[i]);
            hm.put("flag", Integer.toString(devImages[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.fragment_list_item_detail, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id)
    {
        System.out.println("Row Clicked is " + position);

        // Send info to controlling Activity
        listener.onDeviceItemSelected(position);

        // Start the detail view activity
        //Intent i = new Intent(getActivity(), DeviceDetailView.class);
        //startActivity(i);
    }

    public interface OnItemSelectedListener
    {
        public void onDeviceItemSelected(int rowNr);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener)
        {
            listener = (OnItemSelectedListener) activity;
        } else
        {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public void setText(String link)
    {
        return;
    }

}



