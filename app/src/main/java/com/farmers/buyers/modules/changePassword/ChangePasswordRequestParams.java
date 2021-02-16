package com.farmers.buyers.modules.changePassword;

import retrofit2.http.Field;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class ChangePasswordRequestParams {

    String new_password;
    String confirm_password;
    String Old_Password;
    String LoginId;
    String Mobile_OTP;
    String auth_key;

    public ChangePasswordRequestParams(String new_password, String confirm_password, String Old_Password, String Mobile_OTP,
                                       String LoginId,String auth_key) {
        this.new_password = new_password;
        this.confirm_password = confirm_password;
        this.Old_Password = Old_Password;
        this.LoginId = LoginId;
        this.Mobile_OTP = Mobile_OTP;
        this.auth_key = auth_key;
    }

    public String getMobile_OTP() {
        return Mobile_OTP;
    }

    public void setMobile_OTP(String mobile_OTP) {
        Mobile_OTP = mobile_OTP;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getOld_Password() {
        return Old_Password;
    }

    public void setOld_Password(String old_Password) {
        Old_Password = old_Password;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }
}