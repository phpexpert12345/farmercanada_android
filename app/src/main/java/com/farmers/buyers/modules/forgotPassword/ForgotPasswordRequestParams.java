package com.farmers.buyers.modules.forgotPassword;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class ForgotPasswordRequestParams {

    String password;
    String confirm_password;
    String deviceId;
    Integer role;
    String platForm;
    String authKey;
    String mobile_number;

    public ForgotPasswordRequestParams(String mobile_number,String password, String confirm_password, String deviceId, Integer role, String platForm, String authKey) {
        this.mobile_number = mobile_number;
        this.password = password;
        this.confirm_password = confirm_password;
        this.deviceId = deviceId;
        this.role = role;
        this.platForm = platForm;
        this.authKey = authKey;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
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

    public String getMobileNumber() {
        return mobile_number;
    }
}