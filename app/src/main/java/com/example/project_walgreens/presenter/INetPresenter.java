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
    void getRegister(String name, String email, String mobile, String password);
    void setPassword(String mobile, String old_password, String new_password);
    void getOrder(String item_id,String item_names,String item_quantity,String final_price,String mobile,String api_key,String user_id);
    void getTrack(String order_id,String api_key,String user_id);
}
