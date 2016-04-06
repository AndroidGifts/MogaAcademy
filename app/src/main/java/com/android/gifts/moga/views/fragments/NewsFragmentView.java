package com.android.gifts.moga.views.fragments;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.API.model.Schedule;

import java.util.List;

/**
 * Created by Mohamed Fareed on 4/1/2016.
 */
public interface NewsFragmentView {
    void initializeRecyclerView(List<News> firstNews);
    void updateRecyclerView(List<News> news);

    void initializeSchedules(List<Schedule> schedules);

    void showProgress();
    void hideProgress();

    void hideSpinner();
}
