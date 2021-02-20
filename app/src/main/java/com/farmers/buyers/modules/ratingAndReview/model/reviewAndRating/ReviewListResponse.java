package com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating;

import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ReviewListResponse implements Serializable {
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
    private ReviewData data;


    public String getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public ReviewData getData() {
        return data;
    }
}
