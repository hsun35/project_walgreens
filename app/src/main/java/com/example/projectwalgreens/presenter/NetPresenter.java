package com.example.projectwalgreens.presenter;

import android.util.Log;

import com.example.projectwalgreens.model.CategoryResponse;
import com.example.projectwalgreens.model.ErrResponse;
import com.example.projectwalgreens.model.HistoryResponse;
import com.example.projectwalgreens.model.LoginResponse;
import com.example.projectwalgreens.model.OrderResponse;
import com.example.projectwalgreens.model.ProductInfo;
import com.example.projectwalgreens.model.ProductResponse;
import com.example.projectwalgreens.model.RegisterResponse;
import com.example.projectwalgreens.model.ResetResponse;
import com.example.projectwalgreens.model.SubCategoryResponse;
import com.example.projectwalgreens.network.AccountDescription;
import com.example.projectwalgreens.network.EcommerceService;
import com.example.projectwalgreens.network.ProductList;
import com.example.projectwalgreens.network.RetrofitInstance;
import com.example.projectwalgreens.view.IAccountFragment;
import com.example.projectwalgreens.view.ICartFragment;
import com.example.projectwalgreens.view.ICategoryFragment;
import com.example.projectwalgreens.view.ILoginFragment;
import com.example.projectwalgreens.view.IProductFragment;
import com.example.projectwalgreens.view.IRecordFragment;
import com.example.projectwalgreens.view.IRegisterFragment;
import com.example.projectwalgreens.view.ISubCategoryFragment;
import com.example.projectwalgreens.view.ITrackFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hefen on 2/24/2018.
 */

public class NetPresenter implements INetPresenter{

    ILoginFragment iLoginFragment;
    ICategoryFragment iCategoryFragment;
    ISubCategoryFragment iSubCategoryFragment;
    IProductFragment iProductFragment;
    IAccountFragment iAccountFragment;
    IRegisterFragment iRegisterFragment;
    ICartFragment iCartFragment;
    ITrackFragment iTrackFragment;
    IRecordFragment iRecordFragment;

    public NetPresenter (ILoginFragment iLoginFragment) {
        this.iLoginFragment = iLoginFragment;
    }
    public NetPresenter (ICategoryFragment iCategoryFragment) {
        this.iCategoryFragment = iCategoryFragment;
    }
    public NetPresenter (ISubCategoryFragment iSubCategoryFragment) {
        this.iSubCategoryFragment = iSubCategoryFragment;
    }
    public NetPresenter (IProductFragment iProductFragment) {
        this.iProductFragment = iProductFragment;
    }
    public NetPresenter (IAccountFragment iAccountFragment) {
        this.iAccountFragment = iAccountFragment;
    }
    public NetPresenter (IRegisterFragment iRegisterFragment) {
        this.iRegisterFragment = iRegisterFragment;
    }
    public NetPresenter (IRecordFragment iRecordFragment) {
        this.iRecordFragment = iRecordFragment;
    }
    public  NetPresenter (ICartFragment iCartFragment) {
        this.iCartFragment = iCartFragment;
    }
    public  NetPresenter (ITrackFragment iTrackFragment) {
        this.iTrackFragment = iTrackFragment;
    }
    @Override
    public void login(String mobile, String password) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<Object> call = ecommerceService.getLogin(mobile, password);
        Log.i("mylog", "go login");
        Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //Log.i("mylog", "login response");
                if (response.body() instanceof LoginResponse) {
                    Log.i("mylog", "response: " + ((LoginResponse) response.body()).getUserName());
                } else if (response.body() instanceof List<?>) {
                    /*
                    Log.i("mylog", "response List " + ((List) response.body()).get(0).toString());
                    if ( ((List) response.body()).get(0) instanceof LoginResponse4) {
                        Log.i("mylog", "instanceof login response ");
                    } else {
                        Log.i("mylog", "List of unknown type");
                    }*/
                    String resp = ((List) response.body()).get(0).toString();
                    resp = resp.replaceAll("[\\[\\](){}]","");
                    //Log.i("mylog", resp);
                    String[] resp_array= resp.split(",");
                    Log.i("mylog", "resp size " + resp_array.length);
                    for (String item : resp_array) {
                        String[] key_value = item.split("=");
                        //Log.i("mylog", "resp item " + key_value[0] + " " + key_value[1]);
                        switch (key_value[0].trim()) {
                            case "msg":
                                AccountDescription.login = key_value[1].trim();
                                break;
                            case "UserMobile":
                                AccountDescription.UserMobile = key_value[1].trim();
                                break;
                            case "UserName":
                                AccountDescription.UserName = key_value[1].trim();
                                break;
                            case "AppApiKey":
                                AccountDescription.AppApiKey = key_value[1].trim();
                                break;
                            case "UserID":
                                AccountDescription.UserID = key_value[1].trim();
                                break;
                            case "UserEmail":
                                AccountDescription.UserEmail = key_value[1].trim();
                                break;
                            default:break;
                        }
                    }
                    /*Log.i("mylog", "resp account " + AccountDescription.msg + " " + AccountDescription.UserMobile + " "
                                                            + AccountDescription.UserName + " " + AccountDescription.AppApiKey + " "
                                                            + AccountDescription.UserID + " " + AccountDescription.UserEmail);*/
                }
                else {
                    Log.i("mylog", "login response: " + response.body().toString());

                }
                if (AccountDescription.login.equals("success")) {
                    Log.i("mylog", "login go to main activity");
                    iLoginFragment.login();
                } else {
                    Log.i("mylog", "login not success " + AccountDescription.login);
                    iLoginFragment.showLoginMessage("You info doesn't match any account.");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });

        //return false;
    }
    @Override
    public void getPassword(String mobile) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<Object> call = ecommerceService.getPassword(mobile);
        //Log.i("mylog", "get password");
        //Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() instanceof ErrResponse) {
                    Log.i("mylog", "response: " + ((ErrResponse) response.body()).getMsg());
                } else if (response.body() instanceof List<?>) {
                    String resp = ((List) response.body()).get(0).toString();
                    resp = resp.replaceAll("[\\[\\](){}]","");
                    String[] resp_array= resp.split(",");
                    for (String item : resp_array) {
                        String[] key_value = item.split("=");
                        //Log.i("mylog", "resp item " + key_value[0] + " " + key_value[1]);
                        if (key_value[0].trim().equals("UserPassword")) {
                            iLoginFragment.showLoginMessage("Your password is " + key_value[1]);
                        }
                    }
                } else {
                    Log.i("mylog", "login response: " + response.body().toString());
                    iLoginFragment.showLoginMessage("The phone number doesn't exist.");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void setPassword(String mobile, String old_password, String new_password) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);
        Call<ResetResponse> call = ecommerceService.getResetPassowrd(mobile, old_password, new_password);
        call.enqueue(new Callback<ResetResponse>() {
            @Override
            public void onResponse(Call<ResetResponse> call, Response<ResetResponse> response) {
                String reply = response.body().getMsg().get(0);
                Log.i("mylog", "reset password " + reply);
                iAccountFragment.showSetPasswordMessage(reply);
            }

            @Override
            public void onFailure(Call<ResetResponse> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }

    @Override
    //public void getOrder(String item_id, String item_names, String item_quantity, String final_price, String mobile, String api_key, String user_id) {
    public void getOrder(List<ProductInfo> ordered_products){
        //ProductInfo p;
        if (ordered_products == null) {
            iCartFragment.getOrderId();
            return;
        }
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);
        int len = ordered_products.size();
        for (int i = 0; i < len; i++) {
            final ProductInfo p = ordered_products.get(i);
            Call<OrderResponse> call = ecommerceService.getOrder(p.getId(), p.getProductName(), "1", p.getPrize(),
                                        AccountDescription.UserMobile, AccountDescription.AppApiKey, AccountDescription.UserID);
            Log.i("mylog", "call url: " + call.request().url().toString());
            call.enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    String end_sign = p.getStatus();
                    Log.i("mylog", "get order id");
                    String order_id = String.valueOf(response.body().getOrderConfirmed().get(0).getOrderId());
                    Log.i("mylog", "order id: " + order_id);
                    p.setStatus(order_id);
                    //if (i == len) {}
                    if (end_sign.equals("0")) {
                        iCartFragment.getOrderId();
                    }
                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {
                    Log.i("mylog", "failure: " + t.getMessage());
                }
            });
            //while (p.getStatus().equals("1"));
        }
        //iCartFragment.getOrderId(ordered_products);

    }

    @Override
    //public void getTrack(String order_id, String api_key, String user_id) {
    public void getTrack(List<ProductInfo> ordered_products){
        if (ordered_products == null) {
            iTrackFragment.getTrack();
            return;
        }
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);
        int len = ordered_products.size();
        for (int i = 0; i < len; i++) {
            final ProductInfo p = ordered_products.get(i);
            //String order_id = p.getId();

            Call<Object> call = ecommerceService.getTrack(p.getId(), AccountDescription.AppApiKey, AccountDescription.UserID);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    String end_sign = p.getStatus();

                    if (response.body() instanceof List<?>) {
                        if ( ((List) response.body()).size() == 0 ) {
                            //iTrackFragment.getTrack();
                            return;
                        }
                        String resp = ((List) response.body()).get(0).toString();
                        resp = resp.replaceAll("[\\[\\](){}]", "");
                        String[] resp_array = resp.split(",");
                        for (String item : resp_array) {
                            String[] key_value = item.split("=");
                            Log.i("mylog", "resp item " + key_value[0] + " " + key_value[1]);
                            if (key_value[0].trim().equals("OrderStatus")) {
                                p.setStatus(key_value[1].trim());
                            }
                        }

                        //if (i == len) {}
                        if (end_sign.equals("0")) {
                            iTrackFragment.getTrack();
                        }
                    } else {
                        Log.i("mylog", "login response: " + response.body().toString());
                    }

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.i("mylog", "failure: " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void getRecord(String mobile, String api_key, String user_id) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<HistoryResponse> call = ecommerceService.getHistory(mobile, api_key, user_id);
        //Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                Log.i("mylog", "get history record");
                ProductList.historyItemList = response.body().getOrderHistory();
                iRecordFragment.getRecord();
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getSubCategory(String Id, String api_key, String user_id) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<SubCategoryResponse> call = ecommerceService.getSubCategoryList(Id, api_key, user_id);
        //Log.i("mylog", "get product");
        //Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                //Log.i("mylog", "response: " + response.body().getSubCategory().get(0).getCatagoryImage());
                ProductList.subCategoryItemList = response.body().getSubCategory();
                //MovieAdapter adapter = new MovieAdapter(response.body().getResults(), MainActivity.this);
                //recyclerView.setAdapter(adapter);
                iSubCategoryFragment.obtainSubCategory();
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getProduct(String Id, String api_key, String user_id) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<ProductResponse> call = ecommerceService.getProductList(Id, api_key, user_id);
        Log.i("mylog", "get product");
        Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.body().getProduct() == null || response.body().getProduct().size() == 0) {
                    iProductFragment.showProductMessage("No products in the category.");
                    iProductFragment.emptyShelf();
                    return;
                }
                Log.i("mylog", "response: " + response.body().getProduct().get(0).getProductName());
                ProductList.productItemList = response.body().getProduct();
                //MovieAdapter adapter = new MovieAdapter(response.body().getResults(), MainActivity.this);
                //recyclerView.setAdapter(adapter);
                iProductFragment.obtainPruduct();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getRegister(String name, String email, String mobile, String password) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<Object> call = ecommerceService.getRegistration(name, email, mobile, password);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("mylog", "response " + response.body().toString());
                iRegisterFragment.showRegisterMessage(response.body().toString().trim());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });
    }


    @Override
    public void getCategory(String api_key, String user_id) {
        EcommerceService ecommerceService = RetrofitInstance.getRetrofitInstance().create(EcommerceService.class);

        Call<CategoryResponse> call = ecommerceService.getCategoryList(api_key, user_id);
        Log.i("mylog", "get category");
        Log.i("mylog", "call url: " + call.request().url().toString());
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i("mylog", "response: " + response.body().getCategory().get(0).getCatagoryName());
                ProductList.categoryItemList = response.body().getCategory();
                //MovieAdapter adapter = new MovieAdapter(response.body().getResults(), MainActivity.this);
                //recyclerView.setAdapter(adapter);
                iCategoryFragment.obtainCategory();
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.i("mylog", "failure: " + t.getMessage());
            }
        });

    }



}
