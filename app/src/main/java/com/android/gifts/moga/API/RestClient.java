package com.android.gifts.moga.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static MogaApiInterface apiInterface;
    private static String baseURL = "http://mogaoperation-001-site1.btempurl.com";

    public static MogaApiInterface getClient() {
        if (apiInterface == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = client.create(MogaApiInterface.class);
        }

        return apiInterface;
    }
}