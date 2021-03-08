package com.farmers.seller.modules.ourOrders.model;

import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrderResponse {
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
        @SerializedName("OrderList")
        private List<OrderListData> getOrderList;

        public List<OrderListData> getGetOrderList() {
            return getOrderList;
        }
    }

    public static class OrderListData {

        public String address_id;
        public String vendor_country;
        public String vendor_state;
        public String vendor_city;
        public String vendor_address;
        public String vendor_lat;
        public String vendor_long;
        public String landmark;
        public String address_title;
        public String address_postcode;
        public String account_phone_number;
        public String current_date;
        public String month_name;
        public String customer_email;
        public String customer_mobile;
        public String customer_address;
        public String day_name;
        public String order_id;
        public String order_date;
        public String order_time;
        public String subtotal;
        public String Total_amount;
        public String gst_tax_amount;
        public String service_tax_amount;
        public String package_fee_amount;
        public String delivery_amount;
        public String coupon_discount_amount;
        public String discount_amount;
        public String order_status_msg;
        public String farm_name;
        public String farm_logo;
        public String order_number;
        public String order_type;
        public String review_status;
        public String order_status_close;
        public String order_status_color_code;
        public String current_time;
        public String customer_name;
        public String farm_address;
        public List<RecordList> OrderRecordList;
    }
}
