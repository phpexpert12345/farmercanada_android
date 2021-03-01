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
    private String addressTitle;
    private String city;
    private String state;
    private String pin_code;
    private String phoneNumber;

    public double getAddress_lat() {
        return address_lat;
    }

    public void setAddress_lat(double address_lat) {
        this.address_lat = address_lat;
    }

    public double getAddress_long() {
        return address_long;
    }

    public void setAddress_long(double address_long) {
        this.address_long = address_long;
    }

    private double address_lat;
    private double address_long;
    Boolean isSelected;
    Boolean canChange;
    private String vendor_country;

    public CheckOutCartAddressItems(String address_id, String addressType, String address, String detail, Boolean isSelected, Boolean canChange) {
        this.address_id = address_id;
        this.addressType = addressType;
        this.address = address;
        this.detail = detail;
        this.isSelected = isSelected;
        this.canChange = canChange;
    }
public CheckOutCartAddressItems(String address_id,String city, String address, String vendor_country,String address_title, String phoneNumber,String state,String postcode,String add_lat,String add_long){
    this.address_id = address_id;
    this.city=city;
    this.address=address;
    this.vendor_country=vendor_country;
    this.addressTitle=address_title;
    this.phoneNumber=phoneNumber;
    this.state=state;
    this.pin_code=postcode;
    this.address_lat=Double.parseDouble(add_lat);
    this.address_long=Double.parseDouble(add_long);

}


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
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

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
