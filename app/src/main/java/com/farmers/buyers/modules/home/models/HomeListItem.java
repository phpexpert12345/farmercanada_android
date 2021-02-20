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

    public HomeListItem(String farmName, String distance, String rating, String isSaved, String id, String coverImage, String farmImage) {
        this.farmName = farmName;
        this.distance = distance;
        this.rating = rating;
        this.isSaved = isSaved;
        this.id = id;
        this.coverImage = coverImage;
        this.farmImage = farmImage;
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

    @Override
    public int getViewType() {
        return CardConstant.HOME_FARM_LIST_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
