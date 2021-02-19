package com.farmers.buyers.modules.address.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressApiModel {

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

        @SerializedName("AddressList")
        private List<AddressListData> allDataModels;

        public List<AddressListData> getAllDataModels() {
            return allDataModels;
        }

        public void setAllDataModels(List<AddressListData> allDataModels) {
            this.allDataModels = allDataModels;
        }
    }

    public static class AddressListData implements RecyclerViewListItem {

        private String address_id;
        private String vendor_country;
        private String vendor_state;
        private String vendor_city;
        private String vendor_address;
        private String vendor_lat;
        private String vendor_long;
        private String landmark;
        private String address_title;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getVendor_country() {
            return vendor_country;
        }

        public void setVendor_country(String vendor_country) {
            this.vendor_country = vendor_country;
        }

        public String getVendor_state() {
            return vendor_state;
        }

        public void setVendor_state(String vendor_state) {
            this.vendor_state = vendor_state;
        }

        public String getVendor_city() {
            return vendor_city;
        }

        public void setVendor_city(String vendor_city) {
            this.vendor_city = vendor_city;
        }

        public String getVendor_address() {
            return vendor_address;
        }

        public void setVendor_address(String vendor_address) {
            this.vendor_address = vendor_address;
        }

        public String getVendor_lat() {
            return vendor_lat;
        }

        public void setVendor_lat(String vendor_lat) {
            this.vendor_lat = vendor_lat;
        }

        public String getVendor_long() {
            return vendor_long;
        }

        public void setVendor_long(String vendor_long) {
            this.vendor_long = vendor_long;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getAddress_title() {
            return address_title;
        }

        public void setAddress_title(String address_title) {
            this.address_title = address_title;
        }

        @Override
        public int getViewType() {
            return CardConstant.MY_CART_ADDRESS_ADAPTER;
        }

        @Override
        public Object getUnique() {
            return this;
        }

    }
}
