package com.android.gifts.moga.presenter.login;

/**
 * Created by Mohamed Fareed on 3/29/2016.
 */
public interface OnFinishedLoginListener {
    void onSuccess();
    void onLoginError();

    void onSendNewPassSuccess();

    void onFail();
}
