package com.farmers.buyers.remote;

import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:10
 * mohammadsajjad679@gmail.com
 */

public interface ApiController {


    @FormUrlEncoded
    @POST
    Call<LoginApiModel> getUserLogin(@Url String url, @Field("mobile") String mobile, @Field("password") String password,@Field("user_type") Integer user_type);


    @FormUrlEncoded
    @POST
    Call<SignUpApiModel> doUserSignUp(@Url String url, @Field("account_name") String name,@Field("account_mobile") String mobile,@Field("account_email") String email,@Field("account_password") String password,@Field("account_type") Integer account_type,@Field("account_country") String account_country,@Field("account_state") String account_state,@Field("account_city") String account_city,@Field("account_address") String account_address,@Field("account_lat") String account_lat,@Field("account_long") String account_long,@Field("account_phone_code") String account_phone_code,@Field("device_id") String device_id,@Field("device_platform") String device_platform);
}