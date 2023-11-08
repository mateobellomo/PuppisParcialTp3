package com.proyecto.personal.puppisparcialtp3.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPref {
    private static SharedPreferences mSharedPref;
    public static final String NAME = "NAME";
    public static final String IMAGE_URL = "IMAGE_URL";
    public static final Boolean DARK_MODE = false;

    private static List<OnImageURLChangeListener> imageURLChangeListeners = new ArrayList<>();


    private SharedPref() {
    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        if (key == SharedPref.IMAGE_URL) {
            notifyImageURLChangeListeners(value);
        }
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public interface OnImageURLChangeListener {
        void onImageURLChanged(String newImageUrl);
    }

    public static void addImageURLChangeListener(OnImageURLChangeListener listener) {
        imageURLChangeListeners.add(listener);
    }

    public static void removeImageURLChangeListener(OnImageURLChangeListener listener) {
        imageURLChangeListeners.remove(listener);
    }

    private static void notifyImageURLChangeListeners(String imageUrl) {
        for (OnImageURLChangeListener listener : imageURLChangeListeners) {
            listener.onImageURLChanged(imageUrl);
        }
    }
}
