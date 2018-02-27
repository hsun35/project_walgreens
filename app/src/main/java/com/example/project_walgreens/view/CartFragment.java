package com.example.project_walgreens.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.project_walgreens.R;
import com.example.project_walgreens.model.ProductInfo;
import com.example.project_walgreens.model.ProductItem;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.utils.MyCartAdapter;
import com.example.project_walgreens.utils.MyProductAdapter;
import com.example.project_walgreens.utils.SendMessage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hefen on 2/26/2018.
 */

public class CartFragment extends android.support.v4.app.Fragment implements MyCartAdapter.ItemModifier{
    SendMessage sendMessage;
    View rootView;
    Context context;

    RecyclerView recyclerViewItems;
    MyCartAdapter adapter;

    Button removeButton;
    Button checkoutButton;

    CheckBox selectAllCheck;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart,container,false);
        //Log.i("mylog", "on create fragment");
        if (ProductList.item_in_cart != null) {
            initCart();
        }

        return rootView;
    }

    private void initCart() {
        removeButton = rootView.findViewById(R.id.buttonRemove);
        checkoutButton = rootView.findViewById(R.id.buttonCheck);
        selectAllCheck = rootView.findViewById(R.id.checkBox3);

        context = rootView.getContext();
        recyclerViewItems = rootView.findViewById(R.id.recyclerViewCart);

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewItems.setHasFixedSize(true);

        adapter = new MyCartAdapter(ProductList.item_in_cart, context);
        adapter.setItemModifier(this);//??this context
        recyclerViewItems.setAdapter(adapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = ProductList.item_in_cart.size();
                for (int i = len - 1; i>= 0; i--) {
                    String Id;
                    if (ProductList.item_in_cart.get(i).isSelection()) {
                        Id = ProductList.item_in_cart.get(i).getId();
                        ProductList.item_left.put(Id, ProductList.item_left.get(Id) + 1);
                        ProductList.item_in_cart.remove(i);
                    }
                }
                if (ProductList.item_in_cart.size() < len) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = ProductList.item_in_cart.size();
                for (int i = len - 1; i>= 0; i--) {
                    String Id = ProductList.item_in_cart.get(i).getId();
                    String img;
                    String name;
                    String price;

                    if (ProductList.item_in_cart.get(i).isSelection()) {
                        img = ProductList.item_in_cart.get(i).getImage();
                        name = ProductList.item_in_cart.get(i).getProductName();
                        price = ProductList.item_in_cart.get(i).getPrize();
                        if (ProductList.item_in_record == null) {
                            ProductList.item_in_record = new ArrayList<>();
                        }
                        if (ProductList.item_obtained == null) {
                            ProductList.item_obtained = new HashMap<>();
                        }
                        if (ProductList.item_in_track == null) {
                            ProductList.item_in_track = new ArrayList<>();
                        }

                        if (ProductList.item_obtained.containsKey(Id)) {//put one more into a map
                            ProductList.item_obtained.put(Id, ProductList.item_obtained.get(Id) + 1);
                        } else {
                            ProductList.item_obtained.put(Id, 1);//put into list
                            ProductList.item_in_record.add(new ProductInfo(name, img, price, Id, "1"));
                        }

                        ProductList.item_in_track.add(new ProductInfo(name, img, price, Id, "1"));

                        
                        ProductList.item_in_cart.remove(i);
                    }
                }
                if (ProductList.item_in_cart.size() < len) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        selectAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectAllCheck.isChecked()) {
                    for (ProductInfo item: ProductList.item_in_cart) {
                        item.setSelection(true);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setSendMessage(SendMessage sendMessage){
        this.sendMessage = sendMessage;
    }

    @Override
    public void onItemSelected(int position) {
        boolean select = !(ProductList.item_in_cart.get(position).isSelection() );
        ProductList.item_in_cart.get(position).setSelection(select);
        Log.i("mylog", "item #" + position + " " + select);
    }
}
