package com.farmers.buyers.modules.cart.myCart.model.increaseDecrease;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/22/2021.
 */
public class IncreaseDecreaseApiModel {
    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("status_message")
    private String status_message;


    public String getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getStatus_message() {
        return status_message;
    }
}
