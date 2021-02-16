package com.farmers.buyers.modules.changePassword;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.http.Field;

public class ChangePasswordRepository extends BaseRepository {

    public void doChangePassword(ChangePasswordRequestParams params, ApiResponseCallback<LoginApiModel> responseCallback) {
        Call<LoginApiModel> call = RetrofitBuilder.createServiceContract().getChangePassword(ApiConstants.CHANGE_PASSWORD,
                params.getNew_password(),
                params.getConfirm_password(),
                params.getOld_Password(),
                params.getMobile_OTP(),
                params.getLoginId(),
                params.getAuth_key());
        makeRequest(call, responseCallback);
    }
}