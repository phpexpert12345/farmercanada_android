package com.farmers.buyers.modules.seller.product;

import com.farmers.buyers.modules.seller.product.models.ProductListApiModel;
import com.farmers.buyers.modules.seller.product.models.ProductListItems;
import com.farmers.buyers.modules.seller.product.models.SubProductItemsRecordSeller;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class ProductListTransformer {

    public static List<ProductListItems> getProducts(ProductListApiModel apiData) {
        List<ProductListItems> items = new ArrayList<>();
        for (int i = 0; i < apiData.getData().getSubProductItemsRecordSeller().size(); i++) {
            SubProductItemsRecordSeller item = apiData.getData().getSubProductItemsRecordSeller().get(i);
            items.add(new ProductListItems(
                    item.getProductID(),
                    String.valueOf(item.getFarmId()),
                    String.valueOf(item.getCategoryId()),
                    item.getProductName(),
                    item.getProductCode(),
                    item.getProductStock(),
                    item.getCategoryName(),
                    item.getProductDescription(),
                    item.getProductImages(),
                    item.getDiscountProductAmount(),
                    item.getProductUnitPrice(),
                    item.getProductSalesPrice(),
                    item.getPriceUnitType()));
        }
        return items;
    }

}
