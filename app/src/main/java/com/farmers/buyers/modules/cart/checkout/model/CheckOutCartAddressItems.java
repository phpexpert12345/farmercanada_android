package com.farmers.buyers.modules.cart.checkout.model;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.io.Serializable;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class CheckOutCartAddressItems implements RecyclerViewListItem, Serializable {
    private String address_id;
    private String addressType;
    private String address;
    private String detail;
    Boolean isSelected;
    Boolean canChange;

    public CheckOutCartAddressItems(String address_id, String addressType, String address, String detail, Boolean isSelected, Boolean canChange) {
        this.address_id = address_id;
        this.addressType = addressType;
        this.address = address;
        this.detail = detail;
        this.isSelected = isSelected;
        this.canChange = canChange;
    }

    public String getAddressType() {
        return addressType;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getDetail() {
        return detail;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public Boolean getCanChange() {
        return canChange;
    }

    @Override
    public int getViewType() {
        return CardConstant.MY_CART_ADDRESS_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
