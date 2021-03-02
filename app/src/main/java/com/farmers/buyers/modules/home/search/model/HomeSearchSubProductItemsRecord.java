package com.farmers.buyers.modules.home.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSearchSubProductItemsRecord {

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
        @SerializedName("product_code")
        @Expose
        private String productCode;
        @SerializedName("product_stock")
        @Expose
        private String productStock;
        @SerializedName("shopping_item_available")
        @Expose
        private String shoppingItemAvailable;
        @SerializedName("shopping_item_quantity")
        @Expose
        private String shoppingItemQuantity;
        @SerializedName("cart_id")
        @Expose
        private String cartId;
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

        public Integer getProductID() {
            return productID;
        }

        public void setProductID(Integer productID) {
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

        public String getShoppingItemAvailable() {
            return shoppingItemAvailable;
        }

        public void setShoppingItemAvailable(String shoppingItemAvailable) {
            this.shoppingItemAvailable = shoppingItemAvailable;
        }

        public String getShoppingItemQuantity() {
            return shoppingItemQuantity;
        }

        public void setShoppingItemQuantity(String shoppingItemQuantity) {
            this.shoppingItemQuantity = shoppingItemQuantity;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
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

        public String getProductSalesPriceOld() {
            return productSalesPriceOld;
        }

        public void setProductSalesPriceOld(String productSalesPriceOld) {
            this.productSalesPriceOld = productSalesPriceOld;
        }

        public String getDiscountProductAmount() {
            return discountProductAmount;
        }

        public void setDiscountProductAmount(String discountProductAmount) {
            this.discountProductAmount = discountProductAmount;
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
