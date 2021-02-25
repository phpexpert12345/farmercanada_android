package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:37
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderListItem implements RecyclerViewListItem {

    public String address;
    public String image;
    public String coverImage;
    public String followStatus;
    public String followed_id;

    public FarmDetailHeaderListItem(String address, String image, String coverImage, String followStatus, String followed_id) {
        this.address = address;
        this.image = image;
        this.coverImage = coverImage;
        this.followStatus = followStatus;
        this.followed_id = followed_id;
    }

    public String getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(String followed_id) {
        this.followed_id = followed_id;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_HEADER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
