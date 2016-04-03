package com.android.gifts.moga.views;

/**
 * Created by Mohamed Fareed on 3/22/2016.
 */
public interface BaseView {
    void showProgress();
    void hideProgress();
    void navigateToNextActivity();

    void showNetworkError();
}