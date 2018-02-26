package com.example.project_walgreens.presenter;

/**
 * Created by hefen on 2/24/2018.
 */

public interface INetPresenter {
    void login(String mobile, String password);
    void getCategory(String api_key, String user_id);
    void getPassword(String mobile);
    void getSubCategory(String Id, String api_key, String user_id);
    void getProduct(String Id, String api_key, String user_id);
}
