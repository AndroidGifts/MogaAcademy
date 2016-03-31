package com.android.gifts.moga.interactor.news;

import android.content.Context;
import android.util.Log;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.API.model.NewsResponse;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.presenter.news.OnFinishedNewsListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsInteractorImp implements NewsInteractor {
    ComplexPreferences complexPreferences;
    List<News> newsYear;

    public NewsInteractorImp(Context context) {
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
    }

    @Override
    public void getNews(int pageIndex, int pageSize, final int yearId, int typeId, final OnFinishedNewsListener listener) {
        if (isNewsHere(yearId)) {
            listener.onSuccess(newsYear);
        } else {
            Log.e("FOF", "CAAAAALLLLLLLLINNNNG AAPPPPIIIIII");
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
                            switch (yearId) {
                                case 1:
                                    complexPreferences.putObject(Constants.NEWS1_PREF_KEY, newsResponse); break;
                                case 2:
                                    complexPreferences.putObject(Constants.NEWS2_PREF_KEY, newsResponse); break;
                                case 3:
                                    complexPreferences.putObject(Constants.NEWS3_PREF_KEY, newsResponse); break;
                                case 4:
                                    complexPreferences.putObject(Constants.NEWS4_PREF_KEY, newsResponse); break;
                            }
                            complexPreferences.commmit();

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
    }

    public boolean isNewsHere(int yearId) {
        if (yearId == 1) {
            if (complexPreferences.getObject(Constants.NEWS1_PREF_KEY, NewsResponse.class) != null) {
                newsYear = complexPreferences.getObject(Constants.NEWS1_PREF_KEY, NewsResponse.class).getNews();
            } else {
                return false;
            }
        } else if (yearId == 2) {
            if (complexPreferences.getObject(Constants.NEWS2_PREF_KEY, NewsResponse.class) != null) {
                newsYear = complexPreferences.getObject(Constants.NEWS2_PREF_KEY, NewsResponse.class).getNews();
            } else {
                return false;
            }
        } else if (yearId == 3) {
            if (complexPreferences.getObject(Constants.NEWS3_PREF_KEY, NewsResponse.class) != null) {
                newsYear = complexPreferences.getObject(Constants.NEWS3_PREF_KEY, NewsResponse.class).getNews();
            } else {
                return false;
            }
        } else if (yearId == 4) {
            if (complexPreferences.getObject(Constants.NEWS4_PREF_KEY, NewsResponse.class) != null) {
                newsYear = complexPreferences.getObject(Constants.NEWS4_PREF_KEY, NewsResponse.class).getNews();
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public UserVm getUser() {
        return complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class);
    }

    @Override
    public void deleteNews() {
        complexPreferences.deleteObject(Constants.NEWS1_PREF_KEY);
        complexPreferences.deleteObject(Constants.NEWS2_PREF_KEY);
        complexPreferences.deleteObject(Constants.NEWS3_PREF_KEY);
        complexPreferences.deleteObject(Constants.NEWS4_PREF_KEY);
        complexPreferences.commmit();
    }
}
