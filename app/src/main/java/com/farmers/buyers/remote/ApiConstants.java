package com.farmers.buyers.remote;

import android.net.Uri;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 18:40
 * mohammadsajjad679@gmail.com
 */

public class ApiConstants {
    public static final String BASE_URL = "http://farmercanada.com/buyer_api/";
    // Delete user from DataBase using MobileNumber http://farmercanada.com/buyer_api/phpexpert_customer_delete.php?mobile_number=9001262048

    public static Uri IMAGE_PATH_USER;
    public static Uri IMAGE_PATH_COVER;
    public static final String LOGIN = "phpexpert_login.php";
    public static final String SIGN_UP = "buyer/sign-up";
    public static final String VERIFY_REGISTRATION_OTP = "phpexpert_account_mobile_verify.php";
    public static final String REGIS = "phpexpert_account_register.php";
    public static final String RESEND_OTP = "phpexpert_account_resned_otp.php";
    public static final String VERIFY_OTP = "verifyotp";
    public static final String FORGOT_PASSWORD = "phpexpert_account_forgot_password.php";
    public static final String FORGOT_CHANGE_PASSWORD = "phpexpert_account_reset_password.php";
    public static final String CATEGORY_LIST = "phpexpert_category_list.php";
    public static final String OFFER_LIST = "phpexpert_offers_list.php";
    public static final String ADDRESS_LIST = "phpexpert_customer_address_list.php";
    public static final String REFER_AND_EARN = "phpexpert_customer_earn_share.php";
    public static final String DELETE_ADDRESS = "phpexpert_customer_address_delete.php";
    public static final String ADD_ADDRESS = "phpexpert_customer_address_add.php";
    public static final String EDIT_ADDRESS = "phpexpert_customer_address_edit.php";
    public static final String CHANGE_PASSWORD = "phpexpert_change_password.php";
    public static final String USER_INFORMATION = "phpexpert_customer_information.php";
    public static final String WALLET_HISTORY = "phpexpert_customer_wallet_history.php";
    public static final String ADD_MONEY = "phpexpert_customer_wallet_money_add.php";
    public static final String EDIT_PROFILE = "phpexpert_edit_customer_profile.php";
    public static final String SUB_ORDER_LIST = "phpexpert_customer_order_list.php";
    public static final String ORDER_DETAILS = "phpexpert_customer_order_detail.php";
    public static final String AUTHENTICATION = "phpexpert_account_auth_key.php";
    public static final String CHANGE_USER_TYPE = "phpexpert_account_change_buyer_seller.php";
    public static final String ADD_TO_CART = "phpexpert_product_cart_add.php";
    public static final String CLEAR_CART_ITEMS = "phpexpert_delete_product_cart.php";
    public static final String GET_DATE_DATA = "phpexpert_checkout_date_list.php";
    public static final String GET_TIME_BY_DATE_DATA = "phpexpert_checkout_time_list.php";
    public static final String GET_CUSTOMER_REVIEW_LIST = "phpexpert_customer_review_list.php";
    public static final String GET_FARM_REVIEW_LIST = "phpexpert_farm_review_list.php";
    public static final String GET_FARM_REVIEWED_LIST = "phpexpert_farm_review_customer_list.php";
    public static final String GET_PAYMENT_KEY="phpexpert_payment_key.php";
    public static final String STRIPE_PAY="phpexpert_payment_intent_generate.php";
    public static final String GET_PAYMENT_KEY_WALLET="phpexpert_payment_key_wallet.php";
    public static final String STRIPE_PAY_WALLET="phpexpert_payment_intent_generate_wallet.php";
    public static final String SEARCH_PRODUCT_ITEM = "phpexpert_all_farm_product_search.php";


    //Ganesh working
    public static final String FARM_LIST_URL = "phpexpert_farm_list.php";
    public static final String ALL_FARM_PRODUCT_LIST_URL = "phpexpert_all_farm_product_list.php";
    public static final String APPLY_COUPON_URL = "phpexpert_coupon_apply.php";
    public static final String SERVICE_AND_TAX_URL = "phpexpert_service_charget_get.php";
    public static final String REVIEW_AND_RATING_API = "phpexpert_customer_review_list.php";
    public static final String FARM_REVIEW = "phpexpert_farm_review_list.php";
    public static final String SUBMIT_ORDER_URL = "phpexpert_payment_android_submit.php";
    public static final String SAVE_FARM_LIST = "phpexpert_customer_favourite_list.php";
    public static final String SAVE_UN_SAVE_FARM = "phpexpert_customer_favourite.php";
    public static final String CUSTOMER_PRODUCT_CART_LIST_URL = "phpexpert_customer_Product_Cart_list.php";
    public static final String FOLLOWERS_LIST = "phpexpert_customer_unfollow_follow_list.php";
    public static final String FOLLOW_UNFOLLOW_USER = "phpexpert_customer_follow_unfollow.php";
    public static final String INCREADE_DECREASE_API_URL = "phpexpert_product_cart_increase_decrease.php";


    // TODO Seller Section

    public static final String SETUP_STORE = "phpexpert_store_configuration_process.php";
}