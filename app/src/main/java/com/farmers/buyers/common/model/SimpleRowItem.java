package com.farmers.buyers.common.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 13:13
 * mohammadsajjad679@gmail.com
 */

public class SimpleRowItem implements RecyclerViewListItem {
    String title;
    int leftImageUri;
    int rightImageUri;
    ProfileItem itemType;


    public SimpleRowItem(String title, int leftImageUri, int rightImageUri, ProfileItem itemType) {
        this.title = title;
        this.leftImageUri = leftImageUri;
        this.rightImageUri = rightImageUri;
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public int getLeftImageUri() {
        return leftImageUri;
    }

    public int getRightImageUri() {
        return rightImageUri;
    }

    public ProfileItem getItemType() {
        return itemType;
    }

    @Override
    public int getViewType() {
        return CardConstant.SIMPLE_ROW_ITEM;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
