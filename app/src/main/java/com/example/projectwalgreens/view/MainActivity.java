package com.example.projectwalgreens.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;//rather than android.widget.Toolbar
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.example.projectwalgreens.R;
import com.example.projectwalgreens.network.AccountDescription;
import com.example.projectwalgreens.network.ProductList;
import com.example.projectwalgreens.utils.SendMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/*
* The project_walgreens implements basic functions of typical E-commerce Apps
* The functions includes account management, viewing products on the net, putting orders and checking out items, etc.
* @author Calvin Swern
* @version 0.1
* @since 2018-2-22
* */
public class MainActivity extends AppCompatActivity implements SendMessage, IMainActivity{
    Toolbar myToolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageView home;
    ImageView shopcart;
    String clientToken;

    final int BRAIN_TREE_REQUEST_CODE = 4949;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            return;
        }*/
        fragmentManager = getSupportFragmentManager();//!!

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//remove title on toolbar

        home = findViewById(R.id.imageView11);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (! (f instanceof FrontpageFragment)) {
                    addFrontpageFragment();
                }
            }
        });

        shopcart = findViewById(R.id.imageView14);
        shopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (! (f instanceof CartFragment) && AccountDescription.login.equals("success")) {
                    addCartFragment();
                }
            }
        });

        addFrontpageFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.list) {
            Toast.makeText(MainActivity.this, "my account", Toast.LENGTH_SHORT).show();
            if (AccountDescription.login.equals("success")) {
                addAccountFragment();
            } else {
                addLoginFragment();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFrontpageFragment() {
        FrontpageFragment frontpageFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        frontpageFragment = new FrontpageFragment();
        frontpageFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, frontpageFragment);//in CountriesFragment import android.support.v4.app.Fragment;
        fragmentTransaction.commit();
        //Log.i("mylog", "add list");
    }

    private void addLoginFragment() {
        LoginFragment loginFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        loginFragment = new LoginFragment();
        loginFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, loginFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addAccountFragment() {
        AccountFragment accountFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        accountFragment = new AccountFragment();
        accountFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, accountFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addCategoryFragment() {
        CategoryFragment categoryFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        categoryFragment = new CategoryFragment();
        categoryFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, categoryFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addSubCategoryFragment(int item_index) {
        SubCategoryFragment subCategoryFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        subCategoryFragment = new SubCategoryFragment();
        subCategoryFragment.setSendMessage(MainActivity.this);
        subCategoryFragment.setId(ProductList.categoryItemList.get(item_index).getId());

        fragmentTransaction.replace(R.id.fragmentContainer, subCategoryFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addProductFragment(int item_index) {
        ProductFragment productFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        productFragment = new ProductFragment();
        productFragment.setSendMessage(MainActivity.this);
        productFragment.setId(ProductList.subCategoryItemList.get(item_index).getId());

        fragmentTransaction.replace(R.id.fragmentContainer, productFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addProductDescription(int item_index) {
        ProductDescriptionFragment descriptionFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        descriptionFragment = new ProductDescriptionFragment();
        descriptionFragment.setSendMessage(MainActivity.this);
        descriptionFragment.setId(item_index);

        fragmentTransaction.replace(R.id.fragmentContainer, descriptionFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addCartFragment() {
        CartFragment cartFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        cartFragment = new CartFragment();
        cartFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, cartFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addRecordFragment() {
        RecordFragment recordFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        recordFragment = new RecordFragment();
        //recordFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, recordFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addRegisterFragment() {
        RegisterFragment registerFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        registerFragment = new RegisterFragment();
        registerFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, registerFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addTrackFragment() {
        TrackFragment trackFragment;
        fragmentTransaction=fragmentManager.beginTransaction();

        trackFragment = new TrackFragment();
        //recordFragment.setSendMessage(MainActivity.this);

        fragmentTransaction.replace(R.id.fragmentContainer, trackFragment);//
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void sendData(int item_index) {
        Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (f instanceof FrontpageFragment) {
            if (AccountDescription.login.equals("success")) {
                if (item_index == 1) {
                    Toast.makeText(MainActivity.this, "Category List", Toast.LENGTH_SHORT).show();
                    addCategoryFragment();
                } else {
                    Toast.makeText(MainActivity.this, "item #" + item_index, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please log in.", Toast.LENGTH_SHORT).show();
            }
        } else if (f instanceof CategoryFragment) {
            Toast.makeText(MainActivity.this, "Category #" + item_index, Toast.LENGTH_SHORT).show();
            addSubCategoryFragment(item_index);
        } else if (f instanceof SubCategoryFragment) {
            Toast.makeText(MainActivity.this, "Sub Category #" + item_index, Toast.LENGTH_SHORT).show();
            addProductFragment(item_index);
        } else if (f instanceof ProductFragment) {
            Toast.makeText(MainActivity.this, "Product #" + item_index, Toast.LENGTH_SHORT).show();
            addProductDescription(item_index);
        }

    }

    @Override
    public void sendCommand(String command) {
        if (command.equals("login")) {//successfully logged in
            Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
            addFrontpageFragment();
        } else if (command.equals("cart")) {
            addCartFragment();
        } else if (command.equals("record")) {
            addRecordFragment();
        } else if (command.equals("track")) {
            addTrackFragment();
        } else if (command.equals("logout")) {
            AccountDescription.clearRecord();
            ProductList.clearRecord();
            addFrontpageFragment();
        } else if (command.equals("signup")) {
            addRegisterFragment();
        } else if (command.equals("signin")) {
            addLoginFragment();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    //get token url: http://rjtmobile.com/aamir/braintree-paypal-payment/main.php?
    //SEND_PAYMENT_DETAIL_URL: http://rjtmobile.com/aamir/braintree-paypal-payment/mycheckout.php?
    @Override
    public void getClientTokenFromAppServer() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://rjtmobile.com/aamir/braintree-paypal-payment/main.php?", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("mylog", "Failed Token");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                clientToken = responseString;
                Log.i("mylog", "client token: " + clientToken);

                onBrainTreeSubmit(findViewById(R.id.fragmentContainer).getRootView());
            }
        });
    }

    void postNonceToServer(String nonce, String amount) {
        final Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("nonce", nonce);
        params.put("amount", amount);
        client.post("http://rjtmobile.com/aamir/braintree-paypal-payment/mycheckout.php?", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.i("mylog", "purchase done" + responseBody.toString());
                        if (f instanceof CartFragment) {
                            ((CartFragment) f).checkOut();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("mylog", "purchase failed" + responseBody.toString());
                        if (f instanceof CartFragment) {
                            ((CartFragment) f).cancelOrder();
                        }
                    }
                    // Your implementation here
                }
        );
    }

    void onBrainTreeSubmit(View view) {
        DropInRequest dropInRequest = new DropInRequest().clientToken(clientToken);
        startActivityForResult(dropInRequest.getIntent(this),BRAIN_TREE_REQUEST_CODE);
    }
    /*@Override
    public void login() {
        Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
        addFrontpageFragment();
    }*/

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {//succ
        Object f = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (requestCode == BRAIN_TREE_REQUEST_CODE) {
            if(RESULT_OK == resultCode) {
                DropInResult dropInResult = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                String payment_notice = dropInResult.getPaymentMethodNonce().getNonce();
                Log.i("mylog", "Sucessful notice from braintree");
                Log.i("mylog", "notice from braintree: " + payment_notice);
                //postNonceToServer(payment_notice, "1000");
                //if (f instanceof CartFragment) {
                //    ((CartFragment) f).checkOut();
                //}
                if (f instanceof CartFragment) {
                    ((CartFragment) f).postNonceToServer(payment_notice);
                }
            } else if (requestCode == Activity.RESULT_CANCELED) {
                Log.i("mylog","User cancled payment");
                if (f instanceof CartFragment) {
                    ((CartFragment) f).cancelOrder();
                }

            } else {
                Exception e = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.i("mylog", "braintree payment Error " + e.toString());
                if (f instanceof CartFragment) {
                    ((CartFragment) f).cancelOrder();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
