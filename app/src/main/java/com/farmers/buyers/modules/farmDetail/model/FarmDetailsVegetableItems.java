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
    public String product_stock;
    public String shopping_item_available;
    public String category_id;
    public String cart_id;
    public String productId;
    public String price_unit_type;
    public String farmId;

    public FarmDetailsVegetableItems(String imageUri, String title, String price, String quantity, Boolean inStock) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }

    public FarmDetailsVegetableItems(String imageUri, String title, String price, String quantity, Boolean inStock,
                                     String shopping_item_quantity, String product_code, String product_stock,
                                     String shopping_item_available, String category_id, String cart_id, String productId,
                                     String price_unit_type, String farmId) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
        this.shopping_item_quantity = shopping_item_quantity;
        this.product_code = product_code;
        this.product_stock = product_stock;
        this.shopping_item_available = shopping_item_available;
        this.category_id = category_id;
        this.cart_id = cart_id;
        this.productId = productId;
        this.price_unit_type = price_unit_type;
        this.farmId = farmId;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
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
