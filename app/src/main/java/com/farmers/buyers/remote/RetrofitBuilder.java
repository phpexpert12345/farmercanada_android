package com.farmers.buyers.remote;

import com.farmers.buyers.app.App;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 10:58
 * mohammadsajjad679@gmail.com
 */

public class RetrofitBuilder {

    public static Retrofit retrofit = null;


    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .addInterceptor(new NetworkConnectionInterceptor(App.getAppContext())).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://glamrrealindia.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit;
    }

    public static ApiController createServiceContract() {
        return getRetrofitClient().create(ApiController.class);
    }
}
