package com.android.gifts.moga.presenter.signUp;

import android.content.Context;
import android.util.Log;

import com.android.gifts.moga.helpers.UIValidator;
import com.android.gifts.moga.interactor.signUp.SignUpInteractor;
import com.android.gifts.moga.interactor.signUp.SignUpInteractorImp;
import com.android.gifts.moga.views.activities.RegisterView;

public class SignUpPresenterImp implements SignUpPresenter, OnFinishedRegisterListener {
    RegisterView registerView;
    SignUpInteractor interactor;
    UIValidator uiValidator;

    public SignUpPresenterImp(Context context, RegisterView registerView) {
        this.registerView = registerView;
        interactor = new SignUpInteractorImp(context);
        uiValidator = new UIValidator();
    }

    @Override
    public void register(String name, String mail, String mobile, String password1, String password2, int year, int type) {
        if (registerView != null) {
            if (name.isEmpty()) {
                registerView.setNameError("الإسم مطلوب");
            } else if (mail.isEmpty()) {
                registerView.setMailError("البريد الإلكترونى مطلوب");
            } else if (!uiValidator.isValidEmail(mail)) {
                registerView.setMailError("البريد الإلكترونى غير صالح");
            } else if (mobile.isEmpty()) {
                registerView.setMobileError("الموبايل مطلوب");
            } else if (!uiValidator.isValidMobileNum(mobile)) {
                registerView.setMobileError("هذا الرقم غير صالح");
            } else if (password1.isEmpty()) {
                registerView.setPasswordError("كلمة المرور مطلوبة");
            } else if (!uiValidator.isValidPassword(password1)) {
                registerView.setPasswordError("كلمه المرور لابد أن تكون أكثر من 6 حروف و أرقام");
            } else if (password2.isEmpty() || !password2.equals(password1)) {
                registerView.setPasswordError("برجاء تأكيد كلمة المرور");
            } else {
                registerView.showProgress();
                interactor.register(name, mail, mobile, password1, year, type, this);
            }
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.hideProgress();
            registerView.navigateToNextActivity();
        }
    }

    @Override
    public void onMobileInUse() {
        if (registerView != null) {
            registerView.hideProgress();
            registerView.setMobileError("هذا الرقم مستخدم بالفعل");
        }
    }

    @Override
    public void onMailInUse() {
        if (registerView != null) {
            registerView.hideProgress();
            registerView.setMailError("هذا البريد الإلكترونى مستخدم بالفعل");
        }
    }

    @Override
    public void onFail() {
        if (registerView != null) {
            registerView.hideProgress();
            Log.e("Moga", "REGISTER FAIL");
            registerView.showNetworkError();
        }
    }
}