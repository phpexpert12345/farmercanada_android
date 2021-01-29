package com.farmers.buyers.core;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.NoInternetConnectionException;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.StandardError;

import org.jetbrains.annotations.NotNull;

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
                    Log.e("response", response.body().toString());
                    responseCallback.onSuccess(response.body());
                }
                else  {
                    responseCallback.onFailure(new StandardError(0, "","Something is wrong"));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (t instanceof NoInternetConnectionException) {
                    Log.e("response", t.getMessage());

                    responseCallback.onFailure(new StandardError(0, "",t.getMessage()));
                }
                else {
                    responseCallback.onFailure(new StandardError(0, "",t.getMessage()));
                }
            }
        });
    }
/*
    public<C, T> LiveData<T> getLogin(C params, ) {
        MutableLiveData<Resources<LoginApiModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resources.loading(new LoginApiModel()));

        ((Call<LoginApiModel>) apiCall.getFetchData(requestEntity)).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(@NotNull Call<ResponseEntity> call, @NotNull Response<ResponseEntity> response) {
                try {
                    LoginApiModel entity = response.body();
                    switch (response.code()) {
                        case 200:
                            mutableLiveData.setValue(Resources.success(entity, entity.getMessage()));
                            break;
                        case 201:
                            mutableLiveData.setValue(Resources.success(entity, entity.getMessage()));
                            break;
                        default:
                            mutableLiveData.setValue(Resources.error(response.toString(), new ResponseEntity()));
                            break;
                    }
                } catch (Exception e) {
                    mutableLiveData.setValue(Resources.error(e.getMessage(), new ResponseEntity()));
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                mutableLiveData.setValue(Resources.error(t.toString(), new ResponseEntity()));
            }
        });
        return mutableLiveData;
    }
*/

}
