package com.android.gifts.moga.views.activities;

import com.android.gifts.moga.views.BaseView;

public interface LoginView extends BaseView {
    void setLoginError(String error);
    void setPasswordError(String error);
}
