package com.farmers.buyers.modules.ratingAndReview.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

public class ReviewListItem implements RecyclerViewListItem {
    private String name;
    private String image;
    private String ratingId;
    private float getRating;
    private String comment;
    private String date;
    private String orderNumber;

    public ReviewListItem(String name, String image, String ratingId, float getRating, String comment, String date, String orderNumber) {
        this.name = name;
        this.image = image;
        this.ratingId = ratingId;
        this.getRating = getRating;
        this.comment = comment;
        this.date = date;
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getRatingId() {
        return ratingId;
    }

    public float getGetRating() {
        return getRating;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public int getViewType() {
        return CardConstant.REVIEW_ITEMS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}