package com.farmers.buyers.modules.address;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class MyAddressRequestParams {

    String authKey;

    public MyAddressRequestParams(String authKey) {
        this.authKey = authKey;
    }
    public String getAuthKey() {
        return authKey;
    }
}