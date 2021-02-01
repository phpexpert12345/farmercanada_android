package com.farmers.buyers.modules.followers.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 19:11
 * mohammadsajjad679@gmail.com
 */

public class FollowersItems implements RecyclerViewListItem {
    private int imageUri;
    private String name;
    private String distance;
    private Boolean isFollowing;

    public FollowersItems(int imageUri, String name, String distance, Boolean isFollowing) {
        this.imageUri = imageUri;
        this.name = name;
        this.distance = distance;
        this.isFollowing = isFollowing;
    }

    public int getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public Boolean getFollowing() {
        return isFollowing;
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
