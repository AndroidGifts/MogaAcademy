package com.android.gifts.moga.presenter.news;

public interface NewsPresenter {
    void getNews(int pageIndex, int pageSize, int yearId, int typeId);
    long getUserYear();

    void clearNews();
}
