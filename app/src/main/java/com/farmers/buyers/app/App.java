package com.farmers.buyers.app;

import android.app.Application;
import android.content.Context;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 11:41
 * mohammadsajjad679@gmail.com
 */

public class App extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
