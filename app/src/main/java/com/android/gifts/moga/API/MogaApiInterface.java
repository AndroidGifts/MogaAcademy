package com.android.gifts.moga.API;

import com.android.gifts.moga.API.model.LoginRegisterResponse;
import com.android.gifts.moga.API.model.NewsResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MogaApiInterface {
    @POST("/service/api/Users/LogIn")
    Call<LoginRegisterResponse> loginUser(@Query("login") String login, @Query("password") String password);

    @POST("/service/api/Users/Register")
    @Headers({"Content-Type:application/json"})
    Call<LoginRegisterResponse> registerUser(@Body HashMap<String, Object> body);

    @GET("/service/api/News/GetNews")
    Call<NewsResponse> getNews(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize,
                               @Query("yearId") int yearId);
}