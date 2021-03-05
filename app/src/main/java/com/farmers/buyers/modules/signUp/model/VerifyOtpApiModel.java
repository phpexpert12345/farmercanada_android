package com.farmers.buyers.modules.signUp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 17:15
 * mohammadsajjad679@gmail.com
 */

public class VerifyOtpApiModel {

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
    private OtpData data;

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

    public OtpData getData() {
        return data;
    }

    public void setData(OtpData data) {
        this.data = data;
    }


    public static class OtpData {

        @SerializedName("LoginId")
        @Expose
        private String loginId;
        @SerializedName("login_name")
        @Expose
        private String loginName;
        @SerializedName("login_email")
        @Expose
        private String loginEmail;
        @SerializedName("account_type_name")
        @Expose
        private String accountTypeName;
        @SerializedName("account_type")
        @Expose
        private String accountType;
        @SerializedName("login_phone")
        @Expose
        private String loginPhone;
        @SerializedName("login_phone_code")
        @Expose
        private String loginPhoneCode;
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

        public String getLoginId() {
            return loginId;
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

        public String getAccountTypeName() {
            return accountTypeName;
        }

        public void setAccountTypeName(String accountTypeName) {
            this.accountTypeName = accountTypeName;
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

