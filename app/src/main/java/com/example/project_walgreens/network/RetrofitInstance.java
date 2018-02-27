package com.example.project_walgreens.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hefen on 2/21/2018.
 */

public class RetrofitInstance {
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    //http://api.themoviedb.org/3/movie/top_rated?api_key=7f73e5911b9d2f1465e28fb5bf661fda
    public static final String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/";

    static Retrofit retrofit = null;


    public static Retrofit getRetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
