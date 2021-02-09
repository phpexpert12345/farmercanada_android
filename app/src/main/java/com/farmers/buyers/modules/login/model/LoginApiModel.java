
package com.farmers.buyers.modules.login.model;

import com.google.gson.annotations.SerializedName;

public class LoginApiModel {

    @SerializedName("data")
    private Data mData;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public static class Data {

        @SerializedName("message")
        private String mMessage;
        @SerializedName("token")
        private String mToken;

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            mMessage = message;
        }

        public String getToken() {
            return mToken;
        }

        public void setToken(String token) {
            mToken = token;
        }

    }
}
