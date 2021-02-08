package com.farmers.buyers.modules.home;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.MultipleTextItems;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.HomeCategoryItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:18
 * mohammadsajjad679@gmail.com
 */

public class HomeTransformer {

    public static HomeHeaderItem getHeaderItems() {
        return new HomeHeaderItem("Aman kumar", "address");
    }

    public static HomeSearchListItem getSearchItems() {
        return new HomeSearchListItem("Mohammad sajjad", "Noida");
    }

    public static List<HomeListItem> getHomeFarmListItem() {
        List<HomeListItem> listItem = new ArrayList<>();
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        listItem.add(new HomeListItem("Farm name","2 KM away from you","4.9", false));
        return listItem;
    }

    public static HomeCategoryListItem getCategoryList() {
        List<RecyclerViewListItem> categoryItems = new ArrayList<>();
        categoryItems.add(new HomeCategoryItems("vegetables", R.drawable.ic_category_one));
        categoryItems.add(new HomeCategoryItems("grocery", R.drawable.ic_category_two));
        categoryItems.add(new HomeCategoryItems("meet", R.drawable.ic_category_three));
        categoryItems.add(new HomeCategoryItems("wine", R.drawable.ic_category_four));
        return new HomeCategoryListItem(categoryItems);
    }

    public static HomeTopOffersListItems getTopOffers() {
        List<HomeTopOffersItem> categoryItems = new ArrayList<>();
        categoryItems.add(new HomeTopOffersItem("vegetables",""));
        categoryItems.add(new HomeTopOffersItem("grocery",""));
        categoryItems.add(new HomeTopOffersItem("meet",""));
        categoryItems.add(new HomeTopOffersItem("wine",""));
        return new HomeTopOffersListItems(categoryItems);
    }

    public static HomeFilterListItems getFilterItems() {
        List<RecyclerViewListItem> filterItems = new ArrayList<>();
        filterItems.add(new MultipleTextItems("Categories", true));
        filterItems.add(new MultipleTextItems("Subscribed", true));
        return new HomeFilterListItems(filterItems);
    }
}
