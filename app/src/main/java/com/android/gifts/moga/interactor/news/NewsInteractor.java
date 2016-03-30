package com.android.gifts.moga.interactor.news;

import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.presenter.news.OnFinishedNewsListener;

public interface NewsInteractor {
    void getNews(int pageIndex, int pageSize, int yearId, int typeId, OnFinishedNewsListener listener);
    UserVm getUser();
}