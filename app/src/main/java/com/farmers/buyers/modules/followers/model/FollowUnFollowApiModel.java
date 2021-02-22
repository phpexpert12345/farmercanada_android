package com.farmers.buyers.modules.followers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowUnFollowApiModel {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("data")
    @Expose
    private FollowersApiModel.Data data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public FollowersApiModel.Data getData() {
        return data;
    }

    public void setData(FollowersApiModel.Data data) {
        this.data = data;
    }
}
