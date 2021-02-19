package com.farmers.buyers.modules.splash;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * created by Mohammad Sajjad
 * on 12-02-2021 at 15:27
 * mohammadsajjad679@gmail.com
 */

public class SplashRepository extends BaseRepository {


    public void authenticateUser(ApiResponseCallback<AuthenticationApiModel> responseCallback) {
        Call<AuthenticationApiModel> call = RetrofitBuilder.createServiceContract().authenticateUser(ApiConstants.AUTHENTICATION);
        makeRequest(call, responseCallback);
    }
}
