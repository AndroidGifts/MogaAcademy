package com.android.gifts.moga.helpers.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class ComplexPreferences {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private SharedPreferences.Editor editor;

    private static ComplexPreferences complexPreferences;

    private ComplexPreferences(Context context, String name, int mode) {
        gson = new Gson();

        if (name == null || name.isEmpty())  {
            name = "theComplexPref";
        }

        sharedPreferences = context.getSharedPreferences(name, mode);
        editor = sharedPreferences.edit();
    }

    public static ComplexPreferences getComplexPreferences(Context context, String name, int mode) {
        if (complexPreferences == null) {
            complexPreferences = new ComplexPreferences(context, name, mode);
        }

        return complexPreferences;
    }

    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is null");
        }
        if (key == null || key.equals("")) {
            throw new IllegalArgumentException("Key is empty or null");
        }

        editor.putString(key, gson.toJson(object));
    }

    public void commit() {
        editor.commit();
    }

    public void clearPrefs() {
        editor.clear().commit();
    }

    public void deleteObject(String key) {
        editor.remove(key);
    }

    public <T> T getObject(String key, Class<T> a) {
        String json = sharedPreferences.getString(key, null);
        if (json == null) {
            Log.e("FOF", "JSON NULL");
            return null;
        } else {
            try {
                return gson.fromJson(json, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }
}
