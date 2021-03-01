package com.farmers.buyers.modules.ratingAndReview.customerReview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class CustomerReviewListApiModel {

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
    private CustomerReviewData data;

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

    public CustomerReviewData getData() {
        return data;
    }

    public void setData(CustomerReviewData data) {
        this.data = data;
    }

    public class CustomerReviewData {

        @SerializedName("ReviewList")
        @Expose
        private List<ReviewList> reviewList = null;

        public List<ReviewList> getReviewList() {
            return reviewList;
        }

        public void setReviewList(List<ReviewList> reviewList) {
            this.reviewList = reviewList;
        }

    }
    public class ReviewList {

        @SerializedName("review_id")
        @Expose
        private String reviewId;
        @SerializedName("total_rating")
        @Expose
        private float totalRating;
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

        public String getReviewId() {
            return reviewId;
        }

        public void setReviewId(String reviewId) {
            this.reviewId = reviewId;
        }

        public float getTotalRating() {
            return totalRating;
        }

        public void setTotalRating(float totalRating) {
            this.totalRating = totalRating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getFarmName() {
            return farmName;
        }

        public void setFarmName(String farmName) {
            this.farmName = farmName;
        }

        public String getFarmLogo() {
            return farmLogo;
        }

        public void setFarmLogo(String farmLogo) {
            this.farmLogo = farmLogo;
        }

    }

}
