package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_walgreens.R;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.utils.MyCartAdapter;
import com.example.project_walgreens.utils.MyRecordAdapter;

/**
 * Created by hefen on 2/26/2018.
 */

public class RecordFragment extends Fragment{
    View rootView;
    Context context;
    RecyclerView recyclerViewItems;
    MyRecordAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_record,container,false);
        //Log.i("mylog", "on create fragment");
        initRecord();

        return rootView;
    }

    private void initRecord() {
        context = rootView.getContext();
        recyclerViewItems = rootView.findViewById(R.id.recyclerViewRecord);

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewItems.setHasFixedSize(true);

        adapter = new MyRecordAdapter(ProductList.item_in_record, context);
        //adapter.setItemModifier(this);//??this context
        recyclerViewItems.setAdapter(adapter);

    }
}