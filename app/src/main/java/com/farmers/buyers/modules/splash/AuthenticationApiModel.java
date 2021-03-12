
package com.farmers.buyers.modules.splash;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AuthenticationApiModel {

    @SerializedName("data")
    private List<AuthenticationData> mData;
    @SerializedName("status_code")
    private int mStatusCode;
    @SerializedName("status_message")
    private String mStatusMessage;

    public List<AuthenticationData> getData() {
        return mData;
    }

    public void setData(List<AuthenticationData> data) {
        mData = data;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        mStatusMessage = statusMessage;
    }


    public static class AuthenticationData {

        @SerializedName("auth_key")
        private String mAuthKey;


        @SerializedName("GOOGLE_MAP_KEY")
        private String googleMapKey;

        @SerializedName("currency_code")
        private String currencyCode;

        public String getGoogleMapKey() {
            return googleMapKey;
        }

        public void setGoogleMapKey(String googleMapKey) {
            this.googleMapKey = googleMapKey;
        }

        public String getAuthKey() {
            return mAuthKey;
        }

        public void setAuthKey(String authKey) {
            mAuthKey = authKey;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }

}
