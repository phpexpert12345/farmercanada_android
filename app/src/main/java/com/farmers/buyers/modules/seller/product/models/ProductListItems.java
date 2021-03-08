package com.farmers.buyers.modules.seller.product.models;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:11
 * mohammadsajjad679@gmail.com
 */

public class ProductListItems implements RecyclerViewListItem {
    String productId;
    String productName;
    String listTotal;
    String perUnitPrice;
    String category;
    String quantity;
    String couponAmount;
    String couponCode;
    String description;
    String unitType;
    String salesPrice;

    public ProductListItems(String productId, String productName, String listTotal, String perUnitPrice, String category, String quantity, String couponAmount, String couponCode, String description, String unitType, String salesPrice) {
        this.productName = productName;
        this.listTotal = listTotal;
        this.perUnitPrice = perUnitPrice;
        this.category = category;
        this.quantity = quantity;
        this.couponAmount = couponAmount;
        this.couponCode = couponCode;
        this.description = description;
        this.productId = productId;
        this.unitType = unitType;
        this.salesPrice = salesPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getListTotal() {
        return listTotal;
    }

    public String getPerUnitPrice() {
        return perUnitPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getDescription() {
        return description;
    }

    public String getProductId() {
        return productId;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public String getUnitType() {
        return unitType;
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
