package com.farmers.buyers.modules.home.models;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 22:40
 * mohammadsajjad679@gmail.com
 */

public class HomeListItem implements RecyclerViewListItem {

    String id;
    String farmName;
    String distance;
    String rating;
    String isSaved;
    String coverImage;
    String farmImage;
    Double farmLat;
    Double farmLong;
    String favoriteId;
    String isFollowing;
    String followId;

    public String farm_id;
    public String farm_name;
    public String farm_address;
    public String rating_avg;
    public String farm_opening_hours;
    public String farm_estimate_delivery_time;
    public String farm_hosted_by;
    public String farm_opening_status;
    public String farm_favourite_status;
    public String favourite_id;
    public String farm_followed_status;
    public String farm_delivery_radius_text;
    public String form_type_name;
    public String farm_logo;
    public String farm_cover_photo;

    public String getDelivery_available() {
        return delivery_available;
    }

    public void setDelivery_available(String delivery_available) {
        this.delivery_available = delivery_available;
    }

    public String getPickup_available() {
        return pickup_available;
    }

    public void setPickup_available(String pickup_available) {
        this.pickup_available = pickup_available;
    }

    public String delivery_available;
    public String pickup_available;


    public String getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(String followed_id) {
        this.followed_id = followed_id;
    }

    public String followed_id;

    public void setId(String id) {
        this.id = id;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setIsSaved(String isSaved) {
        this.isSaved = isSaved;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setFarmImage(String farmImage) {
        this.farmImage = farmImage;
    }

    public void setFarmLat(Double farmLat) {
        this.farmLat = farmLat;
    }

    public void setFarmLong(Double farmLong) {
        this.farmLong = farmLong;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getFarm_name() {
        return farm_name;
    }

    public void setFarm_name(String farm_name) {
        this.farm_name = farm_name;
    }

    public String getFarm_address() {
        return farm_address;
    }

    public void setFarm_address(String farm_address) {
        this.farm_address = farm_address;
    }

    public String getRating_avg() {
        return rating_avg;
    }

    public void setRating_avg(String rating_avg) {
        this.rating_avg = rating_avg;
    }

    public String getFarm_opening_hours() {
        return farm_opening_hours;
    }

    public void setFarm_opening_hours(String farm_opening_hours) {
        this.farm_opening_hours = farm_opening_hours;
    }

    public String getFarm_estimate_delivery_time() {
        return farm_estimate_delivery_time;
    }

    public void setFarm_estimate_delivery_time(String farm_estimate_delivery_time) {
        this.farm_estimate_delivery_time = farm_estimate_delivery_time;
    }

    public String getFarm_hosted_by() {
        return farm_hosted_by;
    }

    public void setFarm_hosted_by(String farm_hosted_by) {
        this.farm_hosted_by = farm_hosted_by;
    }

    public String getFarm_opening_status() {
        return farm_opening_status;
    }

    public void setFarm_opening_status(String farm_opening_status) {
        this.farm_opening_status = farm_opening_status;
    }

    public String getFarm_favourite_status() {
        return farm_favourite_status;
    }

    public void setFarm_favourite_status(String farm_favourite_status) {
        this.farm_favourite_status = farm_favourite_status;
    }

    public String getFavourite_id() {
        return favourite_id;
    }

    public void setFavourite_id(String favourite_id) {
        this.favourite_id = favourite_id;
    }

    public String getFarm_followed_status() {
        return farm_followed_status;
    }

    public void setFarm_followed_status(String farm_followed_status) {
        this.farm_followed_status = farm_followed_status;
    }

    public String getFarm_delivery_radius_text() {
        return farm_delivery_radius_text;
    }

    public void setFarm_delivery_radius_text(String farm_delivery_radius_text) {
        this.farm_delivery_radius_text = farm_delivery_radius_text;
    }

    public String getForm_type_name() {
        return form_type_name;
    }

    public void setForm_type_name(String form_type_name) {
        this.form_type_name = form_type_name;
    }

    public String getFarm_logo() {
        return farm_logo;
    }

    public void setFarm_logo(String farm_logo) {
        this.farm_logo = farm_logo;
    }

    public String getFarm_cover_photo() {
        return farm_cover_photo;
    }

    public void setFarm_cover_photo(String farm_cover_photo) {
        this.farm_cover_photo = farm_cover_photo;
    }

    public HomeListItem(String farmName, String distance, String rating, String isSaved, String id, String coverImage,
                        String farmImage, Double farmLat, Double farmLong, String farm_address, String farm_opening_hours, String farm_estimate_delivery_time,
                        String farm_hosted_by, String farm_opening_status, String farm_favourite_status,
                        String favourite_id, String farm_followed_status, String form_type_name, String followed_id,String delivery_available,String pickup_available) {
        this.farmName = farmName;
        this.distance = distance;
        this.rating = rating;
        this.isSaved = isSaved;
        this.id = id;
        this.coverImage = coverImage;
        this.farmImage = farmImage;
        this.farmLat = farmLat;
        this.farmLong = farmLong;
        this.farm_address = farm_address;
        this.farm_opening_hours = farm_opening_hours;
        this.farm_estimate_delivery_time = farm_estimate_delivery_time;
        this.farm_hosted_by = farm_hosted_by;
        this.farm_opening_status = farm_opening_status;
        this.farm_favourite_status = farm_favourite_status;
        this.favourite_id = favourite_id;
        this.farm_followed_status = farm_followed_status;
        this.form_type_name = form_type_name;
        this.followed_id = followed_id;
        this.delivery_available=delivery_available;
        this.pickup_available=pickup_available;
    }

    public HomeListItem(String farmName, String distance, String rating, String isSaved, String id, String coverImage, String farmImage, Double farmLat, Double farmLong, String favoriteId, String isFollowing, String followId) {
        this.farmName = farmName;
        this.distance = distance;
        this.rating = rating;
        this.isSaved = isSaved;
        this.id = id;
        this.coverImage = coverImage;
        this.farmImage = farmImage;
        this.farmLat = farmLat;
        this.farmLong = farmLong;
        this.favoriteId = favoriteId;
        this.isFollowing = isFollowing;
        this.farm_followed_status=isFollowing;
        this.followId = followId;
        this.followed_id=followId;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getDistance() {
        return distance;
    }

    public String getRating() {
        return rating;
    }

    public String getSaved() {
        return isSaved;
    }

    public String getId() {
        return id;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getFarmImage() {
        return farmImage;
    }

    public String getIsSaved() {
        return isSaved;
    }

    public Double getFarmLat() {
        return farmLat;
    }

    public Double getFarmLong() {
        return farmLong;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getIsFollowing() {
        return isFollowing;
    }

    public String getFollowId() {
        return followId;
    }

    @Override
    public int getViewType() {
        return CardConstant.HOME_FARM_LIST_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
