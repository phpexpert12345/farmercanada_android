package com.farmers.buyers.remote;

import androidx.annotation.Nullable;

import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowersApiModel;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.home.search.model.HomeSearchApiModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductApiResponseModel;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.DeleteCouponApiModel;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.ProductListApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.splash.AuthenticationApiModel;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
    Call<AllDataModel> changeUserType(@Url String url, @Field("LoginId") String LoginId, @Field("account_type") String account_type, @Field("auth_key") String authKey);

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
    Call<AddressApiModel> getReferAndEarn(@Url String url, @Field("LoginId") String LoginId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> getDateData(@Url String url, @Field("auth_key") String authKey, @Field("LoginId") String LoginId);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> getOrderTimeByDate(@Url String url,
                                             @Field("auth_key") String authKey,
                                             @Field("LoginId") String LoginId,
                                             @Field("farm_id") String farm_id,
                                             @Field("current_date") String current_date);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> deleteAddress(@Url String url, @Field("LoginId") String LoginId, @Field("address_id") String address_id, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> subOrderList(@Url String url, @Field("LoginId") String LoginId, @Field("farm_followed_status") String farm_followed_status, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> orderDetails(@Url String url, @Field("LoginId") String LoginId, @Field("farm_followed_status") String order_number, @Field("order_number") String farm_followed_status, @Field("auth_key") String authKey);


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
    Call<AddressApiModel> editAddress(@Url String url, @Field("LoginId") String LoginId, @Field("address_id") String address_id,
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
    Call<SignUpApiModel> doUserSignUp(@Url String url, @Field("account_name") String name, @Field("account_mobile") String mobile, @Field("account_email") String email, @Field("account_password") String password, @Field("referral_code") String referral_code, @Field("account_type") Integer account_type, @Field("account_country") String account_country, @Field("account_state") String account_state, @Field("account_city") String account_city, @Field("account_address") String account_address, @Field("account_lat") String account_lat, @Field("account_long") String account_long, @Field("account_phone_code") String account_phone_code, @Field("device_id") String device_id, @Field("device_platform") String device_platform, @Field("auth_key") String authKey);

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
                                     @Part MultipartBody.Part cover_photo,
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
    Call<AddressApiModel> FARM_LIST_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                                  @Field("customer_lat") Double customer_lat,
                                                  @Field("customer_long") Double customer_long,
                                                  @Field("customer_full_address") String customer_full_address,
                                                  @Field("customer_city") String customer_city,
                                                  @Field("farm_type") String farm_type,
                                                  @Field("farm_service_type") String farm_service_type,
                                                  @Field("farm_category_id") String farm_category_id,
                                                  @Field("pageno") String pageno,
                                                  @Field("LoginId") String LoginId);

    @FormUrlEncoded
    @POST
    Call<AllOrderResponse> getNewOrdersList(@Url String url, @Field("LoginId") String LoginId,
                                            @Field("auth_key") String authKey, @Field("order_type_filter") String order_type_filter);

    @FormUrlEncoded
    @POST
    Call<AllOrderResponse> getOrderDetails(@Url String url, @Field("LoginId") String LoginId,
                                           @Field("auth_key") String authKey, @Field("order_number") String order_number);

    @FormUrlEncoded
    @POST
    Call<AllOrderResponse> orderAccept(@Url String url, @Field("LoginId") String LoginId,
                                       @Field("auth_key") String authKey, @Field("order_number") String order_number);

    @FormUrlEncoded
    @POST
    Call<BroadcastMessageResponse> getBroadcastMessage(@Url String url, @Field("LoginId") String LoginId,
                                                       @Field("auth_key") String authKey, @Field("farm_id") String farm_id);

    @FormUrlEncoded
    @POST
    Call<BroadcastMessageResponse> deleteBroadcastMessage(@Url String url, @Field("LoginId") String LoginId,
                                                          @Field("auth_key") String authKey, @Field("MessageID") String MessageID);

    @FormUrlEncoded
    @POST
    Call<BroadcastMessageResponse> publishBroadcastMessage(@Url String url,
                                                           @Field("LoginId") String LoginId,
                                                           @Field("auth_key") String authKey,
                                                           @Field("farm_id") String farm_id,
                                                           @Field("message_title") String message_title,
                                                           @Field("message_content") String message_content,
                                                           @Field("message_target") String message_target,
                                                           @Field("message_status") String message_status);

    @FormUrlEncoded
    @POST
    Call<BroadcastMessageResponse> editBroadcastMessage(@Url String url,
                                                        @Field("LoginId") String LoginId,
                                                        @Field("auth_key") String authKey,
                                                        @Field("farm_id") String farm_id,
                                                        @Field("message_title") String message_title,
                                                        @Field("message_content") String message_content,
                                                        @Field("message_target") String message_target,
                                                        @Field("message_status") String message_status,
                                                        @Field("MessageID") String MessageID);

    @FormUrlEncoded
    @POST
    Call<AllOrderResponse> orderDecline(@Url String url, @Field("LoginId") String LoginId,
                                        @Field("auth_key") String authKey, @Field("order_number") String order_number,
                                        @Field("order_decline_reason") String order_decline_reason);

    @FormUrlEncoded
    @POST
    Call<FarmListProductResponse> getFarmProductList(@Url String url, @Field("auth_key") String authKey, @Field("LoginId") String LoginId,
                                                     @Field("farm_id") String farmId);


    @FormUrlEncoded
    @POST
    Call<FarmListProductResponse> addToCart(@Url String url, @Field("auth_key") String authKey,
                                            @Field("farm_id") String farmId,
                                            @Field("LoginId") String LoginId,
                                            @Field("item_id") String item_id,
                                            @Field("item_quantity") String item_quantity,
                                            @Field("item_price") String item_price,
                                            @Field("item_size") String item_size,
                                            @Field("item_unit") String item_unit,
                                            @Field("order_type") String order_type);

    @FormUrlEncoded
    @POST
    Call<ApplyCouponResponse> APPLY_COUPON_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                                         @Field("farm_id") String farmId,
                                                         @Field("coupon_code") String couponCode,
                                                         @Field("subtotal_amount") double subTotalAmount);

    @FormUrlEncoded
    @POST
    Call<ReviewListResponse> REVIEWD_LIST_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey, @Field("LoginId") String loginId);

    @FormUrlEncoded
    @POST
    Call<AddressApiModel> farm_review_data(@Url String url, @Field("auth_key") String authKey, @Field("farm_id") String farm_id);

    @FormUrlEncoded
    @POST
    Call<TaxResponse> TAX_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                        @Field("farm_id") String farmId,
                                        @Field("delivery_distance") String deliveryDistance,
                                        @Field("order_type") String orderType,
                                        @Field("subtotal_amount") String subTotal);

    @FormUrlEncoded
    @POST
    Call<SubmitResponse> SUBMIT_RESPONSE_CALL(@Url String url, @Field("auth_key") String authKey,
                                              @Field("customer_long") String customer_long,
                                              @Field("customer_lat") String customer_lat,
                                              @Field("customer_postcode") String postcode,
                                              @Field("customer_city") String customer_city,
                                              @Field("customer_address") String customer_address,
                                              @Field("WalletPay") String WalletPay,
                                              @Field("order_type") String order_type,
                                              @Field("SpecialInstruction") String SpecialInstruction,
                                              @Field("delivery_time") String delivery_time,
                                              @Field("delivery_date") String delivery_date,
                                              @Field("discount_amount") String discount_amount,
                                              @Field("coupon_discount_amount") String coupon_discount_amount,
                                              @Field("Total_amount") String Total_amount,
                                              @Field("delivery_amount") String delivery_amount,
                                              @Field("service_tax_amount") String service_tax_amount,
                                              @Field("gst_tax_amount") String gst_tax_amount,
                                              @Field("subtotal") String subtotal,
                                              @Field("payment_type") String payment_type,
                                              @Field("address_id") String address_id,
                                              @Field("LoginId") String LoginId,
                                              @Field("instructions") String instructions,
                                              @Field("item_unit_type") String item_unit_type,
                                              @Field("strsizeid") String strsizeid,
                                              @Field("Price") String Price,
                                              @Field("Quantity") String Quantity,
                                              @Field("itemId") String itemId,
                                              @Field("payment_transaction_id") String payment_transaction_id,
                                              @Field("farm_id") String farm_id);


    @FormUrlEncoded
    @POST
    Call<SaveFarmListApiModel> getSavedFarmList(@Url String url, @Field("LoginId") String userId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<SaveUnsaveFarmApiModel> saveUnSaveFarm(@Url String url, @Field("LoginId") String userId, @Field("auth_key") String authKey, @Field("farm_id") String farmId, @Field("farm_favourite_status") int status, @Field("favourite_id") String favoriteId);

    @FormUrlEncoded
    @POST
    Call<CartListResponse> CART_LIST_RESPONSE_CALL(@Url String url,
                                                   @Field("auth_key") String authKey,
                                                   @Field("LoginId") String userId);

    @FormUrlEncoded
    @POST
    Call<FollowersApiModel> getFollowersList(@Url String url, @Field("LoginId") String userId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<IncreaseDecreaseApiModel> clearAllCartItems(@Url String url, @Field("auth_key") String authKey, @Field("LoginId") String userId);

    @FormUrlEncoded
    @POST
    Call<FollowUnFollowApiModel> followUnFollowFarm(@Url String url, @Field("LoginId") String userId, @Field("auth_key") String authKey, @Field("farm_id") String farmId, @Field("farm_followed_status") String status, @Field("followed_id") String followId);

    @FormUrlEncoded
    @POST
    Call<IncreaseDecreaseApiModel> INCREASE_DECREASE_API_MODEL_CALL(@Url String url, @Field("auth_key") String authKey,
                                                                    @Field("cart_id") String cart_id,
                                                                    @Field("option_type") String optionType);

    @FormUrlEncoded
    @POST
    Call<CustomerReviewListApiModel> customer_review_list(@Url String url, @Field("LoginId") String loginId, @Field("auth_key") String authKey);


    @FormUrlEncoded
    @POST
    Call<FarmReviewListApiModel> getFarmReviewList(@Url String url, @Field("farm_id") String loginId, @Field("auth_key") String authKey);


    @FormUrlEncoded
    @POST
    Call<FarmReviewedListApiModel> getFarmReviewedList(@Url String url, @Field("farm_id") String farmId, @Field("auth_key") String authKey, @Field("LoginId") String loginId);

    @FormUrlEncoded
    @POST
    Call<HomeSearchApiModel> doSearchProduct(@Url String url, @Field("auth_key") String authKey, @Field("search_text") String searchText, @Field("LoginId") String loginId);


    @Multipart
    @POST
    Call<SetupStoreApiModel> doSetupStore(@Url String url,
                                          @Part("store_type") RequestBody storeType,
                                          @Part("store_name") RequestBody storeName,
                                          @Part("store_address") RequestBody address,
                                          @Part("store_city") RequestBody city,
                                          @Part("store_state") RequestBody state,
                                          @Part("store_country") RequestBody country,
                                          @Part("store_post_code") RequestBody postCode,
                                          @Part("order_type") RequestBody orderType,
                                          @Part("pickup_minimum_amount") RequestBody pickUpMinimumOrderAmount,
                                          @Part("pickup_message") RequestBody pickUpMsg,
                                          @Part("delivery_map_location_area") RequestBody mapLocation,
                                          @Part("delivery_location_distance") RequestBody radius,
                                          @Part("delivery_charge") RequestBody deliveryCharge,
                                          @Part("delivery_additional_charge") RequestBody additionalCharge,
                                          @Part("delivery_minimum_amount") RequestBody deliveryMinimumAmount,
                                          @Part("delivery_message") RequestBody deliveryMsg,
                                          @Part("company_type") RequestBody companyType,
                                          @Part("document_type_1") RequestBody documentType,
                                          @Part("LoginId") RequestBody loginId,
                                          @Part("auth_key") RequestBody authKey,
                                          @Part("store_logo") MultipartBody.Part logo,
                                          MultipartBody.Part docOneFront,
                                          MultipartBody.Part docOneBack,
                                          MultipartBody.Part docTwoFront,
                                          MultipartBody.Part docTwoBack);


    @FormUrlEncoded
    @POST
    Call<HomeSearchApiModel> doSearchProduct(@Url String url, @Field("auth_key") String authKey, @Field("search_text") String searchText, @Field("LoginId") String loginId);


    @Multipart
    @POST
    Call<SetupStoreApiModel> doSetupStore(@Url String url,
                                          @Part("store_type") RequestBody storeType,
                                          @Part("store_name") RequestBody storeName,
                                          @Part("store_address") RequestBody address,
                                          @Part("store_city") RequestBody city,
                                          @Part("store_state") RequestBody state,
                                          @Part("store_country") RequestBody country,
                                          @Part("store_post_code") RequestBody postCode,
                                          @Part("order_type") RequestBody orderType,
                                          @Part("pickup_minimum_amount") RequestBody pickUpMinimumOrderAmount,
                                          @Part("pickup_message") RequestBody pickUpMsg,
                                          @Part("delivery_map_location_area") RequestBody mapLocation,
                                          @Part("delivery_location_distance") RequestBody radius,
                                          @Part("delivery_charge") RequestBody deliveryCharge,
                                          @Part("delivery_additional_charge") RequestBody additionalCharge,
                                          @Part("delivery_minimum_amount") RequestBody deliveryMinimumAmount,
                                          @Part("delivery_message") RequestBody deliveryMsg,
                                          @Part("company_type") RequestBody companyType,
                                          @Part("document_type_1") RequestBody documentType,
                                          @Part("LoginId") RequestBody loginId,
                                          @Part("auth_key") RequestBody authKey,
                                          @Part("store_latitude") RequestBody lat,
                                          @Part("store_longitude") RequestBody lng,
                                          @Part MultipartBody.Part logo,
                                          @Part MultipartBody.Part docOneFront,
                                          @Part MultipartBody.Part docOneBack,
                                          @Part MultipartBody.Part docTwoFront,
                                          @Part MultipartBody.Part docTwoBack);


    @Multipart
    @POST
    Call<AddProductApiResponseModel> addProduct(@Url String url,
                                                @Part("product_name") RequestBody productName,
                                                @Part("product_quanity") RequestBody quantity,
                                                @Part("product_unit") RequestBody unit,
                                                @Part("product_category_id") RequestBody categoryId,
                                                @Part("product_unit_price") RequestBody unitPrice,
                                                @Part("product_sales_price") RequestBody salesPrice,
                                                @Part("product_note") RequestBody note,
                                                @Part("farm_id") RequestBody farmId,
                                                @Part("LoginId") RequestBody loginId,
                                                @Part("auth_key") RequestBody authKey,
                                                @Part @Nullable MultipartBody.Part productImage);

    @FormUrlEncoded
    @POST
    Call<ProductListApiModel> getProductList(@Url String url, @Field("LoginId") String loginId, @Field("farm_id") String farmId, @Field("auth_key") String authKey);


    @FormUrlEncoded
    @POST
    Call<CouponListApiModel> getCouponList(@Url String url, @Field("LoginId") String loginId, @Field("farm_id") String farmId, @Field("auth_key") String authKey);

    @FormUrlEncoded
    @POST
    Call<AddCouponApiModel> addCoupon(@Url String url,
                                      @Field("coupon_code") String couponCode,
                                      @Field("discount_type") String discountType,
                                      @Field("discount_amount") String discountAmount,
                                      @Field("discount_minimum_order") String discountMinOrder,
                                      @Field("term_condition") String termsCondition,
                                      @Field("start_date") String startData,
                                      @Field("expire_date") String endDate,
                                      @Field("CouponID") String couponId,
                                      @Field("farm_id") String farmId,
                                      @Field("LoginId") String loginId,
                                      @Field("auth_key") String authKey
    );


    @FormUrlEncoded
    @POST
    Call<AddCouponApiModel> EditCoupon(@Url String url,
                                       @Field("coupon_code") String couponCode,
                                       @Field("discount_type") String discountType,
                                       @Field("discount_amount") String discountAmount,
                                       @Field("discount_minimum_order") String discountMinOrder,
                                       @Field("term_condition") String termsCondition,
                                       @Field("start_date") String startData,
                                       @Field("expire_date") String endDate,
                                       @Field("CouponID") String couponId,
                                       @Field("farm_id") String farmId,
                                       @Field("LoginId") String loginId,
                                       @Field("auth_key") String authKey
    );


    @FormUrlEncoded
    @POST
    Call<DeleteCouponApiModel> deleteCoupon(@Url String url, @Field("CouponID") String couponId, @Field("auth_key") String authKey);


    @FormUrlEncoded
    @POST
    Call<DeleteProductApiModel> deleteProduct(@Url String url, @Field("ProductID") String productId, @Field("auth_key") String authKey);

}


