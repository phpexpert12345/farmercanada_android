package com.farmers.buyers.modules.ratingAndReview.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class FarmReviewedListApiModel {

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
    private FarmReviewedData data;

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

    public FarmReviewedData getData() {
        return data;
    }

    public void setData(FarmReviewedData data) {
        this.data = data;
    }

    public class FarmReviewedData {

        @SerializedName("FarmReviewList")
        @Expose
        private List<FarmReviewList> farmReviewList = null;

        public List<FarmReviewList> getFarmReviewList() {
            return farmReviewList;
        }

        public void setFarmReviewList(List<FarmReviewList> farmReviewList) {
            this.farmReviewList = farmReviewList;
        }

    }

    public class FarmReviewList {

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
        @SerializedName("login_name")
        @Expose
        private String loginName;
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

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getFarmLogo() {
            return farmLogo;
        }

        public void setFarmLogo(String farmLogo) {
            this.farmLogo = farmLogo;
        }

    }

}