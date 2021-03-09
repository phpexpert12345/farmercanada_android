package com.farmers.seller.modules.broadcastMessage.model;

import com.farmers.seller.modules.ourOrders.model.RecordList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BroadcastMessageResponse {
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
        @SerializedName("BoardCastMessgageListSeller")
        private List<BroadcastListData> getBroadcastList;

        public List<BroadcastListData> getBroadcastList() {
            return getBroadcastList;
        }
    }

    public static class BroadcastListData {

        public String MessageID;
        public String farm_id;
        public String message_title;
        public String message_content;
        public String publish_on;
        public String target_audience;
        public String message_status;
        public String message_status_name;
    }
}
