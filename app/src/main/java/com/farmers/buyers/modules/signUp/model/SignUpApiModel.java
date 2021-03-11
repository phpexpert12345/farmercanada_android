package com.farmers.buyers.modules.signUp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpApiModel implements Serializable {


    @SerializedName("status")
    private boolean status;

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("status_message")
    private String statusMessage;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @SerializedName("data")
    private SignUpData data;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setData(SignUpData data) {
        this.data = data;
    }

    public SignUpData getData() {
        return data;
    }


    public static class SignUpData {

        @SerializedName("LoginId")
        private String loginId;

        @SerializedName("login_phone")
        private String loginPhone;

        @SerializedName("login_phone_code")
        private String loginPhoneCode;

        @SerializedName("Mobile_OTP")
        private String Mobile_OTP;

        public String getAccountTypeName() {
            return accountTypeName;
        }

        public void setAccountTypeName(String accountTypeName) {
            this.accountTypeName = accountTypeName;
        }

        @SerializedName("account_type_name")
        private String accountTypeName;

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginPhone(String loginPhone) {
            this.loginPhone = loginPhone;
        }

        public String getLoginPhone() {
            return loginPhone;
        }

        public void setLoginPhoneCode(String loginPhoneCode) {
            this.loginPhoneCode = loginPhoneCode;
        }

        public String getMobile_OTP() {
            return Mobile_OTP;
        }

        public void setMobile_OTP(String mobile_OTP) {
            Mobile_OTP = mobile_OTP;
        }

        public String getLoginPhoneCode() {
            return loginPhoneCode;
        }
    }
}