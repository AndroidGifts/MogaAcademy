package com.android.gifts.moga.interactor.login;

import android.content.Context;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.LoginRegisterResponse;
import com.android.gifts.moga.API.model.Result;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.login.OnFinishedLoginListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImp implements LoginInteractor {

    private ComplexPreferences complexPreferences;
    private MogaApiInterface service;

    public LoginInteractorImp(Context context) {
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
        service = RestClient.getClient();
    }

    @Override
    public void loginUser(String login, String password, final OnFinishedLoginListener listener) {
        Call<LoginRegisterResponse> call = service.loginUser(login, password);

        call.enqueue(new Callback<LoginRegisterResponse>() {
            @Override
            public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {
                if (response.isSuccess()) {
                    LoginRegisterResponse loginRegisterResponse = response.body();
                    if (loginRegisterResponse.getResult().getCode() == 0) {
                        // Save user to prefs
                        complexPreferences.putObject(Constants.USER_PREF_KEY, loginRegisterResponse.getUserVm());
                        complexPreferences.commit();

                        listener.onSuccess();
                    } else if (loginRegisterResponse.getResult().getCode() == 3) {
                        listener.onLoginError();
                    } else {
                        listener.onFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterResponse> call, Throwable t) {
                listener.onFail();
            }
        });
    }

    @Override
    public void sendNewPassword(String email , final OnFinishedLoginListener listener) {
        Call<Result> call = service.sendNewPassword(email);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccess()) {
                    int code = (int) response.body().getCode();

                    if (code == 0) {
                        listener.onSendNewPassSuccess();
                    } else {
                        listener.onFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                listener.onFail();
            }
        });


    }

    @Override
    public boolean isUserLoggedIn() {
        return complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class) != null;
    }
}
