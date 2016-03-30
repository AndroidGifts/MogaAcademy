package com.android.gifts.moga.presenter.news;

import android.content.Context;
import android.util.Log;

import com.android.gifts.moga.API.model.News;
import com.android.gifts.moga.API.model.UserVm;
import com.android.gifts.moga.interactor.news.NewsInteractor;
import com.android.gifts.moga.interactor.news.NewsInteractorImp;
import com.android.gifts.moga.views.activities.MainView;

import java.util.ArrayList;
import java.util.List;

public class NewsPresenterImp implements NewsPresenter, OnFinishedNewsListener {
    private MainView mainView;
    private NewsInteractor interactor;

    private int pageIndex = 0;
    private int yearID;

    public NewsPresenterImp(Context context, MainView mainView) {
        this.mainView = mainView;
        interactor = new NewsInteractorImp(context);
    }

    @Override
    public void getNews(int pageIndex, int pageSize, int yearId, int typeId) {
        this.pageIndex = pageIndex;
        this.yearID = yearId;

        if (mainView != null) {
            // show progress bar for first page only
            if (pageIndex == 0) {
                mainView.showProgress();
            }
        }
        interactor.getNews(pageIndex, pageSize, yearId, typeId, this);
    }

    @Override
    public long getUserYear() {
        return interactor.getUser().getYearId();
    }

    @Override
    public void onSuccess(List<News> news) {
        if (mainView != null) {
            if (yearID <= 2) {
                // If userYear < 2, parse 2 lists of news and setUp two tabs
                List<News> newsType1 = new ArrayList<>();
                List<News> newsType2 = new ArrayList<>();

                for (int i = 0; i < news.size(); i++) {
                    long typeID = news.get(i).getTypeId();
                    if (typeID == 1) {
                        newsType1.add(news.get(i));
                    } else if (typeID == 2) {
                        newsType2.add(news.get(i));
                    }
                }

                mainView.setUpTwoTabs(newsType2, newsType1);

            } else {
                // If userYear > 2, parse 3 lists of news and setUp three tabs
                List<News> newsType3 = new ArrayList<>();
                List<News> newsType4 = new ArrayList<>();
                List<News> newsType5 = new ArrayList<>();

                for (int i = 0; i < news.size(); i++) {
                    long typeID = news.get(i).getTypeId();
                    if (typeID == 3) {
                        newsType3.add(news.get(i));
                    } else if (typeID == 4) {
                        newsType4.add(news.get(i));
                    } else if (typeID == 5) {
                        newsType5.add(news.get(i));
                    }
                }

                mainView.setUpThreeTabs(newsType5, newsType4, newsType3);
            }

            mainView.hideProgress();
        }
    }

    @Override
    public void onNoDataFound() {
        if (mainView != null) {
            mainView.hideProgress();
        }
    }

    @Override
    public void onFail() {
        if (mainView != null) {
            mainView.hideProgress();
        }
    }
}
