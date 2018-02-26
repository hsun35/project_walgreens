package com.example.project_walgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.project_walgreens.R;
import com.example.project_walgreens.network.AccountDescription;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.presenter.INetPresenter;
import com.example.project_walgreens.presenter.NetPresenter;
import com.example.project_walgreens.utils.MyProductAdapter;
import com.example.project_walgreens.utils.MySubCategoryAdapter;
import com.example.project_walgreens.utils.SendMessage;

/**
 * Created by hefen on 2/25/2018.
 */

public class ProductFragment extends Fragment implements IProductFragment, MyProductAdapter.ItemModifier {
    SendMessage sendMessage;
    View rootView;
    Context context;
    INetPresenter iNetPresenter;
    String Id;
    String api_key;
    String user_id;

    RecyclerView recyclerViewItems;
    MyProductAdapter adapter;

    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_product,container,false);
        //Log.i("mylog", "on create fragment");
        initProduct();

        return rootView;
    }

    void initProduct() {
        context = rootView.getContext();
        api_key = AccountDescription.AppApiKey;
        user_id = AccountDescription.UserID;



        iNetPresenter = new NetPresenter(this);
        iNetPresenter.getProduct(Id, api_key, user_id);
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }
    public void setId(String Id){
        this.Id = Id;
    }

    @Override
    public void obtainPruduct() {
        recyclerViewItems = rootView.findViewById(R.id.recyclerView3);//!!!
        recyclerViewItems.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerViewItems.setHasFixedSize(true);

        adapter = new MyProductAdapter(ProductList.productItemList, context);
        adapter.setItemModifier(this);//??this context
        recyclerViewItems.setAdapter(adapter);
    }

    @Override
    public void emptyShelf() {
        imageView = rootView.findViewById(R.id.imageViewEmpty);
        imageView.setImageResource(R.drawable.empty_box);
    }

    @Override
    public void showProductMessage(String msg) {
        sendMessage.showMessage(msg);
    }

    @Override
    public void onItemSelected(int position) {
        if (sendMessage!=null){
            sendMessage.sendData(position);
            //Log.i("mylog", "item #" + position);
        }
    }
}
