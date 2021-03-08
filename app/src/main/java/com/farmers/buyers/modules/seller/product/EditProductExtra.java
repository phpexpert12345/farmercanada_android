package com.farmers.buyers.modules.seller.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class EditProductExtra implements Serializable {
    private String productId;
    private String productName;
    private String listTotal;
    private String perUnitPrice;
    private String category;
    private String quantity;
    private String couponAmount;
    private String couponCode;
    private String description;
    private String unitType;
    private String salesPrice;

    public EditProductExtra(String productId, String productName, String listTotal, String perUnitPrice, String category, String quantity, String couponAmount, String couponCode, String description, String unitType, String salesPrice) {
        this.productId = productId;
        this.productName = productName;
        this.listTotal = listTotal;
        this.perUnitPrice = perUnitPrice;
        this.category = category;
        this.quantity = quantity;
        this.couponAmount = couponAmount;
        this.couponCode = couponCode;
        this.description = description;
        this.unitType = unitType;
        this.salesPrice = salesPrice;
    }

    public String getProductId() {
        return productId;
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

    public String getUnitType() {
        return unitType;
    }

    public String getSalesPrice() {
        return salesPrice;
    }
}