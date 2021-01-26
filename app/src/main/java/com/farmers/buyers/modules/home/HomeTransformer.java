package com.farmers.buyers.modules.home;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.HomeCategoryItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeHeaderListItem;
import com.farmers.buyers.modules.home.models.HomeListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:18
 * mohammadsajjad679@gmail.com
 */

public class HomeTransformer {

    public static HomeHeaderListItem getHeaderItems() {
        return new HomeHeaderListItem("Mohammad sajjad", "Noida");
    }

    public static List<HomeListItem> getHomeFarmListItem() {
        List<HomeListItem> listItem = new ArrayList<>();
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9"));
        return listItem;
    }

    public static HomeCategoryListItem getCategoryList() {
        List<RecyclerViewListItem> categoryItems = new ArrayList<>();
        categoryItems.add(new HomeCategoryItems("vegetables"));
        categoryItems.add(new HomeCategoryItems("grocery"));
        categoryItems.add(new HomeCategoryItems("meet"));
        categoryItems.add(new HomeCategoryItems("wine"));
        return new HomeCategoryListItem(categoryItems);

    }
}
