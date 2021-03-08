package com.farmers.buyers.modules.seller.coupon.list.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohammad sajjad on 07-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class CouponListApiModel {


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
    private CouponData data;

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

    public CouponData getData() {
        return data;
    }

    public void setData(CouponData data) {
        this.data = data;
    }

    public static class CouponData {

        @SerializedName("CouponCodeListSeller")
        @Expose
        public List<CouponCodeListSeller> couponCodeListSeller = null;

        public List<CouponCodeListSeller> getCouponCodeListSeller() {
            return couponCodeListSeller;
        }

        public void setCouponCodeListSeller(List<CouponCodeListSeller> couponCodeListSeller) {
            this.couponCodeListSeller = couponCodeListSeller;
        }
    }
}
