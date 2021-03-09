package com.farmers.buyers.modules.home;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.MultipleTextItems;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetablesListItem;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.HomeCategoryItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.modules.home.search.model.HomeSearchCategoryList;
import com.farmers.buyers.modules.home.search.model.HomeSearchSubProductItemsRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:18
 * mohammadsajjad679@gmail.com
 */

public class HomeTransformer {

    public static HomeHeaderItem getHeaderItems(String name, String address, String account_type_name, String account_type) {
        return new HomeHeaderItem(name, address, account_type_name, account_type);
    }

    public static HomeSearchListItem getSearchItems() {
        return new HomeSearchListItem("Mohammad sajjad", "Noida");
    }

    public static List<HomeListItem> getHomeFarmListItem(List<SubProductItemRecord> listItem) {
        List<HomeListItem> items = new ArrayList<>();
        for (int i = 0; i < listItem.size(); i++) {
            SubProductItemRecord data = listItem.get(i);
            items.add(new HomeListItem(data.getFarmName(),
                    data.getFarmDeliveryRadiusText(),
                    data.getRatingAvg().toString(),
                    data.getFarmFavouriteStatus(),
                    data.getFarmId(),
                    data.getFarmCoverPhoto(),
                    data.getFarmLogo(),
                    data.getFarmLatitude(),
                    data.getFarmLongitude(), data.getFarmAddress(), data.getFarmOpeningHours(),
                    data.farm_estimate_delivery_time, data.getFarmHostedBy(),
                    data.getFarmOpeningStatus(),
                    data.getFarmFavouriteStatus(),
                    data.getFavouriteId(), data.getFarmFollowedStatus(),
                    data.getFormTypeName(), data.getFollowedId(),data.getDelivery_available(),data.getPickup_available()));
        }
        return items;
    }

    public static HomeCategoryListItem getCategoryList(List<AllDataModel.Data> data) {
        List<RecyclerViewListItem> categoryItems = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            categoryItems.add(new HomeCategoryItems(data.get(i).getCategory_name(), data.get(i).getCategory_photo(),
                    R.drawable.ic_category_one, data.get(i).getCategory_id()));
        }
        return new HomeCategoryListItem(categoryItems);
    }

    public static HomeTopOffersListItems getTopOffers(List<AllDataModel.BannerData> data) {
        List<HomeTopOffersItem> categoryItems = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            categoryItems.add(new HomeTopOffersItem(data.get(i).getBanner_photo(), ""));
        }
        return new HomeTopOffersListItems(categoryItems);
    }

    public static HomeHeaderItem getHeaderItems() {
        return new HomeHeaderItem("Aman kumar", "address", "Type", "");
    }

    public static HomeTopOffersListItems getTopOffers() {
        List<HomeTopOffersItem> categoryItems = new ArrayList<>();
        categoryItems.add(new HomeTopOffersItem("vegetables", ""));
        categoryItems.add(new HomeTopOffersItem("grocery", ""));
        categoryItems.add(new HomeTopOffersItem("meet", ""));
        categoryItems.add(new HomeTopOffersItem("wine", ""));
        return new HomeTopOffersListItems(categoryItems);
    }

    public static HomeFilterListItems getFilterItems() {
        List<RecyclerViewListItem> filterItems = new ArrayList<>();
        filterItems.add(new MultipleTextItems("Categories", true));
        return new HomeFilterListItems(filterItems);
    }

    public static FarmDetailsVegetablesListItem transformApiModelToHomeSearchItems(List<HomeSearchSubProductItemsRecord> apiModel) {
        List<RecyclerViewListItem> item = new ArrayList<>();
        for (int i = 0; apiModel.size() > i; i++) {
            item.add(new FarmDetailsVegetableItems(apiModel.get(i).getProductImages(),
                    apiModel.get(i).getProductName(),
                    apiModel.get(i).getProductSalesPrice(),
                    apiModel.get(i).getPriceUnitType(),
                    true,
                    apiModel.get(i).getShoppingItemQuantity(),
                    apiModel.get(i).getProductCode(),
                    apiModel.get(i).getProductStock(),
                    apiModel.get(i).getShoppingItemAvailable(),
                    String.valueOf(apiModel.get(i).getCategoryId()),
                    String.valueOf(apiModel.get(i).getCartId()),
                    String.valueOf(apiModel.get(i).getProductID()),
                    String.valueOf(apiModel.get(i).getPriceUnitType()),
                    String.valueOf(apiModel.get(i).getFarmId())));
        }
        return new FarmDetailsVegetablesListItem(item);
    }
}
