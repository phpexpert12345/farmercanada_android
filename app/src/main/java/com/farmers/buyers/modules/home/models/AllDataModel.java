package com.farmers.buyers.modules.home.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllDataModel {

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

    public Data getmData() {
        return mData;
    }

    public void setmData(Data mData) {
        this.mData = mData;
    }

    public static class Data {

        @SerializedName("CategoryList")
        public List<Data> CategoryList;

        @SerializedName("TopBannerList")
        public List<BannerData> getBannerList;

        @SerializedName("WalletList")
        public List<BannerData> WalletList;

        private String category_id;
        private String category_name;
        private String category_photo;

        public String LoginId;
        public String login_name;
        public String login_email;
        public String account_type_name;
        public String wallet_amount;
        public String login_photo;
        public String account_type;
        public String login_phone;
        public String login_phone_code;

        public String getCategory_id() {
            return category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public String getCategory_photo() {
            return category_photo;
        }
    }

    public static class BannerData {

        private String offer_id;
        private String offer_title;
        private String banner_photo;

        private String wallet_amount;
        private String transaction_status;
        private String added_date;
        private String wallet_added_time;
        private String wallet_status;
        private String wallet_message;

        public String getWallet_amount() {
            return wallet_amount;
        }

        public String getWalletAddedTime() {
            return wallet_added_time;
        }


        public String getTransaction_status() {
            return transaction_status;
        }

        public String getAdded_date() {
            return added_date;
        }

        public String getWallet_status() {
            return wallet_status;
        }

        public String getWallet_message() {
            return wallet_message;
        }

        public String getOffer_id() {
            return offer_id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public String getBanner_photo() {
            return banner_photo;
        }
    }
}
