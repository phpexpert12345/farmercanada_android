package com.farmers.buyers.modules.followers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowersApiModel {


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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("FarmFollowedList")
        @Expose
        private List<FarmFollowedList> farmFollowedList = null;

        public List<FarmFollowedList> getFarmFollowedList() {
            return farmFollowedList;
        }

        public void setFarmFollowedList(List<FarmFollowedList> farmFollowedList) {
            this.farmFollowedList = farmFollowedList;
        }

    }

    public class FarmFollowedList {

        @SerializedName("favourite_id")
        @Expose
        private String favouriteId;
        @SerializedName("farm_followed_status")
        @Expose
        private String farmFollowedStatus;
        @SerializedName("farm_name")
        @Expose
        private String farmName;
        @SerializedName("farm_logo")
        @Expose
        private String farmLogo;

        public String getFarm_id() {
            return farm_id;
        }

        public void setFarm_id(String farm_id) {
            this.farm_id = farm_id;
        }

        @SerializedName("farm_id")
        @Expose
        private String farm_id;

        public int getFollowed_id() {
            return followed_id;
        }

        public void setFollowed_id(int followed_id) {
            this.followed_id = followed_id;
        }

        @SerializedName("followed_id")
        @Expose
        private int followed_id;

        public String getFavouriteId() {
            return favouriteId;
        }

        public void setFavouriteId(String favouriteId) {
            this.favouriteId = favouriteId;
        }

        public String getFarmFollowedStatus() {
            return farmFollowedStatus;
        }

        public void setFarmFollowedStatus(String farmFollowedStatus) {
            this.farmFollowedStatus = farmFollowedStatus;
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
