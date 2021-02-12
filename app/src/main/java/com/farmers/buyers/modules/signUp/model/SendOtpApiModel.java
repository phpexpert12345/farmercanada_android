package com.farmers.buyers.modules.signUp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 15:52
 * mohammadsajjad679@gmail.com
 */

public class SendOtpApiModel {

    @SerializedName("status_code")
    private boolean statusCode;

    @SerializedName("status_message")
    private String statusMessage;

    @SerializedName("data")
    private List<Object> data;

    public void setStatusCode(boolean statusCode){
        this.statusCode = statusCode;
    }

    public boolean isStatusCode(){
        return statusCode;
    }

    public void setStatusMessage(String statusMessage){
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage(){
        return statusMessage;
    }

    public void setData(List<Object> data){
        this.data = data;
    }

    public List<Object> getData(){
        return data;
    }
}
