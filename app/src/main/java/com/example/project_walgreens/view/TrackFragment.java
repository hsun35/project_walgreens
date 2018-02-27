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
import com.example.project_walgreens.utils.MyRecordAdapter;
import com.example.project_walgreens.utils.MyTrackAdapter;

/**
 * Created by hefen on 2/27/2018.
 */

public class TrackFragment extends Fragment {
    View rootView;
    Context context;
    RecyclerView recyclerViewItems;
    MyTrackAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_record,container,false);
        //Log.i("mylog", "on create fragment");
        if (ProductList.item_in_track != null) {
            initTrack();
        }

        return rootView;
    }

    private void initTrack() {
        context = rootView.getContext();
        recyclerViewItems = rootView.findViewById(R.id.recyclerViewRecord);

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewItems.setHasFixedSize(true);

        adapter = new MyTrackAdapter(ProductList.item_in_track, context);
        //adapter.setItemModifier(this);//??this context
        recyclerViewItems.setAdapter(adapter);

    }
}
