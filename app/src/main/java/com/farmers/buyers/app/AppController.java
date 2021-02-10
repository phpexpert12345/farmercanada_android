package com.farmers.buyers.app;

import android.content.Context;

import com.farmers.buyers.storage.SharedPreferenceManager;


/**
 * created by Mohammad Sajjad
 * on 23-01-2021 at 00:53
 * mohammadsajjad679@gmail.com
 */

public class AppController implements AppControllerContract {

    private static AppController INSTANCE;
    private static String baseUrl ;
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
        }
        else {
            return INSTANCE;
        }
    }

    @Override
    public Boolean getIsLoggedIn() {
        return sharedPreferenceManager.getIsLoggedIn();
    }
}
