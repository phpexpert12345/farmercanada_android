package com.farmers.buyers.modules.home.models.farmList;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/16/2021.
 */
public class SubProductItemRecord implements Serializable, RecyclerViewListItem {


    @SerializedName("farm_id")
    @Expose
    private String farmId;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("farm_address")
    @Expose
    private String farmAddress;
    @SerializedName("store_state_name")
    @Expose
    private String storeStateName;
    @SerializedName("store_city_name")
    @Expose
    private String storeCityName;
    @SerializedName("farm_latitude")
    @Expose
    private String farmLatitude;
    @SerializedName("farm_longitude")
    @Expose
    private String farmLongitude;
    @SerializedName("farm_postcode")
    @Expose
    private String farmPostcode;
    @SerializedName("rating_avg")
    @Expose
    private Integer ratingAvg;
    @SerializedName("farm_type")
    @Expose
    private String farmType;
    @SerializedName("farm_service_type")
    @Expose
    private String farmServiceType;
    @SerializedName("farm_opening_hours")
    @Expose
    private String farmOpeningHours;
    @SerializedName("farm_opening_status")
    @Expose
    private String farmOpeningStatus;
    @SerializedName("farm_hosted_by")
    @Expose
    private String farmHostedBy;
    @SerializedName("farm_hosted_phone_code_by")
    @Expose
    private Integer farmHostedPhoneCodeBy;
    @SerializedName("farm_hosted_mobile_number_by")
    @Expose
    private String farmHostedMobileNumberBy;
    @SerializedName("farm_hosted_email_by")
    @Expose
    private String farmHostedEmailBy;
    @SerializedName("farm_followed_status")
    @Expose
    private String farmFollowedStatus;
    @SerializedName("farm_payment_cash_allow")
    @Expose
    private String farmPaymentCashAllow;
    @SerializedName("farm_payment_credit_debit_allow")
    @Expose
    private String farmPaymentCreditDebitAllow;
    @SerializedName("farm_payment_paypal_allow")
    @Expose
    private String farmPaymentPaypalAllow;
    @SerializedName("farm_payment_wallet_allow")
    @Expose
    private String farmPaymentWalletAllow;
    @SerializedName("farm_delivery_charge")
    @Expose
    private String farmDeliveryCharge;
    @SerializedName("farm_minimum_delivery_charge")
    @Expose
    private String farmMinimumDeliveryCharge;
    @SerializedName("farm_minimum_pickup_charge")
    @Expose
    private Object farmMinimumPickupCharge;
    @SerializedName("farm_additional_delivery_charge")
    @Expose
    private String farmAdditionalDeliveryCharge;
    @SerializedName("farm_delivery_radius")
    @Expose
    private Integer farmDeliveryRadius;
    @SerializedName("farm_delivery_radius_text")
    @Expose
    private String farmDeliveryRadiusText;
    @SerializedName("farm_about_us")
    @Expose
    private String farmAboutUs;
    @SerializedName("form_type_name")
    @Expose
    private String formTypeName;
    @SerializedName("farm_logo")
    @Expose
    private String farmLogo;
    @SerializedName("farm_cover_photo")
    @Expose
    private String farmCoverPhoto;
    @SerializedName("service_type_name")
    @Expose
    private String serviceTypeName;
    @SerializedName("farm_favourite_status")
    @Expose
    private String farmFavouriteStatus;


    public String getFarmId() {
        return farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public String getStoreStateName() {
        return storeStateName;
    }

    public String getStoreCityName() {
        return storeCityName;
    }

    public String getFarmLatitude() {
        return farmLatitude;
    }

    public String getFarmLongitude() {
        return farmLongitude;
    }

    public String getFarmPostcode() {
        return farmPostcode;
    }

    public Integer getRatingAvg() {
        return ratingAvg;
    }

    public String getFarmType() {
        return farmType;
    }

    public String getFarmServiceType() {
        return farmServiceType;
    }

    public String getFarmOpeningHours() {
        return farmOpeningHours;
    }

    public String getFarmOpeningStatus() {
        return farmOpeningStatus;
    }

    public String getFarmHostedBy() {
        return farmHostedBy;
    }

    public Integer getFarmHostedPhoneCodeBy() {
        return farmHostedPhoneCodeBy;
    }

    public String getFarmHostedMobileNumberBy() {
        return farmHostedMobileNumberBy;
    }

    public String getFarmHostedEmailBy() {
        return farmHostedEmailBy;
    }

    public String getFarmFollowedStatus() {
        return farmFollowedStatus;
    }

    public String getFarmPaymentCashAllow() {
        return farmPaymentCashAllow;
    }

    public String getFarmPaymentCreditDebitAllow() {
        return farmPaymentCreditDebitAllow;
    }

    public String getFarmPaymentPaypalAllow() {
        return farmPaymentPaypalAllow;
    }

    public String getFarmPaymentWalletAllow() {
        return farmPaymentWalletAllow;
    }

    public String getFarmDeliveryCharge() {
        return farmDeliveryCharge;
    }

    public String getFarmMinimumDeliveryCharge() {
        return farmMinimumDeliveryCharge;
    }

    public Object getFarmMinimumPickupCharge() {
        return farmMinimumPickupCharge;
    }

    public String getFarmAdditionalDeliveryCharge() {
        return farmAdditionalDeliveryCharge;
    }

    public Integer getFarmDeliveryRadius() {
        return farmDeliveryRadius;
    }

    public String getFarmDeliveryRadiusText() {
        return farmDeliveryRadiusText;
    }

    public String getFarmAboutUs() {
        return farmAboutUs;
    }

    public String getFormTypeName() {
        return formTypeName;
    }

    public String getFarmLogo() {
        return farmLogo;
    }

    public String getFarmCoverPhoto() {
        return farmCoverPhoto;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public String getFarmFavouriteStatus() {
        return farmFavouriteStatus;
    }


    @Override
    public int getViewType() {
        return CardConstant.HOME_FARM_LIST_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }


}
