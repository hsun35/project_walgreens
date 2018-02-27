package com.example.project_walgreens.network;

import com.example.project_walgreens.model.CategoryResponse;
import com.example.project_walgreens.model.LoginResponse;
import com.example.project_walgreens.model.OrderResponse;
import com.example.project_walgreens.model.ProductResponse;
import com.example.project_walgreens.model.ResetResponse;
import com.example.project_walgreens.model.RetrieveResponse;
import com.example.project_walgreens.model.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hefen on 2/22/2018.
 */

public interface EcommerceService  {
    //Product Category List
    @GET("cust_category.php")
    public Call<CategoryResponse> getCategoryList(@Query("api_key") String api_key,
                                                  @Query("user_id") String user_id);//CategoryResponse

    /*@GET("top_rated")
    public Call<MovieResponse> getMovieDetails(@Query("api_key") String api_key);*/

    //Product Sub Category List
    @GET("cust_sub_category.php")
    public Call<SubCategoryResponse> getSubCategoryList(@Query("Id") String Id,
                                                        @Query("api_key") String api_key,
                                                        @Query("user_id") String user_id);//CategoryResponse

    //Product List
    @GET("cust_product.php")
    public Call<ProductResponse> getProductList(@Query("Id") String Id,
                                                @Query("api_key") String api_key,
                                                @Query("user_id") String user_id);//ProductResponse

    //Custom Registration, "successfully registered", "Mobile Number already exsist",
    @GET("shop_reg.php")
    public Call<Object> getRegistration(@Query("name") String name,
                                                @Query("email") String email,
                                                @Query("mobile") String mobile,
                                                @Query("password") String password);//String

    @GET("shop_login.php")
    public Call<Object> getLogin(@Query("mobile") String mobile,
                                        @Query("password") String password);//LoginResponse

    //reset password, {"msg":["old password mismatch"]} ErrResponse, {"msg":["password reset successfully",null]}
    @GET("shop_reset_pass.php")
    public Call<ResetResponse> getResetPassowrd(@Query("mobile") String mobile,
                                                @Query("password") String password,
                                                @Query("newpassword") String newpassword);//ResetResponse

    //forget password
    @GET("shop_fogot_pass.php")
    public Call<Object> getPassword(@Query("mobile") String mobile);//RetrieveResponse

    //orders
    @GET("orders.php")
    public Call<OrderResponse> getOrder(@Query("item_id") String item_id,
                                               @Query("item_names") String item_names,
                                               @Query("item_quantity") String item_quantity,
                                               @Query("final_price") String final_price,
                                               @Query("mobile") String mobile,
                                               @Query("api_key") String api_key,
                                               @Query("user_id") String user_id);//String
    //order history

    //order track
    @GET("order_track.php")
    public Call<Object> getTrack(@Query("order_id") String order_id,
                                 @Query("api_key") String api_key,
                                 @Query("user_id") String user_id);//ResetResponse
}
