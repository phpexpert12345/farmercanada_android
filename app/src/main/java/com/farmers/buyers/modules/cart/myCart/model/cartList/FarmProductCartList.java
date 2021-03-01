package com.farmers.buyers.modules.cart.myCart.model.cartList;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/20/2021.
 */
public class FarmProductCartList implements Serializable , RecyclerViewListItem {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("item_quantity")
    @Expose
    private String itemQuantity;
    @SerializedName("item_price")
    @Expose
    private String itemPrice;
    @SerializedName("item_unit")
    @Expose
    private String itemUnit;
    @SerializedName("item_size")
    @Expose
    private String itemSize;
    @SerializedName("item_note")
    @Expose
    private String itemNote;
    @SerializedName("farm_id")
    @Expose
    private String farmId;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("farm_address")
    @Expose
    private String farmAddress;
    @SerializedName("store_state_name")
    @Expose
    private String storeStateName;
    @SerializedName("store_city_name")
    @Expose
    private String storeCityName;
    @SerializedName("farm_latitude")
    @Expose
    private String farmLatitude;
    @SerializedName("farm_longitude")
    @Expose
    private String farmLongitude;
    @SerializedName("farm_postcode")
    @Expose
    private String farmPostcode;
    @SerializedName("farm_logo")
    @Expose
    private String farmLogo;
    @SerializedName("farm_cover_photo")
    @Expose
    private String farmCoverPhoto;

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    @SerializedName("product_description")
    @Expose
    private String product_description;
    public String product_name;
    public String product_images;

    public String getCartId() {
        return cartId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public String getItemSize() {
        return itemSize;
    }

    public String getItemNote() {
        return itemNote;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public String getStoreStateName() {
        return storeStateName;
    }

    public String getStoreCityName() {
        return storeCityName;
    }

    public String getFarmLatitude() {
        return farmLatitude;
    }

    public String getFarmLongitude() {
        return farmLongitude;
    }

    public String getFarmPostcode() {
        return farmPostcode;
    }

    public String getFarmLogo() {
        return farmLogo;
    }

    public String getFarmCoverPhoto() {
        return farmCoverPhoto;
    }

    @Override
    public int getViewType() {
        return CardConstant.MY_CART_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
