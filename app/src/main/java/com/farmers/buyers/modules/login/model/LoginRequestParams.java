package com.farmers.buyers.modules.login.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class LoginRequestParams {

    String mobile;
    String password;

    public LoginRequestParams(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
