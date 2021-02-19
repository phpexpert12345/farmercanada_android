package com.farmers.buyers.modules.forgotPassword;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class ForgotPasswordRepository extends BaseRepository {

    public void doForgotPassword(ForgotPasswordRequestParams params, ApiResponseCallback<LoginApiModel> responseCallback) {
        Call<LoginApiModel> call = RetrofitBuilder.createServiceContract().getForgotPassword(ApiConstants.FORGOT_CHANGE_PASSWORD,
                params.getOtp(),
                params.getUserId(),
                params.getPassword(),
                params.getConfirmPassword(),
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void doVerifyForgotPassword(ForgotPasswordRequestParams params, ApiResponseCallback<LoginApiModel> responseCallback) {
        Call<LoginApiModel> call = RetrofitBuilder.createServiceContract().getVerifyForgotPassword(ApiConstants.FORGOT_PASSWORD,
                "91",
                params.getMobileNumber(),
                params.getRole(),
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
