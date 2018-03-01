package com.example.projectwalgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectwalgreens.R;
import com.example.projectwalgreens.network.AccountDescription;
import com.example.projectwalgreens.network.ProductList;
import com.example.projectwalgreens.presenter.INetPresenter;
import com.example.projectwalgreens.presenter.NetPresenter;
import com.example.projectwalgreens.utils.MySubCategoryAdapter;
import com.example.projectwalgreens.utils.SendMessage;

/**
 * Created by hefen on 2/25/2018.
 */

public class SubCategoryFragment extends Fragment implements ISubCategoryFragment, MySubCategoryAdapter.ItemModifier {
    SendMessage sendMessage;
    View rootView;
    Context context;
    INetPresenter iNetPresenter;
    String Id;
    String api_key;
    String user_id;

    RecyclerView recyclerViewItems;
    MySubCategoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_subcategory,container,false);
        //Log.i("mylog", "on create fragment");
        initSubCategory();

        return rootView;
    }

    void initSubCategory() {
        context = rootView.getContext();
        api_key = AccountDescription.AppApiKey;
        user_id = AccountDescription.UserID;



        iNetPresenter = new NetPresenter(this);
        iNetPresenter.getSubCategory(Id, api_key, user_id);
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }
    public void setId(String Id){
        this.Id = Id;
    }

    @Override
    public void obtainSubCategory() {
        recyclerViewItems = rootView.findViewById(R.id.recyclerView2);//!!!
        recyclerViewItems.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerViewItems.setHasFixedSize(true);

        adapter = new MySubCategoryAdapter(ProductList.subCategoryItemList, context);
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
