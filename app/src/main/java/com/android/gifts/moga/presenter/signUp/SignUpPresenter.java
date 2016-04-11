package com.android.gifts.moga.presenter.signUp;

/**
 * Created by Mohamed Fareed on 3/23/2016.
 */
public interface SignUpPresenter {
    void register(String name, String mail, String mobile, String password1,
                  String password2, int year, int type);
}
