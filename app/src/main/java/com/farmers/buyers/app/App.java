package com.farmers.buyers.app;

import android.app.Application;
import android.content.Context;

import com.farmers.buyers.modules.orders.OrderSingleton;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 11:41
 * mohammadsajjad679@gmail.com
 */

public class App extends Application {

    public static Context appContext;
    public static  boolean finish_activity=false;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        AppController.init(appContext, "https://farmercanada.com/public/api/");
        OrderSingleton singleton=new OrderSingleton();
        OrderSingleton.setInstance(singleton);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
