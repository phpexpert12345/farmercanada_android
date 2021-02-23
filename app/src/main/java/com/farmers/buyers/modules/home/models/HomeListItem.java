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
        this.followId = followId;
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
