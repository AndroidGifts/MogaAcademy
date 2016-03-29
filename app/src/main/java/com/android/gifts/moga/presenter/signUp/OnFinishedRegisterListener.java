package com.android.gifts.moga.presenter.signUp;

/**
 * Created by Mohamed Fareed on 3/30/2016.
 */
public interface OnFinishedRegisterListener {
    void onSuccess();
    void onMobileInUse();
    void onMailInUse();
    void onFail();
}
