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

    public void setProfilePic(String value) {
        setSharedPreference(StorageKey.Token.toString(), value);
    }

    public String getProfilePic() {
        return (String) getSharedPreferences(StorageKey.Profile_Pic.toString(), "");
    }

    public void setWalletAmount(String value) {
        setSharedPreference(StorageKey.WalletAmount.toString(), value);
    }

    public String getWalletAmount() {
        return (String) getSharedPreferences(StorageKey.WalletAmount.toString(), "");
    }

    public void setIsComingFrom(int value) {
        setSharedPreference(StorageKey.ComingFrom.toString(), value);
    }

    public int getIsComingFrom() {
        return (int) getSharedPreferences(StorageKey.ComingFrom.toString(), 0);
    }

    public void setUserId(String value) {
        setSharedPreference(StorageKey.UserID.toString(), value);
    }

    public String getUserId() {
        return (String) getSharedPreferences(StorageKey.UserID.toString(), "");
    }


    public void setLoginId(String value) {
        setSharedPreference(StorageKey.LoginId.toString(), value);
    }

    public String getLoginId() {
        return (String) getSharedPreferences(StorageKey.LoginId.toString(), "");
    }

    public String getAuthenticationKey() {
        return (String) getSharedPreferences(StorageKey.AuthenticationKey.toString(), "");
    }

    public void setAuthenticationKey(String value) {
        setSharedPreference(StorageKey.AuthenticationKey.toString(), value);
    }

    public String getDeviceId() {
        return (String) getSharedPreferences(StorageKey.DeviceId.toString(), "");
    }

    public void setDeviceId(String value) {
        setSharedPreference(StorageKey.DeviceId.toString(), value);
    }

    public void setSignUpMobileNumber(String value) {
        setSharedPreference(StorageKey.SignUpMobileNumber.toString(), value);
    }


    public String getSignUpPhoneNumber() {
        return (String) getSharedPreferences(StorageKey.SignUpMobileNumber.toString(), "");
    }

    public void setFarmId(String value) {
        setSharedPreference(StorageKey.FarmId.toString(), value);
    }

    public String getFarmId() {
        return (String) getSharedPreferences(StorageKey.FarmId.toString(), "");
    }

    public String getRole() {
        return (String) getSharedPreferences(StorageKey.Role.toString(), "");
    }

    public void setRole(String value) {
        setSharedPreference(StorageKey.Role.toString(), value);
    }

    public void setIsStoreSetup(Boolean value) {
        setSharedPreference(StorageKey.IsStoreSetup.toString(), value);
    }

    public Boolean getIsStoreSetup() {
        return (Boolean) getSharedPreferences(StorageKey.IsStoreSetup.toString(), false);
    }

    public void setGoogleApiKey(String value) {
        setSharedPreference(StorageKey.GOOGLE_API_KEY.toString(), value);
    }

    public String getGoogleApiKey() {
        return (String) getSharedPreferences(StorageKey.GOOGLE_API_KEY.toString(), "");
    }


    public void setSharedPreference(String key, Object value) {
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


    public Object getSharedPreferences(String key, @Nullable Object defaultValue) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(prefsName, 0);
        Object value;

        if (String.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getString(key, String.valueOf(defaultValue));
        } else if (Integer.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (Boolean.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (Float.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (Long.class.equals(defaultValue.getClass())) {
            value = sharedPreferences.getLong(key, (Long) defaultValue);
        } else {
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


