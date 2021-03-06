package com.android.gifts.moga.interactor.login;


import com.android.gifts.moga.presenter.login.OnFinishedLoginListener;

public interface LoginInteractor {
    void loginUser(String login, String password, OnFinishedLoginListener listener);

    void sendNewPassword(String email, OnFinishedLoginListener listener);

    boolean isUserLoggedIn();
}
