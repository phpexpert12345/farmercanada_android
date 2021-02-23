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

    public FarmDetailHeaderListItem(String address, String image, String coverImage) {
        this.address = address;
        this.image = image;
        this.coverImage = coverImage;
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
