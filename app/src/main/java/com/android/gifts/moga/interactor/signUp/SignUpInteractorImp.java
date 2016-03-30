package com.android.gifts.moga.interactor.signUp;

import android.content.Context;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.LoginRegisterResponse;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.signUp.OnFinishedRegisterListener;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpInteractorImp implements SignUpInteractor {
    ComplexPreferences complexPreferences;

    public SignUpInteractorImp(Context context) {
        ObjectPreference preference  = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
    }

    @Override
    public void register(String name, String mail, String mobile, String password, int year, final OnFinishedRegisterListener listener) {
        MogaApiInterface service = RestClient.getClient();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Name", name);
        hashMap.put("Email", mail);
        hashMap.put("Mobile", mobile);
        hashMap.put("Password", password);
        hashMap.put("YearId", year);

        Call<LoginRegisterResponse> call = service.registerUser(hashMap);

        call.enqueue(new Callback<LoginRegisterResponse>() {
            @Override
            public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {

                if (response.isSuccess()) {
                    LoginRegisterResponse registerResponse = response.body();

                    if (registerResponse.getResult().getCode() == 0) {
                        // Save user to prefs
                        complexPreferences.putObject(Constants.USER_PREF_KEY, registerResponse.getUserVm());
                        complexPreferences.commmit();

                        listener.onSuccess();
                    } else if (registerResponse.getResult().getCode() == 2) {
                        // Email in use
                        listener.onMailInUse();
                    } else if (registerResponse.getResult().getCode() == 6) {
                        // Mobile in use
                        listener.onMobileInUse();
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
}
