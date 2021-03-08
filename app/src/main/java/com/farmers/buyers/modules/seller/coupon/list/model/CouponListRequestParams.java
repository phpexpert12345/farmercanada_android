package com.farmers.buyers.modules.seller.coupon.list.model;

/**
 * Created by Mohammad sajjad on 07-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class CouponListRequestParams {
    String authKey;
    String farmId;
    String loginId;

    public CouponListRequestParams(String authKey, String farmId, String loginId) {
        this.authKey = authKey;
        this.farmId = farmId;
        this.loginId = loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getLoginId() {
        return loginId;
    }
}
