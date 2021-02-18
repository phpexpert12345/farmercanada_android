
package com.farmers.buyers.modules.login.model;

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


        @SerializedName("Mobile_OTP")
        private String Mobile_OTP;

        @SerializedName("LoginId")
        private String LoginId;

        @SerializedName("login_name")
        private String login_name;

        @SerializedName("message")
        private String mMessage;

        @SerializedName("login_email")
        private String login_email;

        @SerializedName("token")
        private String mToken;

        public String getMobile_OTP() {
            return Mobile_OTP;
        }

        public void setMobile_OTP(String mobile_OTP) {
            Mobile_OTP = mobile_OTP;
        }

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            mMessage = message;
        }

        public String getToken() {
            return mToken;
        }

        public String getLoginId() {
            return LoginId;
        }

        public void setLoginId(String loginId) {
            LoginId = loginId;
        }

        public String getLogin_name() {
            return login_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public String getLogin_email() {
            return login_email;
        }

        public void setLogin_email(String login_email) {
            this.login_email = login_email;
        }

        public void setToken(String token) {
            mToken = token;
        }
    }
}
