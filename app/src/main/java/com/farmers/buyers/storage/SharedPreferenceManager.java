package com.farmers.buyers.storage;

import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;

import kotlin.TypeCastException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 12:27
 * mohammadsajjad679@gmail.com
 */

public class SharedPreferenceManager {

    private static String prefsName = "prefsName";
    private static SharedPreferenceManager sharedPreferenceManager;

    public static SharedPreferenceManager getInstance() {
        return sharedPreferenceManager == null ? sharedPreferenceManager = new SharedPreferenceManager() : sharedPreferenceManager;
    }


    public void setIsLoggedIn(Boolean value) {
        setSharedPreference(StorageKey.isLoggedIn.toString(), value);
    }

    public Boolean getIsLoggedIn() {
        return (Boolean) getSharedPreferences(StorageKey.isLoggedIn.toString(), false);
    }

    public void setToken(String value) {
        setSharedPreference(StorageKey.Token.toString(), value);
    }

    public String getToken() {
        return (String) getSharedPreferences(StorageKey.Token.toString(), "");
    }



    private void setSharedPreference(String key, Object value) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(prefsName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String) {
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type String");
            }
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type Int");
            }
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type Boolean");
            }

            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type Float");
            }

            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            if (value == null) {
                throw new TypeCastException("null cannot be cast to non-null type Long");
            }

            editor.putLong(key, (Long) value);
        }

        editor.apply();
    }


    private Object getSharedPreferences(String key, @Nullable Object defaultValue) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(prefsName, 0);
        Object value;

        if (String.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getString(key, String.valueOf(defaultValue));
        }
        else if (Integer.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getInt(key, (Integer) defaultValue);
        }

        else if (Boolean.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        }

        else if (Float.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getFloat(key, (Float) defaultValue);
        }

        else if (Long.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getLong(key, (Long) defaultValue);
        }
        else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        return value;

    }

    private void removeSharedPreference(String key) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(prefsName, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearUserInfo() {
        removeSharedPreference(StorageKey.isLoggedIn.toString());
        sharedPreferenceManager.setIsLoggedIn(false);
    }


}


