package com.farmers.buyers.modules.followers.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 19:11
 * mohammadsajjad679@gmail.com
 */

public class FollowersItems implements RecyclerViewListItem {
    private String imageUri;
    private String name;
    private String distance;
    private String isFollowing;
    private String farmId;

    public FollowersItems(String imageUri, String name, String distance, String isFollowing, String farmId) {
        this.imageUri = imageUri;
        this.name = name;
        this.distance = distance;
        this.isFollowing = isFollowing;
        this.farmId = farmId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getFollowing() {
        return isFollowing;
    }

    public String getFarmId() {
        return farmId;
    }

    @Override
    public int getViewType() {
        return CardConstant.FOLLOWERS_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
