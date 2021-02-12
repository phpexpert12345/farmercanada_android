package com.farmers.buyers.modules.login.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class LoginRequestParams {

    String mobile;
    String password;
    String deviceId;
    Integer role;
    String platForm;
    String authKey;

    public LoginRequestParams(String mobile, String password, String deviceId, Integer role, String platForm, String authKey) {
        this.mobile = mobile;
        this.password = password;
        this.deviceId = deviceId;
        this.role = role;
        this.platForm = platForm;
        this.authKey = authKey;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Integer getRole() {
        return role;
    }

    public String getPlatForm() {
        return platForm;
    }

    public String getAuthKey() {
        return authKey;
    }
}


//    account_email:test@gmail.com
//    account_password:123556
//    account_type:1
//    device_id:fdfd
//    device_platform:android