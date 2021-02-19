package com.farmers.buyers.modules.signUp.model;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 18:35
 * mohammadsajjad679@gmail.com
 */

public class SignUpRequestParams {
    String name;
    String mobile;
    String email;
    String password;
    String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public Integer getAccount_type() {
        return account_type;
    }

    public String getAccount_country() {
        return account_country;
    }

    public String getAccount_state() {
        return account_state;
    }

    public String getAccount_city() {
        return account_city;
    }

    public String getAccount_address() {
        return account_address;
    }

    public String getAccount_lat() {
        return account_lat;
    }

    public String getAccount_long() {
        return account_long;
    }

    public String getAccount_phone_code() {
        return account_phone_code;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    Integer account_type;
    String  account_country;
    String account_state;
    String  account_city;
    String account_address;
    String account_lat;
    String account_long;
    String account_phone_code;
    String device_id;
    String device_platform;

    public SignUpRequestParams(String name, String mobile, String email, String password, Integer account_type, String  account_country, String account_state,String  account_city,String account_address,String account_lat,String account_long,String account_phone_code,String device_id,String device_platform, String authKey) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.account_country = account_country;
        this.account_city = account_city;
        this.account_address = account_address;
        this.account_lat = account_lat;
        this.account_long = account_long;
        this.account_phone_code = account_phone_code;
        this.device_id = device_id;
        this.account_state = account_state;
        this.device_platform = device_platform;
        this.account_type=account_type;
        this.authKey = authKey;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
