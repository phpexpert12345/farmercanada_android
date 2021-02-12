package com.farmers.buyers.modules.signUp;



import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:44
 * mohammadsajjad679@gmail.com
 */

public class SignUpRepository extends BaseRepository {

    public void doUserSignUp(SignUpRequestParams params, ApiResponseCallback<SignUpApiModel> responseCallback) {
//        Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().doUserSignUp(
//                ApiConstants.SIGN_UP,
//                params.getName(),
//                params.getMobile(),
//                params.getEmail(),
//                params.getPassword()
//        );
//        makeRequest(call, responseCallback);
    }
    public void doUserRegis(SignUpRequestParams params,ApiResponseCallback<SignUpApiModel> responseCallback){
        Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().doUserSignUp(
                ApiConstants.REGIS,
                params.getName(),
                params.getMobile(),
                params.getEmail(),
                params.getPassword(),
                params.getAccount_type(),
                params.getAccount_country(),
                params.getAccount_state(),
                params.getAccount_city(),
                params.getAccount_address(),
                params.getAccount_lat(),
                params.getAccount_long(),
                params.getAccount_phone_code(),
                params.getDevice_id(),
                params.getDevice_platform(),
                params.getAuthKey()
        );
        makeRequest(call, responseCallback);
    }


    public void reSendOtp(SendOtpRequestParams params, ApiResponseCallback<SendOtpApiModel> responseCallback) {

        Call<SendOtpApiModel> call = RetrofitBuilder.createServiceContract().requestOtp(ApiConstants.RESEND_OTP, params.getNumber(), params.getAuthKey(), params.getLoginId());
        makeRequest(call, responseCallback);
    }

    public void verifyOtp(VerifyOtpRequestParams params, ApiResponseCallback<VerifyOtpApiModel> responseCallback) {
        Call<VerifyOtpApiModel> call = RetrofitBuilder.createServiceContract().doVerifyOtp(ApiConstants.VERIFY_OTP, params.getUserId(), params.getOtp());
        makeRequest(call, responseCallback);
    }

}
