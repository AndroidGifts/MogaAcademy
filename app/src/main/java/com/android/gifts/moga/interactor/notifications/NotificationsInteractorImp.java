package com.android.gifts.moga.interactor.notifications;

import android.content.Context;
import android.util.Log;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.Result;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsInteractorImp implements NotificationInteractor {
    ComplexPreferences complexPreferences;
    MogaApiInterface service;
    UserVm currentUser;

    public NotificationsInteractorImp(Context context) {
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
        currentUser = complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class);

        service = RestClient.getClient();
    }

    @Override
    public void registerDevice(String DeviceId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("UserId", currentUser.getId());
        hashMap.put("DeviceId", DeviceId);
        hashMap.put("TypeId", 1);

        Call<Result> call = service.registerDevice(hashMap);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccess()) {
                    long code = response.body().getCode();

                    if (code == 0) {
                        Log.e("MYLOG", "Device Registered Success");
                    } else {
                        Log.e("MYLOG", "Device Registered FAIL");
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("MYLOG", "Device Registered FAIL");
            }
        });
    }

    @Override
    public void deleteDevice(String DeviceId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("UserId", currentUser.getId());
        hashMap.put("DeviceId", DeviceId);
        hashMap.put("TypeId", 1);

        Call<Result> call = service.deleteDevice(hashMap);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccess()) {
                    long code = response.body().getCode();

                    if (code == 0) {
                        Log.e("MYLOG", "Device Deleted Success");
                    } else {
                        Log.e("MYLOG", "Device Deleted FAIL");
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("MYLOG", "Device Deleted FAIL");
            }
        });
    }
}
