package com.farmers.buyers.remote;

import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.splash.AuthenticationApiModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:10
 * mohammadsajjad679@gmail.com
 */

public interface ApiController {


    @GET
    Call<AuthenticationApiModel> authenticateUser(@Url String url);

    @FormUrlEncoded
    @POST
    Call<LoginApiModel> getUserLogin(@Url String url, @Field("account_email") String mobile, @Field("account_password") String password, @Field("account_type") Integer user_type, @Field("device_id") String deviceId, @Field("device_platform") String platform, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<LoginApiModel> getVerifyForgotPassword(@Url String url, @Field("account_phone_code") String account_phone_code,
                                                @Field("account_mobile") String account_mobile,
                                                @Field("account_type") Integer user_type,
                                                @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<LoginApiModel> getForgotPassword(@Url String url,
                                          @Field("Mobile_OTP") String Mobile_OTP,
                                          @Field("LoginId") String LoginId,
                                          @Field("new_password") String new_password,
                                          @Field("confirm_password") String confirm_password,
                                          @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AllDataModel> getCategoryList(@Url String url, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AllDataModel> getOffersList(@Url String url, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> getAddressList(@Url String url, @Field("LoginId") String LoginId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> addAddress(@Url String url, @Field("LoginId") String LoginId,
                                     @Field("name_of_address") String name_of_address,
                                     @Field("complete_address") String complete_address,
                                     @Field("address_city") String address_city,
                                     @Field("address_state") String address_state,
                                     @Field("address_postcode") String address_postcode,
                                     @Field("account_phone_number") String account_phone_number,
                                     @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<LoginApiModel> getChangePassword(@Url String url, @Field("new_password") String new_password,
                                          @Field("confirm_password") String confirm_password,
                                          @Field("Old_Password") String Old_Password,
                                          @Field("Mobile_OTP") String Mobile_OTP,
                                          @Field("LoginId") String LoginId,
                                          @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<SignUpApiModel> doUserSignUp(@Url String url, @Field("account_name") String name, @Field("account_mobile") String mobile, @Field("account_email") String email, @Field("account_password") String password, @Field("account_type") Integer account_type, @Field("account_country") String account_country, @Field("account_state") String account_state, @Field("account_city") String account_city, @Field("account_address") String account_address, @Field("account_lat") String account_lat, @Field("account_long") String account_long, @Field("account_phone_code") String account_phone_code, @Field("device_id") String device_id, @Field("device_platform") String device_platform, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<SendOtpApiModel> requestOtp(@Url String url, @Field("Mobile") String mobile, @Field("auth_key") String authKey, @Field("LoginId") String loginId);


    @FormUrlEncoded
    @POST
    Call<VerifyOtpApiModel> doVerifyOtp(@Url String url, @Field("id") String userId, @Field("otp") String otp);

    @FormUrlEncoded
    @POST
    Call<VerifyOtpApiModel> doVerifyRegisterOtp(@Url String url, @Field("LoginId") String userId, @Field("Mobile_OTP") String Mobile_OTP, @Field("auth_key") String authKey);


}