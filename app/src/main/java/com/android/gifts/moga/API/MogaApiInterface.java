package com.android.gifts.moga.API;

import com.android.gifts.moga.API.model.LoginRegisterResponse;
import com.android.gifts.moga.API.model.NewsResponse;
import com.android.gifts.moga.API.model.Result;
import com.android.gifts.moga.API.model.SchedulesResponse;
import com.android.gifts.moga.API.model.UserVm;

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
    Call<LoginRegisterResponse> signUpUser(@Body HashMap<String, Object> body);

    @GET("/service/api/News/GetNews")
    Call<NewsResponse> getNews(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize,
                               @Query("yearId") int yearId, @Query("typeId") int typeID);

    @GET("/service/api/News/GetSchedules")
    Call<SchedulesResponse> getSchedules(@Query("yearId") int yearId, @Query("typeId") int typeID);

    @POST("/service/api/Users/UpdateUser")
    @Headers({"Content-Type:application/json"})
    Call<Result> updateUser(@Body UserVm user);

    @POST("/service/api/Users/ForgetPassword")
    @Headers({"Content-Type:application/json"})
    Call<Result> sendNewPassword(@Query("email") String email);

    @POST("/service/api/Notification/RegisterDevice")
    @Headers({"Content-Type:application/json"})
    Call<Result> registerDevice(@Body HashMap<String, Object> body);

    @POST("/service/api/Notification/DeleteDevice")
    @Headers({"Content-Type:application/json"})
    Call<Result> deleteDevice(@Body HashMap<String, Object> body);

    @POST("/service/api/General/ContactUs")
    Call<Result> contactUs(@Query("userId") int userId, @Query("message") String message);
}