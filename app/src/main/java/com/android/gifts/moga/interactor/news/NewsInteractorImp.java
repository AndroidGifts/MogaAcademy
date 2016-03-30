package com.android.gifts.moga.interactor.news;

import android.content.Context;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.NewsResponse;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.news.OnFinishedNewsListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsInteractorImp implements NewsInteractor {
    ComplexPreferences complexPreferences;

    public NewsInteractorImp(Context context) {
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
    }

    @Override
    public void getNews(int pageIndex, int pageSize, int yearId, int typeId, final OnFinishedNewsListener listener) {
        MogaApiInterface service = RestClient.getClient();

        // Type ID is not user in this function ....

        Call<NewsResponse> call = service.getNews(pageIndex, pageSize, yearId);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccess()) {
                    NewsResponse newsResponse = response.body();
                    long code = newsResponse.getResult().getCode();
                    if (code == 0) {
                        // Success with Data
                        listener.onSuccess(newsResponse.getNews());
                    } else if (code == 5) {
                        // No Data Found
                        listener.onNoDataFound();
                    } else {
                        // Failure
                        listener.onFail();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Failure
                listener.onFail();
            }
        });
    }

    @Override
    public UserVm getUser() {
        return complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class);
    }
}
