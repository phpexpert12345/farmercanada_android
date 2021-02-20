package com.farmers.buyers.modules.orders.subOrderList;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 18:35
 * mohammadsajjad679@gmail.com
 */

public class SubOrderRequestParams {

    String LoginId;
    String farm_followed_status;
    String authKey;

    public SubOrderRequestParams(String loginId, String farm_followed_status, String authKey) {
        this.LoginId = loginId;
        this.farm_followed_status = farm_followed_status;
        this.authKey = authKey;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getFarm_followed_status() {
        return farm_followed_status;
    }

    public void setFarm_followed_status(String farm_followed_status) {
        this.farm_followed_status = farm_followed_status;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
