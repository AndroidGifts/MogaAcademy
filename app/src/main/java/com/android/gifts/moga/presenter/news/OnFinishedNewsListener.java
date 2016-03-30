package com.android.gifts.moga.presenter.news;

import com.android.gifts.moga.API.model.News;

import java.util.List;

public interface OnFinishedNewsListener {
    void onSuccess(List<News> news);
    void onNoDataFound();
    void onFail();
}
