package com.android.gifts.moga.views.activities;

import com.android.gifts.moga.views.BaseView;

public interface RegisterView extends BaseView {
    void setNameError(String error);
    void setMailError(String error);
    void setMobileError(String error);
    void setPasswordError(String error);
}