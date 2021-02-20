package com.farmers.buyers.core;

import android.util.Log;

import com.farmers.buyers.app.NoInternetConnectionException;
import com.farmers.buyers.remote.StandardError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 15:07
 * mohammadsajjad679@gmail.com
 */

public class BaseRepository {

    public <T> void makeRequest(Call<T> call,final ApiResponseCallback<T> responseCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    Log.e("response", response.toString());
                    responseCallback.onSuccess(response.body());
                }
                else  {
                    responseCallback.onFailure(new StandardError(0, "",response.message()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (t instanceof NoInternetConnectionException) {
                    responseCallback.onFailure(new StandardError(0, "",new NoInternetConnectionException().getMessage()));
                }
                else {
                    responseCallback.onFailure(new StandardError(0, "",t.getMessage()));
                }
            }
        });
    }

}
