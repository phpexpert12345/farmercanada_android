package com.farmers.buyers.remote;

import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.splash.AuthenticationApiModel;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    Call<AllDataModel> getUserInformation(@Url String url, @Field("LoginId") String LoginId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AllDataModel> getWalletHistory(@Url String url, @Field("LoginId") String LoginId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> getAddressList(@Url String url, @Field("LoginId") String LoginId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> deleteAddress(@Url String url, @Field("LoginId") String LoginId, @Field("address_id") String address_id, @Field("auth_key") String authKey);

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
    Call<SignUpApiModel> addMoney(@Url String url, @Field("LoginId") String LoginId,
                                  @Field("wallet_amount") String wallet_amount,
                                  @Field("wallet_transation_id") String wallet_transation_id,
                                  @Field("wallet_transation_status") String wallet_transation_status,
                                  @Field("auth_key") String authKey);

    @Multipart
    @POST
    Call<SignUpApiModel> editProfile(@Url String url,
                                     @Part("LoginId") RequestBody LoginId,
                                     @Part("account_name") RequestBody account_name,
                                     @Part("account_email") RequestBody account_email,
                                     @Part MultipartBody.Part account_photo,
                                     @Part("auth_key") RequestBody auth_key);


    @FormUrlEncoded
    @POST
    Call<SendOtpApiModel> requestOtp(@Url String url, @Field("Mobile") String mobile, @Field("auth_key") String authKey, @Field("LoginId") String loginId);


    @FormUrlEncoded
    @POST
    Call<VerifyOtpApiModel> doVerifyOtp(@Url String url, @Field("id") String userId, @Field("otp") String otp);

    @FormUrlEncoded
    @POST
    Call<VerifyOtpApiModel> doVerifyRegisterOtp(@Url String url, @Field("LoginId") String userId, @Field("Mobile_OTP") String Mobile_OTP, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<FarmListResponse> FARM_LIST_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                                   @Field("customer_lat") String customer_lat,
                                                   @Field("customer_long") String customer_long,
                                                   @Field("customer_full_address") String customer_full_address,
                                                   @Field("customer_city") String customer_city,
                                                   @Field("farm_type") String farm_type,
                                                   @Field("farm_type_developer_information") String farm_type_developer_information,
                                                   @Field("farm_service_type") String farm_service_type,
                                                   @Field("order_type_developer_information") String order_type_developer_information,
                                                   @Field("farm_category_id") String farm_category_id,
                                                   @Field("pageno") String pageno,
                                                   @Field("LoginId") String LoginId);
    @FormUrlEncoded
    @POST
    Call<FarmListProductResponse>FARM__PRODUCT_LIST_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey, @Field("farm_id") String farmId);


    @FormUrlEncoded
    @POST
    Call<ApplyCouponResponse>APPLY_COUPON_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                                        @Field("farm_id") String farmId,
                                                        @Field("coupon_code")String couponCode,
                                                        @Field("subtotal_amount") int subTotalAmount);

    @FormUrlEncoded
    @POST
    Call<ReviewListResponse> REVIEWD_LIST_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey, @Field("LoginId") String loginId);


}