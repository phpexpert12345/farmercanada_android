package com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ReviewList implements Serializable, RecyclerViewListItem {

    @SerializedName("review_id")
    @Expose
    private String reviewId;
    @SerializedName("total_rating")
    @Expose
    private String totalRating;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("farm_logo")
    @Expose
    private String farmLogo;
    @Override
    public int getViewType() {
        return CardConstant.REVIEW_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getTotalRating() {
        return totalRating;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getFarmLogo() {
        return farmLogo;
    }
}
