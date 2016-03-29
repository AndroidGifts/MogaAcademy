package com.android.gifts.moga.presenter.login;


import android.content.Context;
import android.util.Log;

import com.android.gifts.moga.helpers.UIValidator;
import com.android.gifts.moga.interactor.login.LoginInteractor;
import com.android.gifts.moga.interactor.login.LoginInteractorImp;
import com.android.gifts.moga.views.activities.LoginView;

public class LoginPresenterImp implements LoginPresenter, OnFinishedLoginListener {
    LoginView loginView;
    LoginInteractor interactor;
    UIValidator uiValidator;

    public LoginPresenterImp(Context context, LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImp(context);
        uiValidator = new UIValidator();
    }

    @Override
    public void login(String login, String password) {
        if (loginView != null) {
            if (login.isEmpty()) {
                loginView.setLoginError("من فضلك أدخل البريد الإلكترونى أو الموبايل");
            } else if (!uiValidator.isValidCredentials(login)) {
                loginView.setLoginError("البريد الإلكترونى أو الموبايل غير صالح");
            } else if (!uiValidator.isValidPassword(password)) {
                loginView.setPasswordError("كلمه المرور لابد أن تكون أكثر من 6 حروف و أرقام");
            } else {
                loginView.showProgress();
                interactor.loginUser(login, password, this);
            }
        }
    }

    @Override
    public boolean isUserHere() {
        return interactor.isUserLoggedIn();
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.navigateToNextActivity();
        }
    }

    @Override
    public void onLoginError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setLoginError("البريد الإلكترونى أو الموبايل غير صالح");
        }
    }

    @Override
    public void onFail() {
        if (loginView != null) {
            loginView.hideProgress();
            Log.e("Moga", "LOGIN FAIL");
        }
    }
}
