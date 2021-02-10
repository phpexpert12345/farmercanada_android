package com.farmers.buyers.modules.login.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class LoginRequestParams {

    String mobile;
    String password;

    public Integer getUser_type() {
        return user_type;
    }

    Integer user_type;

    public LoginRequestParams(String mobile, String password, Integer user_type) {
        this.mobile = mobile;
        this.password = password;
        this.user_type=user_type;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
