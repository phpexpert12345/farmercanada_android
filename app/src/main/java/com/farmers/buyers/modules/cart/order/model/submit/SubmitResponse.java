package com.farmers.buyers.modules.cart.order.model.submit;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/19/2021.
 */
public class SubmitResponse implements Serializable {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public List<Object> getData() {
        return data;
    }
}
