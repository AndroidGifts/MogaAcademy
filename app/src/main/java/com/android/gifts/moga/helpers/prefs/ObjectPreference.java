package com.android.gifts.moga.helpers.prefs;

import android.app.Application;
import android.util.Log;

import com.android.gifts.moga.helpers.Constants;

public class ObjectPreference extends Application {
    private ComplexPreferences complexPreferences = null;

    @Override
    public void onCreate() {
        super.onCreate();
        complexPreferences = ComplexPreferences.getComplexPreferences(getBaseContext(),
                Constants.PREFERENCE_NAME, MODE_PRIVATE);

        Log.e("FOF", "Pref Created");
    }

    public ComplexPreferences getComplexPreference() {
        if (complexPreferences != null) {
            return complexPreferences;
        }
        return null;
    }
}
