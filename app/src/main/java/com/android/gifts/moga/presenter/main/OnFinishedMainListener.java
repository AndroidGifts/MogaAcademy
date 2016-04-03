package com.android.gifts.moga.presenter.main;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.API.model.Schedule;

import java.util.List;

public interface OnFinishedMainListener {
    void onNewsSuccess(List<News> news);
    void onNoNewsFound();

    void onSchedulesSuccess(List<Schedule> schedules);

    void onUpdateSuccess();

    void onFail();
}