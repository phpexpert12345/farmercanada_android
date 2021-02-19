package com.farmers.buyers.modules.profile.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 03:46
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionMenuItems implements RecyclerViewListItem {
    String title;
    int icon;
    int color;
    ProfileOptionsGridItem gridItem;


    public MyProfileOptionMenuItems(String title, int icon, int color, ProfileOptionsGridItem gridItem) {
        this.title = title;
        this.icon = icon;
        this.color = color;
        this.gridItem = gridItem;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public ProfileOptionsGridItem getGridItem() {
        return gridItem;
    }

    @Override
    public int getViewType() {
        return CardConstant.PROFILE_OPTION_MENU_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
