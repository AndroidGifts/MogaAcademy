package com.android.gifts.moga.interactor.login;

import android.content.Context;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.LoginRegisterResponse;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.prefs.ComplexPreferences;
import com.android.gifts.moga.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.login.OnFinishedLoginListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImp implements LoginInteractor {

    private ComplexPreferences complexPreferences;

    public LoginInteractorImp(Context context) {
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
    }

    @Override
    public void loginUser(String login, String password, final OnFinishedLoginListener listener) {
        MogaApiInterface service = RestClient.getClient();

        Call<LoginRegisterResponse> call = service.loginUser(login, password);

        call.enqueue(new Callback<LoginRegisterResponse>() {
            @Override
            public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {
                LoginRegisterResponse loginRegisterResponse = response.body();
                if (response.isSuccess()) {
                    if (loginRegisterResponse.getResult().getCode() == 0) {
                        // Save user to prefs
                        complexPreferences.putObject(Constants.USER_PREF_KEY, loginRegisterResponse.getUserVm());
                        complexPreferences.commmit();

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
    public boolean isUserLoggedIn() {
        return complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class) != null;
    }
}
