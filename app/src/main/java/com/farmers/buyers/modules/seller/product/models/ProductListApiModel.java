package com.farmers.buyers.modules.seller.product.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ProductListApiModel {

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
    private ProductData data;

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

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }


    public static class ProductData {

        @SerializedName("subProductItemsRecordSeller")
        @Expose
        private List<SubProductItemsRecordSeller> subProductItemsRecordSeller = null;

        public List<SubProductItemsRecordSeller> getSubProductItemsRecordSeller() {
            return subProductItemsRecordSeller;
        }

        public void setSubProductItemsRecordSeller(List<SubProductItemsRecordSeller> subProductItemsRecordSeller) {
            this.subProductItemsRecordSeller = subProductItemsRecordSeller;
        }
    }
}



