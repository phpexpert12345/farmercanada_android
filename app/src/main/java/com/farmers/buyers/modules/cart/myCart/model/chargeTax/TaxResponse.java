package com.farmers.buyers.modules.cart.myCart.model.chargeTax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/18/2021.
 */
public class TaxResponse implements Serializable {

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
    private TaxData taxData;

    public String getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public TaxData getTaxData() {
        return taxData;
    }
}
