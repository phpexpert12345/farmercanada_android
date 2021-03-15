package com.farmers.seller.modules.workingHour.model;

import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkingHoursResponse {
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
       /* @SerializedName("OrderList")
        private List<AllOrderResponse.OrderListData> getOrderList;

        public List<AllOrderResponse.OrderListData> getGetOrderList() {
            return getOrderList;
        }*/
    }

}
