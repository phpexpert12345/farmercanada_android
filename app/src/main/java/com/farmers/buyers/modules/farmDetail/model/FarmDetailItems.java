package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:44
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailItems implements RecyclerViewListItem {
    String farmName;
    String farmAddress;
    String rating;
    String hostedBy;
    String farm_opening_hours;
    String farm_estimate_delivery_time;
    String farm_followed_status;
    String farm_delivery_radius_text;
    String farm_hosted_by;

    public FarmDetailItems(String farmName, String farmAddress, String rating, String hostedBy, String farm_opening_hours, String
            farm_estimate_delivery_time, String farm_followed_status, String farm_delivery_radius_text, String farm_hosted_by) {
        this.farmName = farmName;
        this.farmAddress = farmAddress;
        this.rating = rating;
        this.hostedBy = hostedBy;
        this.farm_opening_hours = farm_opening_hours;
        this.farm_estimate_delivery_time = farm_estimate_delivery_time;
        this.farm_followed_status = farm_followed_status;
        this.farm_delivery_radius_text = farm_delivery_radius_text;
        this.farm_hosted_by = farm_hosted_by;
    }

    public FarmDetailItems() {

    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHostedBy() {
        return hostedBy;
    }

    public void setHostedBy(String hostedBy) {
        this.hostedBy = hostedBy;
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

    public String getFarm_hosted_by() {
        return farm_hosted_by;
    }

    public void setFarm_hosted_by(String farm_hosted_by) {
        this.farm_hosted_by = farm_hosted_by;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
