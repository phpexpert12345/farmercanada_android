package com.farmers.seller.modules.setupSellerAccount;

import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.model.StoreDeliveryRangeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreTransformer {

    public static List<StoreDeliveryRangeItems> getDeliveryRangeItem() {
        List<StoreDeliveryRangeItems> items = new ArrayList<>();
        items.add(new StoreDeliveryRangeItems("5KM"));
        items.add(new StoreDeliveryRangeItems("10KM"));
        items.add(new StoreDeliveryRangeItems("15KM"));
        items.add(new StoreDeliveryRangeItems("20KM"));
        items.add(new StoreDeliveryRangeItems("25KM"));
        items.add(new StoreDeliveryRangeItems("30KM"));
        return items;
    }
}
