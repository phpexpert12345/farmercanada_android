package com.farmers.buyers.modules.seller.product.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubProductItemsRecordSeller {
    @SerializedName("ProductID")
    @Expose
    private String productID;
    @SerializedName("farm_id")
    @Expose
    private Integer farmId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_stock")
    @Expose
    private String productStock;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    @SerializedName("Discount_product_Amount")
    @Expose
    private String discountProductAmount;
    @SerializedName("product_unit_price")
    @Expose
    private String productUnitPrice;
    @SerializedName("product_sales_price")
    @Expose
    private String productSalesPrice;
    @SerializedName("price_unit_type")
    @Expose
    private String priceUnitType;

       @SerializedName("product_category_Name")
    @Expose
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public String getDiscountProductAmount() {
        return discountProductAmount;
    }

    public void setDiscountProductAmount(String discountProductAmount) {
        this.discountProductAmount = discountProductAmount;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductSalesPrice() {
        return productSalesPrice;
    }

    public void setProductSalesPrice(String productSalesPrice) {
        this.productSalesPrice = productSalesPrice;
    }

    public String getPriceUnitType() {
        return priceUnitType;
    }

    public void setPriceUnitType(String priceUnitType) {
        this.priceUnitType = priceUnitType;
    }

}
