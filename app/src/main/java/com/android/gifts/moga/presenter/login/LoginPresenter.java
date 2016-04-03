package com.android.gifts.moga.presenter.login;

public interface LoginPresenter {
    void login(String login, String password);
    boolean isUserHere();

    void sendNewPass(String email);

    void onDestroy();
}
