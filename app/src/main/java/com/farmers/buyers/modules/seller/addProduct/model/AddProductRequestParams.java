package com.farmers.buyers.modules.seller.addProduct.model;

import java.io.File;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddProductRequestParams {
    private String productName;
    private String quantity;
    private String unit;
    private String categoryId;
    private String unitPrice;
    private String salesPrice;
    private String note;
    private String farmId;
    private String loginId;
    private String authKey;
    private File image;

    public AddProductRequestParams(String productName, String quantity, String unit, String categoryId, String unitPrice, String salesPrice, String note, String farmId, String loginId, String authKey, File image) {
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.salesPrice = salesPrice;
        this.note = note;
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public String getNote() {
        return note;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public File getImage() {
        return image;
    }
}
