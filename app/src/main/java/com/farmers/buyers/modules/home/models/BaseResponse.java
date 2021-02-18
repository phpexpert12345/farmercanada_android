package com.farmers.buyers.modules.home.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/16/2021.
 */
public class BaseResponse implements Serializable {
    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;


    public String getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }


}
