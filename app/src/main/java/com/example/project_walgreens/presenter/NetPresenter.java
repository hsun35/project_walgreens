package com.example.project_walgreens.presenter;

import android.util.Log;

import com.example.project_walgreens.model.CategoryResponse;
import com.example.project_walgreens.model.ErrResponse;
import com.example.project_walgreens.model.LoginResponse;
import com.example.project_walgreens.model.LoginResponse3;
import com.example.project_walgreens.model.LoginResponse4;
import com.example.project_walgreens.model.ProductResponse;
import com.example.project_walgreens.model.ResetResponse;
import com.example.project_walgreens.model.SubCategoryResponse;
import com.example.project_walgreens.network.AccountDescription;
import com.example.project_walgreens.network.EcommerceService;
import com.example.project_walgreens.network.ProductList;
import com.example.project_walgreens.network.RetrofitInstance;
import com.example.project_walgreens.view.IAccountFragment;
import com.example.project_walgreens.view.ICategoryFragment;
import com.example.project_walgreens.view.ILoginFragment;
import com.example.project_walgreens.view.IMainActivity;
import com.example.project_walgreens.view.IProductFragment;
import com.example.project_walgreens.view.ISubCategoryFragment;
import com.example.project_walgreens.view.MainActivity;

import java.util.Collection;
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
