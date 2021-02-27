package com.farmers.buyers.modules.farmDetail.model.farmList.request;

import android.app.Activity;

import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmProductListReq implements Serializable {

    public String auth_key;
    public String farm_id;
    public String LoginId;
    public String item_id;
    public String item_quantity;
    public String item_price;
    public String item_size;
    public String item_unit;
    public String order_type;
    public Activity context;
    public String product_stock;
    public String shopping_item_available;
    public String category_id;
    public String cart_id;
    public String productId;

    public FarmProductListReq(String auth_key, String farm_id, String loginId, String item_id, String item_quantity,
                              String item_price, String item_size, String item_unit, String order_type) {
        this.auth_key = auth_key;
        this.farm_id = farm_id;
        LoginId = loginId;
        this.item_id = item_id;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.item_size = item_size;
        this.item_unit = item_unit;
        this.order_type = order_type;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public FarmProductListReq(Activity context, String auth_key, String loginId, String farm_id) {
        this.context = context;
        this.LoginId = loginId;
        this.auth_key = auth_key;
        this.farm_id = farm_id;
    }


    public Activity getContext() {
        return context;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getFarm_id() {
        return farm_id;
    }
}
