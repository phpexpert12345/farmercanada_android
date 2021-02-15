package com.farmers.buyers.modules.signUp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpApiModel implements Serializable {

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("status_message")
    private String statusMessage;

    @SerializedName("data")
    private List<SignUpData> data;

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

    public void setData(List<SignUpData> data) {
        this.data = data;
    }

    public List<SignUpData> getData() {
        return data;
    }


    public static class SignUpData {

        @SerializedName("LoginId")
        private String loginId;

        @SerializedName("login_phone")
        private String loginPhone;

        @SerializedName("login_phone_code")
        private String loginPhoneCode;

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

        public String getLoginPhoneCode() {
            return loginPhoneCode;
        }
    }
}