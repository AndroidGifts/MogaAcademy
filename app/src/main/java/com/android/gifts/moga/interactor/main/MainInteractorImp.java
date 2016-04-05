package com.android.gifts.moga.interactor.main;

import android.content.Context;

import com.android.gifts.moga.API.MogaApiInterface;
import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.NewsResponse;
import com.android.gifts.moga.API.model.Result;
import com.android.gifts.moga.API.model.SchedulesResponse;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.Constants;
import com.android.gifts.moga.helpers.prefs.ComplexPreferences;
import com.android.gifts.moga.helpers.prefs.ObjectPreference;
import com.android.gifts.moga.interactor.notifications.NotificationInteractor;
import com.android.gifts.moga.interactor.notifications.NotificationsInteractorImp;
import com.android.gifts.moga.presenter.main.OnFinishedMainListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImp implements MainInteractor {
    ComplexPreferences complexPreferences;
    MogaApiInterface service;
    UserVm currentUser;
    Context context;

    public MainInteractorImp(Context context) {
        this.context = context;
        ObjectPreference preference = (ObjectPreference) context.getApplicationContext();
        complexPreferences = preference.getComplexPreference();
        service = RestClient.getClient();
    }

    @Override
    public void getNews(final int pageIndex, int pageSize, final int yearId, final int typeId, final OnFinishedMainListener listener) {
        NewsResponse cachedResponse = complexPreferences.getObject(Constants.NEWS_KEY + yearId + typeId, NewsResponse.class);
        if (cachedResponse != null && pageIndex == 0) {
            listener.onNewsSuccess(cachedResponse.getNews());
        } else {
            // Type ID is not user in this function ....
            Call<NewsResponse> call = service.getNews(pageIndex, pageSize, yearId, typeId);

            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccess()) {
                        NewsResponse newsResponse = response.body();
                        long code = newsResponse.getResult().getCode();
                        if (code == 0) {
                            if (pageIndex == 0) {
                                complexPreferences.putObject(Constants.NEWS_KEY + yearId + typeId, newsResponse);
                                complexPreferences.commit();
                            }

                            listener.onNewsSuccess(newsResponse.getNews());
                        } else if (code == 5) {
                            // No Data Found
                            listener.onNoNewsFound();
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

    @Override
    public void updateUser(final UserVm user, final OnFinishedMainListener listener) {
        Call<Result> call = service.updateUser(user);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccess()) {
                    long code = response.body().getCode();
                    if (code == 0) {
                        // Save user to prefs
                        complexPreferences.deleteObject(Constants.USER_PREF_KEY);
                        complexPreferences.commit();

                        complexPreferences.putObject(Constants.USER_PREF_KEY, user);
                        complexPreferences.commit();
                        listener.onUpdateSuccess();
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
    public void getSchedules(final int yearId, final int typeId, final OnFinishedMainListener listener) {
        SchedulesResponse cachedResponse = complexPreferences.getObject(Constants.SCHEDULES + yearId + typeId, SchedulesResponse.class);

        if (cachedResponse != null) {
            listener.onSchedulesSuccess(cachedResponse.getSchedules());
        } else {
            Call<SchedulesResponse> call = service.getSchedules(yearId, typeId);

            call.enqueue(new Callback<SchedulesResponse>() {
                @Override
                public void onResponse(Call<SchedulesResponse> call, Response<SchedulesResponse> response) {
                    if (response.isSuccess()) {
                        SchedulesResponse schedulesResponse = response.body();
                        long code = schedulesResponse.getResult().getCode();
                        if (code == 0) {
                            // cache these schedules
                            complexPreferences.putObject(Constants.SCHEDULES + yearId + typeId, schedulesResponse);
                            complexPreferences.commit();

                            listener.onSchedulesSuccess(schedulesResponse.getSchedules());
                        } else if (code == 5) {
                            // No Data Found
                            listener.onNoNewsFound();
                        } else {
                            // Failure
                            listener.onFail();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SchedulesResponse> call, Throwable t) {
                    // Failure
                    listener.onFail();
                }
            });
        }
    }

    @Override
    public UserVm getUser() {
        currentUser = complexPreferences.getObject(Constants.USER_PREF_KEY, UserVm.class);
        return currentUser;
    }

    @Override
    public void deleteUser() {

        // Delete Device ID from server also
        NotificationInteractor notificationInteractor = new NotificationsInteractorImp(context);
        notificationInteractor.deleteDevice(complexPreferences.getObject(Constants.USER_DEVICE_ID, String.class));

        complexPreferences.deleteObject(Constants.USER_PREF_KEY);
        complexPreferences.commit();
        userDeleted = true;
    }

    boolean userDeleted = false;
    @Override
    public void deletePrefs() {
        // If user is logging out, so don't save his data again
        complexPreferences.clearPrefs();
        if (!userDeleted) {
            complexPreferences.putObject(Constants.USER_PREF_KEY, currentUser);
            complexPreferences.commit();
        }
    }
}
