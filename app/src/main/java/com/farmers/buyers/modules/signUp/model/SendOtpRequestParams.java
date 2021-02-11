package com.farmers.buyers.modules.signUp.model;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 15:51
 * mohammadsajjad679@gmail.com
 */

public class SendOtpRequestParams {
    String number;

    public SendOtpRequestParams(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
