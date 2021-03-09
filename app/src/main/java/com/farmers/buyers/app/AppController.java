package com.farmers.buyers.app;

import android.content.Context;

import androidx.annotation.Nullable;

import com.farmers.buyers.storage.SharedPreferenceManager;


/**
 * created by Mohammad Sajjad
 * on 23-01-2021 at 00:53
 * mohammadsajjad679@gmail.com
 */

public class AppController implements AppControllerContract {

    private static AppController INSTANCE;
    private static String baseUrl;
    private static Context context;
    private SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.getInstance();


    public static AppController init(Context context, String baseUrl) {
        AppController.context = context;
        AppController.baseUrl = baseUrl;

        if (INSTANCE == null) {
            INSTANCE = new AppController();
        }
        return INSTANCE;
    }

    public static AppController get() {
        if (INSTANCE == null) {
            return new AppController();
        } else {
            return INSTANCE;
        }
    }

    @Override
    public Boolean getIsLoggedIn() {
        return sharedPreferenceManager.getIsLoggedIn();
    }

    @Override
    public String getUserId() {
        return sharedPreferenceManager.getUserId();
    }

    @Override
    public String getAuthenticationKey() {
        return sharedPreferenceManager.getAuthenticationKey();
    }

    @Override
    public String getDeviceId() {
        return sharedPreferenceManager.getDeviceId();
    }

    @Override
    public String getLoginId() {
        return sharedPreferenceManager.getLoginId();
    }

    @Override
    public String getWalletAmount() {
        return sharedPreferenceManager.getWalletAmount();
    }

    @Override
    public String getProfilePic() {
        return null;
    }

    @Override
    public String getSharedPreferences(String key, @Nullable Object defaultValue) {
        return null;
    }

    @Override
    public String getRole() {
        return sharedPreferenceManager.getRole();
    }

    @Override
    public Boolean getIsStoreSetup() {
        return sharedPreferenceManager.getIsStoreSetup();
    }

    @Override
    public String getRole() {
        return sharedPreferenceManager.getRole();
    }

    @Override
    public Boolean getIsStoreSetup() {
        return sharedPreferenceManager.getIsStoreSetup();
    }

    @Override
    public String getGoogleApiKey() {
        return sharedPreferenceManager.getGoogleApiKey();
    }

    @Override
    public String getFarmId() {
        return sharedPreferenceManager.getFarmId();
    }
}
