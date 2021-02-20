package com.farmers.buyers.modules.cart.myCart.model.applyCoupon;

import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ApplyCouponResponse {
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
    private ApplyCouponData data;


    public String getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public ApplyCouponData getData() {
        return data;
    }
}
