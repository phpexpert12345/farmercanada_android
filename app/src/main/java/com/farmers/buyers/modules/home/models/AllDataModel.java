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

    public static class Data implements RecyclerViewListItem {

        @SerializedName("CategoryList")
        public List<Data> CategoryList;

        private String offer_id;
        private String offer_title;
        private String banner_photo;

        private String category_id;
        private String category_name;
        private String category_photo;

        public String getOffer_id() {
            return offer_id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public String getBanner_photo() {
            return banner_photo;
        }

        public String getCategory_id() {
            return category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public String getCategory_photo() {
            return category_photo;
        }

        @Override
        public int getViewType() {
            return CardConstant.HOME_CATEGORY_LIST_ITEM_ADAPTER;
        }

        @Override
        public Object getUnique() {
            return this;
        }
    }
}
