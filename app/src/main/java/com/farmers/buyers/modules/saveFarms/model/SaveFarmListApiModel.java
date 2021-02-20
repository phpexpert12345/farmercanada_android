package com.farmers.buyers.modules.saveFarms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveFarmListApiModel {
    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("FarmFavouriteList")
        @Expose
        private List<FarmFavouriteList> farmFavouriteList = null;

        public List<FarmFavouriteList> getFarmFavouriteList() {
            return farmFavouriteList;
        }

        public void setFarmFavouriteList(List<FarmFavouriteList> farmFavouriteList) {
            this.farmFavouriteList = farmFavouriteList;
        }

    }
    public class FarmFavouriteList {

        @SerializedName("favourite_id")
        @Expose
        private Integer favouriteId;
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
        @SerializedName("farm_favourite_status")
        @Expose
        private String farmFavouriteStatus;
        @SerializedName("farm_followed_status")
        @Expose
        private String farmFollowedStatus;
        @SerializedName("followed_id")
        @Expose
        private String followedId;
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
        @SerializedName("service_type_name")
        @Expose
        private String serviceTypeName;
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

        public Integer getFavouriteId() {
            return favouriteId;
        }

        public void setFavouriteId(Integer favouriteId) {
            this.favouriteId = favouriteId;
        }

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getFarmName() {
            return farmName;
        }

        public void setFarmName(String farmName) {
            this.farmName = farmName;
        }

        public String getFarmAddress() {
            return farmAddress;
        }

        public void setFarmAddress(String farmAddress) {
            this.farmAddress = farmAddress;
        }

        public String getStoreStateName() {
            return storeStateName;
        }

        public void setStoreStateName(String storeStateName) {
            this.storeStateName = storeStateName;
        }

        public String getStoreCityName() {
            return storeCityName;
        }

        public void setStoreCityName(String storeCityName) {
            this.storeCityName = storeCityName;
        }

        public String getFarmLatitude() {
            return farmLatitude;
        }

        public void setFarmLatitude(String farmLatitude) {
            this.farmLatitude = farmLatitude;
        }

        public String getFarmLongitude() {
            return farmLongitude;
        }

        public void setFarmLongitude(String farmLongitude) {
            this.farmLongitude = farmLongitude;
        }

        public String getFarmPostcode() {
            return farmPostcode;
        }

        public void setFarmPostcode(String farmPostcode) {
            this.farmPostcode = farmPostcode;
        }

        public Integer getRatingAvg() {
            return ratingAvg;
        }

        public void setRatingAvg(Integer ratingAvg) {
            this.ratingAvg = ratingAvg;
        }

        public String getFarmType() {
            return farmType;
        }

        public void setFarmType(String farmType) {
            this.farmType = farmType;
        }

        public String getFarmServiceType() {
            return farmServiceType;
        }

        public void setFarmServiceType(String farmServiceType) {
            this.farmServiceType = farmServiceType;
        }

        public String getFarmOpeningHours() {
            return farmOpeningHours;
        }

        public void setFarmOpeningHours(String farmOpeningHours) {
            this.farmOpeningHours = farmOpeningHours;
        }

        public String getFarmOpeningStatus() {
            return farmOpeningStatus;
        }

        public void setFarmOpeningStatus(String farmOpeningStatus) {
            this.farmOpeningStatus = farmOpeningStatus;
        }

        public String getFarmHostedBy() {
            return farmHostedBy;
        }

        public void setFarmHostedBy(String farmHostedBy) {
            this.farmHostedBy = farmHostedBy;
        }

        public Integer getFarmHostedPhoneCodeBy() {
            return farmHostedPhoneCodeBy;
        }

        public void setFarmHostedPhoneCodeBy(Integer farmHostedPhoneCodeBy) {
            this.farmHostedPhoneCodeBy = farmHostedPhoneCodeBy;
        }

        public String getFarmHostedMobileNumberBy() {
            return farmHostedMobileNumberBy;
        }

        public void setFarmHostedMobileNumberBy(String farmHostedMobileNumberBy) {
            this.farmHostedMobileNumberBy = farmHostedMobileNumberBy;
        }

        public String getFarmHostedEmailBy() {
            return farmHostedEmailBy;
        }

        public void setFarmHostedEmailBy(String farmHostedEmailBy) {
            this.farmHostedEmailBy = farmHostedEmailBy;
        }

        public String getFarmFavouriteStatus() {
            return farmFavouriteStatus;
        }

        public void setFarmFavouriteStatus(String farmFavouriteStatus) {
            this.farmFavouriteStatus = farmFavouriteStatus;
        }

        public String getFarmFollowedStatus() {
            return farmFollowedStatus;
        }

        public void setFarmFollowedStatus(String farmFollowedStatus) {
            this.farmFollowedStatus = farmFollowedStatus;
        }

        public String getFollowedId() {
            return followedId;
        }

        public void setFollowedId(String followedId) {
            this.followedId = followedId;
        }

        public String getFarmPaymentCashAllow() {
            return farmPaymentCashAllow;
        }

        public void setFarmPaymentCashAllow(String farmPaymentCashAllow) {
            this.farmPaymentCashAllow = farmPaymentCashAllow;
        }

        public String getFarmPaymentCreditDebitAllow() {
            return farmPaymentCreditDebitAllow;
        }

        public void setFarmPaymentCreditDebitAllow(String farmPaymentCreditDebitAllow) {
            this.farmPaymentCreditDebitAllow = farmPaymentCreditDebitAllow;
        }

        public String getFarmPaymentPaypalAllow() {
            return farmPaymentPaypalAllow;
        }

        public void setFarmPaymentPaypalAllow(String farmPaymentPaypalAllow) {
            this.farmPaymentPaypalAllow = farmPaymentPaypalAllow;
        }

        public String getFarmPaymentWalletAllow() {
            return farmPaymentWalletAllow;
        }

        public void setFarmPaymentWalletAllow(String farmPaymentWalletAllow) {
            this.farmPaymentWalletAllow = farmPaymentWalletAllow;
        }

        public String getFarmDeliveryCharge() {
            return farmDeliveryCharge;
        }

        public void setFarmDeliveryCharge(String farmDeliveryCharge) {
            this.farmDeliveryCharge = farmDeliveryCharge;
        }

        public String getFarmMinimumDeliveryCharge() {
            return farmMinimumDeliveryCharge;
        }

        public void setFarmMinimumDeliveryCharge(String farmMinimumDeliveryCharge) {
            this.farmMinimumDeliveryCharge = farmMinimumDeliveryCharge;
        }

        public Object getFarmMinimumPickupCharge() {
            return farmMinimumPickupCharge;
        }

        public void setFarmMinimumPickupCharge(Object farmMinimumPickupCharge) {
            this.farmMinimumPickupCharge = farmMinimumPickupCharge;
        }

        public String getFarmAdditionalDeliveryCharge() {
            return farmAdditionalDeliveryCharge;
        }

        public void setFarmAdditionalDeliveryCharge(String farmAdditionalDeliveryCharge) {
            this.farmAdditionalDeliveryCharge = farmAdditionalDeliveryCharge;
        }

        public Integer getFarmDeliveryRadius() {
            return farmDeliveryRadius;
        }

        public void setFarmDeliveryRadius(Integer farmDeliveryRadius) {
            this.farmDeliveryRadius = farmDeliveryRadius;
        }

        public String getFarmDeliveryRadiusText() {
            return farmDeliveryRadiusText;
        }

        public void setFarmDeliveryRadiusText(String farmDeliveryRadiusText) {
            this.farmDeliveryRadiusText = farmDeliveryRadiusText;
        }

        public String getServiceTypeName() {
            return serviceTypeName;
        }

        public void setServiceTypeName(String serviceTypeName) {
            this.serviceTypeName = serviceTypeName;
        }

        public String getFarmAboutUs() {
            return farmAboutUs;
        }

        public void setFarmAboutUs(String farmAboutUs) {
            this.farmAboutUs = farmAboutUs;
        }

        public String getFormTypeName() {
            return formTypeName;
        }

        public void setFormTypeName(String formTypeName) {
            this.formTypeName = formTypeName;
        }

        public String getFarmLogo() {
            return farmLogo;
        }

        public void setFarmLogo(String farmLogo) {
            this.farmLogo = farmLogo;
        }

        public String getFarmCoverPhoto() {
            return farmCoverPhoto;
        }

        public void setFarmCoverPhoto(String farmCoverPhoto) {
            this.farmCoverPhoto = farmCoverPhoto;
        }

    }

}
