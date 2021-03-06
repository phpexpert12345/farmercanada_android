package com.farmers.buyers.modules.farmDetail;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailHeaderListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsHeaderItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetablesListItem;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.SubProductItemsRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:14
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailTransformer {

    public static FarmDetailHeaderListItem getHeaderItems() {
        List<RecyclerViewListItem> item = new ArrayList<>();
        item.add(new FarmDetailsHeaderItems("https://meet.google.com/iyq-kict-oit"));
        item.add(new FarmDetailsHeaderItems("https://meet.google.com/iyq-kict-oit"));
        return new FarmDetailHeaderListItem(item);
    }

    public static FarmDetailItems getFarmDetailItems() {
        return new FarmDetailItems();
    }

    public static FarmDetailsVegetablesListItem getFarmDetailVegList(List<SubProductItemsRecord> subProductItems) {
        List<RecyclerViewListItem> item = new ArrayList<>();

        for (int i = 0; subProductItems.size() > i; i++) {
            item.add(new FarmDetailsVegetableItems(subProductItems.get(i).getProductImages(),
                    subProductItems.get(i).getProductName(), subProductItems.get(i).getProductSalesPrice(),
                    subProductItems.get(i).getPriceUnitType(), true));
        }
        return new FarmDetailsVegetablesListItem(item);
    }
}
