package com.farmers.buyers.modules.seller.product.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class ProductListItems implements RecyclerViewListItem {
    String productName;
    String listTotal;
    String perUnitPrice;
    String category;
    String quantity;
    String couponAmount;
    String couponCode;
    String description;

    @Override
    public int getViewType() {
        return CardConstant.PRODUCT_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
