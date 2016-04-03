package com.android.gifts.moga.interactor.main;

import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.presenter.main.OnFinishedMainListener;

public interface MainInteractor {
    void getNews(int pageIndex, int pageSize, int yearId, int typeId, OnFinishedMainListener listener);
    void getSchedules(int yearId, int typeId, OnFinishedMainListener listener);

    UserVm getUser();
    void deleteUser();
    void deletePrefs();

    void updateUser(UserVm user, OnFinishedMainListener listener);
}