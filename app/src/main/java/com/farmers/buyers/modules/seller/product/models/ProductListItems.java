package com.farmers.buyers.modules.seller.product.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class ProductListItems implements RecyclerViewListItem {

    public String ProductID;
    public String farm_id;
    public String category_id;
    public String product_name;
    public String product_code;
    public String product_stock;
    public String product_category_Name;
    public String product_description;
    public String product_images;
    public String Discount_product_Amount;
    public String product_unit_price;
    public String product_sales_price;
    public String price_unit_type;

    public ProductListItems(String productID, String farm_id, String category_id, String product_name, String product_code, String product_stock, String product_category_Name, String product_description, String product_images, String discount_product_Amount,
                            String product_unit_price, String product_sales_price, String price_unit_type) {
        ProductID = productID;
        this.farm_id = farm_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.product_code = product_code;
        this.product_stock = product_stock;
        this.product_category_Name = product_category_Name;
        this.product_description = product_description;
        this.product_images = product_images;
        Discount_product_Amount = discount_product_Amount;
        this.product_unit_price = product_unit_price;
        this.product_sales_price = product_sales_price;
        this.price_unit_type = price_unit_type;
    }

    @Override
    public int getViewType() {
        return CardConstant.PRODUCT_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
