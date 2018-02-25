package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_walgreens.R;
import com.example.project_walgreens.network.AccountDescription;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.presenter.INetPresenter;
import com.example.project_walgreens.presenter.NetPresenter;
import com.example.project_walgreens.utils.MyRecyclerAdapter;
import com.example.project_walgreens.utils.SendMessage;

/**
 * Created by hefen on 2/25/2018.
 */

public class CategoryFragment extends Fragment implements ICategoryFragment, MyRecyclerAdapter.ItemModifier{
    SendMessage sendMessage;
    View rootView;
    Context context;
    INetPresenter iNetPresenter;
    String api_key;
    String user_id;
    RecyclerView recyclerViewItems;
    MyRecyclerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_category,container,false);
        //Log.i("mylog", "on create fragment");
        initCategory();

        return rootView;
    }

    private void initCategory() {
        context = rootView.getContext();
        api_key = AccountDescription.AppApiKey;
        user_id = AccountDescription.UserID;



        iNetPresenter = new NetPresenter(this);
        iNetPresenter.getCategory(api_key, user_id);

    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }

    @Override
    public void obtainCategory() {
        recyclerViewItems = rootView.findViewById(R.id.recyclerView);
        recyclerViewItems.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerViewItems.setHasFixedSize(true);

        adapter = new MyRecyclerAdapter(ProductList.categoryItemList, context);
        adapter.setItemModifier(this);//??this context
        recyclerViewItems.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(int position) {
        if (sendMessage!=null){
            sendMessage.sendData(position);
            //Log.i("mylog", "item #" + position);
        }
    }
}
