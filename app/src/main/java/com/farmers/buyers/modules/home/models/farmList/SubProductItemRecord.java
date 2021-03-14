package com.farmers.buyers.modules.home.models.farmList;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/16/2021.
 */
public class SubProductItemRecord {

    private String farm_id;
    private String farm_name;
    private String farm_address;
    private String store_state_name;
    private String store_city_name;
    private String farm_latitude;
    private String farm_longitude;
    private String farm_postcode;
    private Integer rating_avg;
    private String farm_type;
    private String farm_service_type;
    private String farm_opening_hours;
    private String farm_opening_status;
    private String farm_hosted_by;
    private Integer farm_hosted_phone_code_by;
    private String farm_hosted_mobile_number_by;
    private String farm_hosted_email_by;
    private String farm_followed_status;
    private String farm_payment_cash_allow;
    private String farm_payment_credit_debit_allow;
    private String farm_payment_paypal_allow;
    private String farm_payment_wallet_allow;
    private String farm_delivery_charge;
    private String farm_minimum_delivery_charge;
    private Object farm_minimum_pickup_charge;
    private String farm_additional_delivery_charge;
    private Integer farm_delivery_radius;
    private String farm_delivery_radius_text;
    private String farm_about_us;
    private String form_type_name;
    private String farm_logo;
    private String farm_cover_photo;
    private String service_type_name;
    private String farm_favourite_status;
    public String farm_estimate_delivery_time;
    private String favourite_id;
    private String followed_id;

    public String getDelivery_available() {
        return delivery_available;
    }

    public void setDelivery_available(String delivery_available) {
        this.delivery_available = delivery_available;
    }

    public String getPickup_available() {
        return pickup_available;
    }

    public void setPickup_available(String pickup_available) {
        this.pickup_available = pickup_available;
    }

    private String delivery_available;
    private String pickup_available;


    public String getFarmId() {
        return farm_id;
    }

    public String getFarmName() {
        return farm_name;
    }

    public String getFarmAddress() {
        return farm_address;
    }

    public String getStoreStateName() {
        return store_state_name;
    }

    public String getStoreCityName() {
        return store_city_name;
    }

    public String getFarmLatitude() {
        return farm_latitude;
    }

    public String getFarmLongitude() {
        return farm_longitude;
    }

    public String getFarmPostcode() {
        return farm_postcode;
    }

    public Integer getRatingAvg() {
        return rating_avg;
    }

    public String getFarmType() {
        return farm_type;
    }

    public String getFarmServiceType() {
        return farm_service_type;
    }

    public String getFarmOpeningHours() {
        return farm_opening_hours;
    }

    public String getFarmOpeningStatus() {
        return farm_opening_status;
    }

    public String getFarmHostedBy() {
        return farm_hosted_by;
    }

    public Integer getFarmHostedPhoneCodeBy() {
        return farm_hosted_phone_code_by;
    }

    public String getFarmHostedMobileNumberBy() {
        return farm_hosted_mobile_number_by;
    }

    public String getFarmHostedEmailBy() {
        return farm_hosted_email_by;
    }

    public String getFarmFollowedStatus() {
        return farm_followed_status;
    }

    public String getFarmPaymentCashAllow() {
        return farm_payment_cash_allow;
    }

    public String getFarmPaymentCreditDebitAllow() {
        return farm_payment_credit_debit_allow;
    }

    public String getFarmPaymentPaypalAllow() {
        return farm_payment_paypal_allow;
    }

    public String getFarmPaymentWalletAllow() {
        return farm_payment_wallet_allow;
    }

    public String getFarmDeliveryCharge() {
        return farm_delivery_charge;
    }

    public String getFarmMinimumDeliveryCharge() {
        return farm_minimum_delivery_charge;
    }

    public Object getFarmMinimumPickupCharge() {
        return farm_minimum_pickup_charge;
    }

    public String getFarmAdditionalDeliveryCharge() {
        return farm_additional_delivery_charge;
    }

    public Integer getFarmDeliveryRadius() {
        return farm_delivery_radius;
    }

    public String getFarmDeliveryRadiusText() {
        return farm_delivery_radius_text;
    }

    public String getFarmAboutUs() {
        return farm_about_us;
    }

    public String getFormTypeName() {
        return farm_type;
    }

    public String getFarmLogo() {
        return farm_logo;
    }

    public String getFarmCoverPhoto() {
        return farm_cover_photo;
    }

    public String getServiceTypeName() {
        return service_type_name;
    }

    public String getFarmFavouriteStatus() {
        return farm_favourite_status;
    }

    public String getFavouriteId() {
        return favourite_id;
    }

    public String getFollowedId() {
        return followed_id;
    }
}
