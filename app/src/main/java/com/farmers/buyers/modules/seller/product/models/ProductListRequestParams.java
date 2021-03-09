package com.farmers.buyers.modules.seller.product.models;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ProductListRequestParams {
    String loginId;
    String farmId;
    String authKey;

    public ProductListRequestParams(String loginId, String farmId, String authKey) {
        this.loginId = loginId;
        this.farmId = farmId;
        this.authKey = authKey;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
