package com.farmers.buyers.modules.home.homeFragment;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class CategoryListRequestParams {

    String userId;
    String authKey;

    public CategoryListRequestParams(String userId,String authKey) {
        this.userId = userId;
        this.authKey = authKey;
    }

    public CategoryListRequestParams(String authKey) {
        this.authKey = authKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthKey() {
        return authKey;
    }
}