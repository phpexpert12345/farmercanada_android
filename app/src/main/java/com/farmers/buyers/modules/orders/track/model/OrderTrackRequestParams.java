package com.farmers.buyers.modules.orders.track.model;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 18:35
 * mohammadsajjad679@gmail.com
 */

public class OrderTrackRequestParams {

    String LoginId;
    String farm_followed_status;
    String authKey;
    String orderId;

    public OrderTrackRequestParams(String loginId, String farm_followed_status, String orderId, String authKey) {
        this.LoginId = loginId;
        this.farm_followed_status = farm_followed_status;
        this.orderId = orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
