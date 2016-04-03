package com.android.gifts.moga.presenter.main;

import android.content.Context;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.API.model.Schedule;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.helpers.UIValidator;
import com.android.gifts.moga.interactor.main.MainInteractor;
import com.android.gifts.moga.interactor.main.MainInteractorImp;
import com.android.gifts.moga.views.fragments.NewsFragmentView;
import com.android.gifts.moga.views.fragments.SettingsFragmentView;

import java.util.List;

public class MainPresenterImp implements MainPresenter, OnFinishedMainListener {
    private MainInteractor interactor;
    private NewsFragmentView newsFragmentView;

    private SettingsFragmentView settingsFragmentView;
    private UserVm currentUser;

    private int pageIndex = 0;

    public MainPresenterImp(Context context) {
        interactor = new MainInteractorImp(context);
        currentUser = interactor.getUser();
    }

    public MainPresenterImp(NewsFragmentView newsFragmentView, Context context) {
        this.newsFragmentView = newsFragmentView;
        interactor = new MainInteractorImp(context);
        currentUser = interactor.getUser();
    }

    public MainPresenterImp(SettingsFragmentView settingsFragmentView, Context context) {
        this.settingsFragmentView = settingsFragmentView;
        interactor = new MainInteractorImp(context);
        currentUser = interactor.getUser();
    }

    @Override
    public long getUserYear() {
        return currentUser.getYearId();
    }

    @Override
    public UserVm getUser() {
        return currentUser;
    }

    @Override
    public void updateUser(String name, int year) {
        if (settingsFragmentView != null) {
            if (name.isEmpty()) {
                settingsFragmentView.showNameError("الإسم مطلوب");
            } else {
                settingsFragmentView.showProgress();
                interactor.updateUser(new UserVm(currentUser.getId(), name, currentUser.getEmail(), currentUser.getMobile(),
                        currentUser.getPassword(), year, currentUser.getCreatedAt()), this);
            }
        }
    }

    @Override
    public void onUpdateSuccess() {
        if (settingsFragmentView != null) {
            settingsFragmentView.hideProgress();
            settingsFragmentView.showSuccessMessage();
        }
    }

    @Override
    public void getNews(int pageIndex, int pageSize, int yearId, int typeId) {
        this.pageIndex = pageIndex;
        if (pageIndex == 0) {
            newsFragmentView.showProgress();
        }
        interactor.getNews(pageIndex, pageSize, yearId, typeId, this);
    }

    @Override
    public void clearNews() {
        interactor.deletePrefs();
    }

    @Override
    public void onNewsSuccess(List<News> news) {
        if (pageIndex == 0) {
            newsFragmentView.hideProgress();
            newsFragmentView.initializeRecyclerView(news);
        } else {
            newsFragmentView.updateRecyclerView(news);
        }
    }

    @Override
    public void onNoNewsFound() {
        if (pageIndex > 0) {
            newsFragmentView.hideSpinner();
        } else {
            newsFragmentView.hideProgress();
        }
    }

    @Override
    public void getSchedules(int yearId, int typeId) {
        interactor.getSchedules(yearId, typeId, this);
    }

    @Override
    public void logOutUser() {
        interactor.deleteUser();
    }

    @Override
    public void onSchedulesSuccess(List<Schedule> schedules) {

    }

    @Override
    public void onFail() {
        newsFragmentView.hideProgress();
    }
}