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
    Call<LoginApiModel> getUserLogin(@Url String url, @Field("mobile") String mobile, @Field("password") String password);


    @FormUrlEncoded
    @POST
    Call<SignUpApiModel> doUserSignUp(@Url String url, @Field("name") String name,@Field("mobile") String mobile,@Field("email") String email,@Field("password") String password);
}