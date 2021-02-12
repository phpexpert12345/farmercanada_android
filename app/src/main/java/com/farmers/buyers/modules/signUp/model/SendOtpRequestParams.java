package com.farmers.buyers.modules.signUp.model;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 15:51
 * mohammadsajjad679@gmail.com
 */

public class SendOtpRequestParams {
    String number;
    String authKey;
    String loginId;

    public SendOtpRequestParams(String number, String authKey, String loginId) {
        this.number = number;
        this.authKey = authKey;
        this.loginId = loginId;
    }

    public String getNumber() {
        return number;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getLoginId() {
        return loginId;
    }
}
