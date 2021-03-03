
package com.farmers.buyers.modules.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginApiModel {

    @SerializedName("status")
    private boolean status;

    @SerializedName("status_message")
    private String status_message;

    @SerializedName("status_code")
    private String status_code;

    @SerializedName("data")
    private Data mData;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public static class Data {


        public String getLoginId() {
            return loginId;
        }

        @SerializedName("LoginId")
        @Expose
        private String loginId;
        @SerializedName("Mobile_OTP")
        @Expose
        private String mobileOTP;
        @SerializedName("login_name")
        @Expose
        private String loginName;
        @SerializedName("login_email")
        @Expose
        private String loginEmail;
        @SerializedName("login_phone")
        @Expose
        private String loginPhone;
        @SerializedName("login_phone_code")
        @Expose
        private String loginPhoneCode;
        @SerializedName("farm_id")
        @Expose
        private String farmId;
        @SerializedName("account_type_name")
        @Expose
        private String accountTypeName;
        @SerializedName("store_setup_status")
        @Expose
        private String storeSetupStatus;
        @SerializedName("account_type")
        @Expose
        private String accountType;
        @SerializedName("wallet_amount")
        @Expose
        private String walletAmount;
        @SerializedName("Total_following")
        @Expose
        private String totalFollowing;
        @SerializedName("Total_followers")
        @Expose
        private String totalFollowers;
        @SerializedName("Total_Inbox_Message")
        @Expose
        private String totalInboxMessage;
        @SerializedName("login_photo")
        @Expose
        private String loginPhoto;


        public String getMobileOTP() {
            return mobileOTP;
        }

        public void setMobileOTP(String mobileOTP) {
            this.mobileOTP = mobileOTP;
        }


        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getLoginEmail() {
            return loginEmail;
        }

        public void setLoginEmail(String loginEmail) {
            this.loginEmail = loginEmail;
        }

        public String getFarmId() {
            return farmId;
        }

        public void setFarmId(String farmId) {
            this.farmId = farmId;
        }

        public String getAccountTypeName() {
            return accountTypeName;
        }

        public void setAccountTypeName(String accountTypeName) {
            this.accountTypeName = accountTypeName;
        }

        public String getStoreSetupStatus() {
            return storeSetupStatus;
        }

        public void setStoreSetupStatus(String storeSetupStatus) {
            this.storeSetupStatus = storeSetupStatus;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getLoginPhone() {
            return loginPhone;
        }

        public void setLoginPhone(String loginPhone) {
            this.loginPhone = loginPhone;
        }

        public String getLoginPhoneCode() {
            return loginPhoneCode;
        }

        public void setLoginPhoneCode(String loginPhoneCode) {
            this.loginPhoneCode = loginPhoneCode;
        }

        public String getWalletAmount() {
            return walletAmount;
        }

        public void setWalletAmount(String walletAmount) {
            this.walletAmount = walletAmount;
        }

        public String getTotalFollowing() {
            return totalFollowing;
        }

        public void setTotalFollowing(String totalFollowing) {
            this.totalFollowing = totalFollowing;
        }

        public String getTotalFollowers() {
            return totalFollowers;
        }

        public void setTotalFollowers(String totalFollowers) {
            this.totalFollowers = totalFollowers;
        }

        public String getTotalInboxMessage() {
            return totalInboxMessage;
        }

        public void setTotalInboxMessage(String totalInboxMessage) {
            this.totalInboxMessage = totalInboxMessage;
        }

        public String getLoginPhoto() {
            return loginPhoto;
        }

        public void setLoginPhoto(String loginPhoto) {
            this.loginPhoto = loginPhoto;
        }

    }
}
