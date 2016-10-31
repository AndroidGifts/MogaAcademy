package com.android.gifts.moga.schedules;

import com.android.gifts.moga.API.RestClient;
import com.android.gifts.moga.API.model.SchedulesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ScheduleListInteractor implements ScheduleListContract.Interactor {

    ScheduleListInteractor() {
    }

    @Override
    public void getSchedules(int yearId, int typeId, int scheduleType, final GetSchedulesCallback callback) {
        RestClient.getClient().getSchedules(yearId, typeId, scheduleType)
                .enqueue(new Callback<SchedulesResponse>() {
                    @Override
                    public void onResponse(Call<SchedulesResponse> call, Response<SchedulesResponse> response) {
                        if (response.isSuccess()) {
                            callback.success(response.body().getSchedules());
                        } else {
                            callback.fail("تحقق من اتصال الإنترنت الخاص بك وحاول مرة أخرى");
                        }
                    }

                    @Override
                    public void onFailure(Call<SchedulesResponse> call, Throwable t) {
                        callback.fail("تحقق من اتصال الإنترنت الخاص بك وحاول مرة أخرى");
                    }
                });
    }
}
