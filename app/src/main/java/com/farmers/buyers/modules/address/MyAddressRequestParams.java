package com.farmers.buyers.modules.address;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class MyAddressRequestParams {

    String authKey;
    String userId;

    public MyAddressRequestParams(String userId,String authKey) {
        this.userId = userId;
        this.authKey = authKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthKey() {
        return authKey;
    }
}