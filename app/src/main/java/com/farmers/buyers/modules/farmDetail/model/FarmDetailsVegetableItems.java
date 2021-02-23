package com.farmers.buyers.modules.farmDetail.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:51
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailsVegetableItems implements RecyclerViewListItem {
    private String imageUri;
    private String title;
    private String price;
    private String quantity;
    private Boolean inStock;
    public String shopping_item_quantity;
    public String product_code;

    public FarmDetailsVegetableItems(String imageUri, String title, String price, String quantity, Boolean inStock) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }

    public FarmDetailsVegetableItems(String imageUri, String title, String price, String quantity, Boolean inStock, String shopping_item_quantity, String product_code) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
        this.shopping_item_quantity = shopping_item_quantity;
        this.product_code = product_code;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public Boolean getInStock() {
        return inStock;
    }

    @Override
    public int getViewType() {
        return CardConstant.FARM_DETAIL_VEGETABLE_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
