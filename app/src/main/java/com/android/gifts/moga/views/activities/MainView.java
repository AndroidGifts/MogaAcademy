package com.android.gifts.moga.views.activities;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.views.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void setUpTwoTabs(long yearId, List<News> entesabNews, List<News> entezamNews);
    void setUpThreeTabs(long yearId, List<News> khargyaNews, List<News> edaraNews, List<News> mohasbaNews);
}
