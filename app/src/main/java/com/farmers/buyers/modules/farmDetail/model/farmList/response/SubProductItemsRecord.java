package com.farmers.buyers.modules.farmDetail.model.farmList.response;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class SubProductItemsRecord {
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("farm_id")
    @Expose
    private Integer farmId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    @SerializedName("product_sales_price_old")
    @Expose
    private String productSalesPriceOld;
    @SerializedName("Discount_product_Amount")
    @Expose
    private String discountProductAmount;
    @SerializedName("product_sales_price")
    @Expose
    private String productSalesPrice;
    @SerializedName("price_unit_type")
    @Expose
    private String priceUnitType;
    public String product_code;
    public String shopping_item_quantity;

    public Integer getProductID() {
        return productID;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductImages() {
        return productImages;
    }

    public String getProductSalesPriceOld() {
        return productSalesPriceOld;
    }

    public String getDiscountProductAmount() {
        return discountProductAmount;
    }

    public String getProductSalesPrice() {
        return productSalesPrice;
    }

    public String getPriceUnitType() {
        return priceUnitType;
    }
}
