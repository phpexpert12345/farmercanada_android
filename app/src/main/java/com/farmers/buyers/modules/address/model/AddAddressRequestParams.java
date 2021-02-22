package com.farmers.buyers.modules.address.model;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 18:35
 * mohammadsajjad679@gmail.com
 */

public class AddAddressRequestParams {

    String LoginId;
    String name_of_address;
    String complete_address;
    String address_city;
    String address_state;
    String address_postcode;
    String account_phone_number;
    String authKey;
    String addressId;

    public AddAddressRequestParams(String loginId, String addressId, String authKey) {
        this.LoginId = loginId;
        this.address_postcode = addressId;
        this.authKey = authKey;
    }

    public AddAddressRequestParams(String loginId, String addressId, String name_of_address, String complete_address, String address_city, String address_state,
                                   String address_postcode, String account_phone_number, String authKey) {
        this.LoginId = loginId;
        this.addressId = addressId;
        this.name_of_address = name_of_address;
        this.complete_address = complete_address;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_postcode = address_postcode;
        this.account_phone_number = account_phone_number;
        this.authKey = authKey;
    }

    public AddAddressRequestParams(String loginId, String name_of_address, String complete_address, String address_city, String address_state,
                                   String address_postcode, String account_phone_number, String authKey) {
        this.LoginId = loginId;
        this.name_of_address = name_of_address;
        this.complete_address = complete_address;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_postcode = address_postcode;
        this.account_phone_number = account_phone_number;
        this.authKey = authKey;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getName_of_address() {
        return name_of_address;
    }

    public void setName_of_address(String name_of_address) {
        this.name_of_address = name_of_address;
    }

    public String getComplete_address() {
        return complete_address;
    }

    public void setComplete_address(String complete_address) {
        this.complete_address = complete_address;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_state() {
        return address_state;
    }

    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_postcode() {
        return address_postcode;
    }

    public void setAddress_postcode(String address_postcode) {
        this.address_postcode = address_postcode;
    }

    public String getAccount_phone_number() {
        return account_phone_number;
    }

    public void setAccount_phone_number(String account_phone_number) {
        this.account_phone_number = account_phone_number;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
