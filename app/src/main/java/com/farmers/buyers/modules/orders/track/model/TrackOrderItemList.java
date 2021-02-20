package com.farmers.buyers.modules.orders.track.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:30
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderItemList implements RecyclerViewListItem {

    private String item_id;
    private String product_name;
    private String product_code;
    private String product_description;
    private String product_images;
    private String item_quantity;
    private String item_price;
    private String item_unit;
    private String item_size;
    private String item_note;

    public TrackOrderItemList() {

    }

    public TrackOrderItemList(String item_id, String product_name, String product_code, String product_description, String product_images, String item_quantity, String item_price, String item_unit, String item_size, String item_note) {
        this.item_id = item_id;
        this.product_name = product_name;
        this.product_code = product_code;
        this.product_description = product_description;
        this.product_images = product_images;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.item_unit = item_unit;
        this.item_size = item_size;
        this.item_note = item_note;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
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

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getItem_note() {
        return item_note;
    }

    public void setItem_note(String item_note) {
        this.item_note = item_note;
    }

    @Override
    public int getViewType() {
        return CardConstant.TRACK_ORDER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
