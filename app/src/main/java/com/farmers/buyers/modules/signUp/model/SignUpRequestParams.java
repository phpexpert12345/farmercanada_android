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

    public SignUpRequestParams(String name, String mobile, String email, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
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
