package com.example.projectwalgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projectwalgreens.R;
import com.example.projectwalgreens.utils.MyListAdapter;
import com.example.projectwalgreens.utils.SendMessage;

/**
 * Created by hefen on 2/24/2018.
 */

public class FrontpageFragment extends Fragment{
    View rootView;//used to init listview
    ListView listView;
    Context context;
    MyListAdapter adapter;
    SendMessage sendMessage;


    static public String[] items = {"Prescriptions & Health", "Shop Products", "Photo", "Weekly Ad & Coupons"};
    static public int[] items_pics = {R.drawable.ic_prescription, R.drawable.ic_shop,
                                        R.drawable.ic_photo, R.drawable.ic_coupons};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_frontpage,container,false);
        //Log.i("mylog", "on create fragment");
        initList();

        return rootView;
    }

    private void initList() {
        context = rootView.getContext();


        listView = (ListView) rootView.findViewById(R.id.listView);
        if (listView == null) {
            Log.i("mylog", "null listView");
        } else {
            Log.i("mylog", "listView");
        }
        adapter = new MyListAdapter(context, items, items_pics);
        if (adapter == null) {
            Log.i("mylog", "null adapter");
        } else {
            Log.i("mylog", "adapter");
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (sendMessage!=null){
                    sendMessage.sendData(position);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }
}
