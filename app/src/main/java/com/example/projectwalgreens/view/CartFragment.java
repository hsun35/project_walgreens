package com.example.projectwalgreens.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.braintreepayments.api.dropin.DropInRequest;
import com.example.projectwalgreens.R;
import com.example.projectwalgreens.model.ProductInfo;
import com.example.projectwalgreens.network.ProductList;
import com.example.projectwalgreens.presenter.INetPresenter;
import com.example.projectwalgreens.presenter.NetPresenter;
import com.example.projectwalgreens.utils.MyCartAdapter;
import com.example.projectwalgreens.utils.SendMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by hefen on 2/26/2018.
 */

public class CartFragment extends android.support.v4.app.Fragment implements MyCartAdapter.ItemModifier, ICartFragment{
    SendMessage sendMessage;
    View rootView;
    Context context;

    RecyclerView recyclerViewItems;
    MyCartAdapter adapter;

    Button removeButton;
    Button checkoutButton;

    CheckBox selectAllCheck;

    String clientToken;

    INetPresenter iNetPresenter;//Net

    List<ProductInfo> ordered_products;

    TextView money;
    int amount;

    final int BRAIN_TREE_REQUEST_CODE = 4949;
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
        money = rootView.findViewById(R.id.textViewMoney);
        amount = 0;

        context = rootView.getContext();
        recyclerViewItems = rootView.findViewById(R.id.recyclerViewCart);

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));

        recyclerViewItems.setHasFixedSize(true);

        iNetPresenter = new NetPresenter(this);

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
                amount = 0;//reset amount after removed items
                money.setText("$ 0");
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBrainTreeSubmit(view);//release braintree
                /*getClientTokenFromAppServer();
                if (clientToken == null || clientToken.length() == 0) {
                    Log.i("mylog", "no token");
                } else {
                    Log.i("mylog", "client token: " + clientToken);
                }*/
                int len = ProductList.item_in_cart.size();
                int i;
                for (i = len - 1; i>= 0; i--) {
                    if (ProductList.item_in_cart.get(i).isSelection()) {
                        break;
                    }
                }
                if (i >= 0) {//if select any item, then start the payment process
                    sendMessage.getClientTokenFromAppServer();
                } else {
                    sendMessage.showMessage("No item selected.");
                }

                /*int len = ProductList.item_in_cart.size();

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

                        //parse ordered_products to net presenter
                        if (ordered_products == null) {
                            ordered_products = new ArrayList<>();
                        }
                        ordered_products.add(new ProductInfo(name, img, price, Id, "1"));
                        ////

                        if (ProductList.item_obtained.containsKey(Id)) {//put one more into a map
                            ProductList.item_obtained.put(Id, ProductList.item_obtained.get(Id) + 1);
                        } else {
                            ProductList.item_obtained.put(Id, 1);//put into list
                            ProductList.item_in_record.add(new ProductInfo(name, img, price, Id, "1"));
                        }

                        //ProductList.item_in_track.add(new ProductInfo(name, img, price, Id, "1"));fake track

                        
                        ProductList.item_in_cart.remove(i);
                    }
                }
                if (ProductList.item_in_cart.size() < len) {

                    adapter.notifyDataSetChanged();
                    Log.i("mylog", "put orders on net");
                    ordered_products.get(ordered_products.size() - 1).setStatus("0");//set the end sign, so the net knows when to return
                    iNetPresenter.getOrder(ordered_products);//parse the ordered products list to net presenter to put order
                }*/
            }
        });

        selectAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectAllCheck.isChecked()) {
                    amount = 0;
                    for (ProductInfo item: ProductList.item_in_cart) {
                        item.setSelection(true);
                        amount += Integer.parseInt(item.getPrize());
                    }
                    money.setText("$ " + String.valueOf(amount));
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
        int price = Integer.parseInt(ProductList.item_in_cart.get(position).getPrize());
        boolean select = !(ProductList.item_in_cart.get(position).isSelection() );
        ProductList.item_in_cart.get(position).setSelection(select);
        Log.i("mylog", "item #" + position + " " + select);
        if (select) {
            amount += price;
        } else {
            amount -= price;
        }
        money.setText("$ " + String.valueOf(amount) );
    }

    void onBrainTreeSubmit(View view) {
        DropInRequest dropInRequest = new DropInRequest().clientToken(clientToken);
        startActivityForResult(dropInRequest.getIntent(getActivity()),BRAIN_TREE_REQUEST_CODE);
    }

    public void checkOut() {
        Log.i("mylog", "check out!");
        sendMessage.showMessage("You payed $ " + String.valueOf(amount));
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

                //parse ordered_products to net presenter
                if (ordered_products == null) {
                    ordered_products = new ArrayList<>();
                }
                ordered_products.add(new ProductInfo(name, img, price, Id, "1"));
                ////

                if (ProductList.item_obtained.containsKey(Id)) {//put one more into a map
                    ProductList.item_obtained.put(Id, ProductList.item_obtained.get(Id) + 1);
                } else {
                    ProductList.item_obtained.put(Id, 1);//put into list
                    ProductList.item_in_record.add(new ProductInfo(name, img, price, Id, "1"));
                }

                //ProductList.item_in_track.add(new ProductInfo(name, img, price, Id, "1"));fake track


                ProductList.item_in_cart.remove(i);
            }
        }
        if (ProductList.item_in_cart.size() < len) {

            adapter.notifyDataSetChanged();
            Log.i("mylog", "put orders on net");
            ordered_products.get(ordered_products.size() - 1).setStatus("0");//set the end sign, so the net knows when to return
            iNetPresenter.getOrder(ordered_products);//parse the ordered products list to net presenter to put order
            amount = 0;//reset amount after removed items
            money.setText("$ 0");
        }
    }
    public void cancelOrder() {
        Log.i("mylog", "cancel order!");
    }

    @Override
    //public void getOrderId(List<ProductInfo> ordered_products) {
    public void getOrderId() {
        if (ordered_products == null || ordered_products.size() == 0) {

            return;
        }
        int len = ordered_products.size();
        for (int i = 0; i < len; i++) {
            ProductInfo p = ordered_products.get(i);
            String name = p.getProductName();
            String img = p.getImage();
            String price = p.getPrize();
            String Id = p.getId();
            String order_id = p.getStatus();//keep order_id, the product id is no more in use

            Log.i("mylog", "order id: " + order_id);
            ProductList.item_in_track.add(new ProductInfo(name, img, price, order_id, "1"));//real track
        }
        this.ordered_products = null;

    }
    void postNonceToServer(String nonce) {
        String amount = "1000";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("nonce", nonce);
        params.put("amount", amount);
        client.post("http://rjtmobile.com/aamir/braintree-paypal-payment/mycheckout.php?", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.i("mylog", "purchase done" + responseBody.toString());
                        checkOut();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("mylog", "purchase failed" + responseBody.toString());
                        cancelOrder();
                    }
                    // Your implementation here
                }
        );
    }
    /*public void getClientTokenFromAppServer() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("mylog", "Failed Token");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                clientToken = responseString;
                Log.i("mylog", "client token: " + clientToken);
            }
        });
    }*/
}
