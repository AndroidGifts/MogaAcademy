package com.android.gifts.moga.presenter.main;

import com.android.gifts.moga.API.model.UserVm;

public interface MainPresenter {
    void getNews(int pageIndex, int pageSize, int yearId, int typeId);
    long getUserYear();

    void getSchedules(int yearId, int typeId);
    UserVm getUser();

    void updateUser(String name, int year);

    void logOutUser();
    void clearNews();
}
