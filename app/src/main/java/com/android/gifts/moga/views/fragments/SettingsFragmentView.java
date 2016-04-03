package com.android.gifts.moga.views.fragments;

public interface SettingsFragmentView {
    void showProgress();
    void hideProgress();

    void showNameError(String error);
    void showSuccessMessage();
}
