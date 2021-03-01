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

    public static FarmDetailHeaderListItem getHeaderItems(String address, String image, String coverImage, String followStatus, String followed_id) {
        return new FarmDetailHeaderListItem(address, image, coverImage, followStatus, followed_id);
    }

    public static FarmDetailItems getFarmDetailItems(String farm_name, String farm_address, String rating_ang, String farm_opening_hours,
                                                     String farm_estimate_delivery_time, String farm_followed_status,
                                                     String farm_delivery_radius_text,
                                                     String farm_hosted_by, String farmImage, String farmId) {

        return new FarmDetailItems(farm_name, farm_address, rating_ang, farm_hosted_by,
                farm_opening_hours, farm_estimate_delivery_time, farm_followed_status,
                farm_delivery_radius_text, farm_hosted_by, farmImage, farmId);
    }

    public static FarmDetailItems getFarmDetailItems() {
        return new FarmDetailItems();
    }

    public static FarmDetailsVegetablesListItem getFarmDetailVegList(List<SubProductItemsRecord> subProductItems) {
        List<RecyclerViewListItem> item = new ArrayList<>();
        for (int i = 0; subProductItems.size() > i; i++) {
            item.add(new FarmDetailsVegetableItems(subProductItems.get(i).getProductImages(),
                    subProductItems.get(i).getProductName(),
                    subProductItems.get(i).getProductSalesPrice(),
                    subProductItems.get(i).getPriceUnitType(),
                    true,
                    subProductItems.get(i).shopping_item_quantity,
                    subProductItems.get(i).product_code,
                    subProductItems.get(i).product_stock,
                    subProductItems.get(i).shopping_item_available,
                    String.valueOf(subProductItems.get(i).getCategoryId()),
                    String.valueOf(subProductItems.get(i).cart_id),
                    String.valueOf(subProductItems.get(i).getProductID()),
                    String.valueOf(subProductItems.get(i).getPriceUnitType()),
                    String.valueOf(subProductItems.get(i).getFarmId())));
        }
        return new FarmDetailsVegetablesListItem(item);
    }
}