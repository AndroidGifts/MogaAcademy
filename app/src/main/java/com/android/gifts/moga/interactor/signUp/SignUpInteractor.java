package com.android.gifts.moga.interactor.signUp;

import com.android.gifts.moga.presenter.signUp.OnFinishedRegisterListener;

public interface SignUpInteractor {
    void register(String name, String mail, String mobile, String password, int year, OnFinishedRegisterListener listener);
}
