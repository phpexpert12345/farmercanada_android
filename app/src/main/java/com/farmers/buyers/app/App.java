package com.farmers.buyers.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.farmers.buyers.modules.orders.OrderSingleton;
import com.stripe.android.CustomerSession;
import com.stripe.android.EphemeralKeyProvider;
import com.stripe.android.EphemeralKeyUpdateListener;
import com.stripe.android.PaymentConfiguration;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 11:41
 * mohammadsajjad679@gmail.com
 */

public class App extends Application {

    public static Context appContext;

    public static  boolean finish_activity=false;
    public static boolean updated=false;
    public static boolean wallet_updated=false;
    public static boolean cart_updated=false;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        AppController.init(appContext, "https://farmercanada.com/public/api/");
        OrderSingleton singleton=new OrderSingleton();
        OrderSingleton.setInstance(singleton);
        PaymentConfiguration.init(this,"pk_test_51H335kI4oh76Z6dpZGTM13kKY5tMuzpQpGAzDOxhjLIHvzgD3IUWsznINS83NYvmTtXWOugAVvlnMfIDC5c8X2cm00V8TXD3tL");
        CustomerSession.initCustomerSession(this, new EphemeralKeyProvider() {
            @Override
            public void createEphemeralKey(@NonNull String apiVersion, @NonNull EphemeralKeyUpdateListener keyUpdateListener) {

            }
        }, false);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
