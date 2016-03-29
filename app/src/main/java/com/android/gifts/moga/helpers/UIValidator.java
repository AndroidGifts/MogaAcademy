package com.android.gifts.moga.helpers;

import android.util.Patterns;

import java.util.regex.Pattern;

public class UIValidator {
    public boolean isValidMobileNum(String mobileNumber) {
        return Pattern.compile(Constants.MOBILE_PATTERN).matcher(mobileNumber).matches();
    }

    public boolean isValidEmail(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    public boolean isValidCredentials(String credentials) {
        return Patterns.EMAIL_ADDRESS.matcher(credentials).matches()
                || Pattern.compile(Constants.MOBILE_PATTERN).matcher(credentials).matches();
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}