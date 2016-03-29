package com.android.gifts.moga.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class ComplexPreferences {
    private Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private SharedPreferences.Editor editor;

    private static ComplexPreferences complexPreferences;

    private ComplexPreferences(Context context, String name, int mode) {
        this.context = context;
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

        Log.e("FOF", "LOCAL getComplexPreference Called");

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
        Log.e("FOF", "PUTTED OBJECT");
    }

    public void commmit() {
        editor.commit();
        Log.e("FOF", "COMMIT HERE");
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
                Log.e("FOF", "OBJECT RETURNED");
                return gson.fromJson(json, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }
}
