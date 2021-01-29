package com.farmers.buyers.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.farmers.buyers.app.NoInternetConnectionException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 10:55
 * mohammadsajjad679@gmail.com
 */

public class NetworkConnectionInterceptor implements Interceptor {
    private Context mContext;

    public NetworkConnectionInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!isConnected()) {
            try {
                throw new NoInternetConnectionException();
            } catch (NoInternetConnectionException e) {
                e.printStackTrace();
            }
            // Throwing our custom exception 'NoConnectivityException'
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

}
