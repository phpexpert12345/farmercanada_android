package com.farmers.buyers.modules.login.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class LoginRequestParams {

    String email;
    String password;

    public LoginRequestParams(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
