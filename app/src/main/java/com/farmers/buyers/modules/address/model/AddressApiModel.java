package com.farmers.buyers.modules.address.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

        @SerializedName("OrderList")
        private List<AddressListData> allOrderList;

        @SerializedName("subProductItemsRecord")
        private ArrayList<SubProductItemRecord> subProductItemRecords;

        public ArrayList<SubProductItemRecord> getSubProductItemRecords() {
            return subProductItemRecords;
        }

        public List<AddressListData> getAllDataModels() {
            return allDataModels;
        }

        public List<AddressListData> getAllOrderList() {
            return allOrderList;
        }

        @SerializedName("DateList")
        private List<AddressListData> allDateList;

        public List<AddressListData> getAllDateList() {
            return allDateList;
        }

        public void setAllDateList(List<AddressListData> allDateList) {
            this.allDateList = allDateList;
        }

        @SerializedName("TimeList")
        private List<AddressListData> allTimeList;

        public List<AddressListData> getAllTimeList() {
            return allTimeList;
        }

        public void setAllTimeList(List<AddressListData> allTimeList) {
            this.allTimeList = allTimeList;
        }
    }

    public static class AddressListData {

        private String address_id;
        private String vendor_country;
        private String vendor_state;
        private String vendor_city;
        private String vendor_address;
        private String vendor_lat;
        private String vendor_long;
        private String landmark;
        private String address_title;
        private String address_postcode;
        private String account_phone_number;
        public String current_date;
        public String month_name;
        public String day_name;
        private String order_id;
        private String order_date;
        private String order_time;
        private String Total_amount;
        private String order_status_msg;
        private String farm_name;
        private String farm_logo;
        private String order_number;
        private String order_type;
        private String review_status;
        private String order_status_close;
        private String order_status_color_code;
        public String current_time;

        @SerializedName("OrderRecordList")
        private List<AllRecordsData> allRecordList;

        public List<AllRecordsData> getAllRecordList() {
            return allRecordList;
        }

        public void setAllRecordList(List<AllRecordsData> allRecordList) {
            this.allRecordList = allRecordList;
        }

        public String getAddress_postcode() {
            return address_postcode;
        }

        public void setAddress_postcode(String address_postcode) {
            this.address_postcode = address_postcode;
        }

        public String getAccount_phone_number() {
            return account_phone_number;
        }

        public void setAccount_phone_number(String account_phone_number) {
            this.account_phone_number = account_phone_number;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getReview_status() {
            return review_status;
        }

        public void setReview_status(String review_status) {
            this.review_status = review_status;
        }

        public String getOrder_status_close() {
            return order_status_close;
        }

        public void setOrder_status_close(String order_status_close) {
            this.order_status_close = order_status_close;
        }

        public String getOrder_status_color_code() {
            return order_status_color_code;
        }

        public void setOrder_status_color_code(String order_status_color_code) {
            this.order_status_color_code = order_status_color_code;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getTotal_amount() {
            return Total_amount;
        }

        public void setTotal_amount(String total_amount) {
            Total_amount = total_amount;
        }

        public String getOrder_status_msg() {
            return order_status_msg;
        }

        public void setOrder_status_msg(String order_status_msg) {
            this.order_status_msg = order_status_msg;
        }

        public String getFarm_name() {
            return farm_name;
        }

        public void setFarm_name(String farm_name) {
            this.farm_name = farm_name;
        }

        public String getFarm_logo() {
            return farm_logo;
        }

        public void setFarm_logo(String farm_logo) {
            this.farm_logo = farm_logo;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

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

    }

    public static class AllRecordsData {

        private String item_id;
        private String product_name;
        private String product_code;
        private String product_description;
        private String product_images;
        private String item_quantity;
        private String item_price;
        private String item_unit;
        private String item_size;
        private String item_note;

        public String getItem_id() {
            return item_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public String getProduct_code() {
            return product_code;
        }

        public String getProduct_description() {
            return product_description;
        }

        public String getProduct_images() {
            return product_images;
        }

        public String getItem_quantity() {
            return item_quantity;
        }

        public String getItem_price() {
            return item_price;
        }

        public String getItem_unit() {
            return item_unit;
        }

        public String getItem_size() {
            return item_size;
        }

        public String getItem_note() {
            return item_note;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public void setProduct_images(String product_images) {
            this.product_images = product_images;
        }

        public void setItem_quantity(String item_quantity) {
            this.item_quantity = item_quantity;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public void setItem_unit(String item_unit) {
            this.item_unit = item_unit;
        }

        public void setItem_size(String item_size) {
            this.item_size = item_size;
        }

        public void setItem_note(String item_note) {
            this.item_note = item_note;
        }
    }
}

