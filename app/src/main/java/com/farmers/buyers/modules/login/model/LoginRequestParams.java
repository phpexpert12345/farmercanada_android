package com.farmers.buyers.modules.login.model;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class LoginRequestParams {

    String email_or_mobile_no;
    String password;

    public LoginRequestParams(String email_or_mobile_no, String password) {
        this.email_or_mobile_no = email_or_mobile_no;
        this.password = password;
    }


}
