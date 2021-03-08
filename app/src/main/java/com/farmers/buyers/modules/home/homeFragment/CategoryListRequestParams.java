package com.farmers.buyers.modules.home.homeFragment;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:40
 * mohammadsajjad679@gmail.com
 */

public class CategoryListRequestParams {

    String userId;
    String authKey;
    String status;
    String order_decline_reason;

    public CategoryListRequestParams(String userId, String authKey, String status, String order_decline_reason) {
        this.userId = userId;
        this.authKey = authKey;
        this.status = status;
        this.order_decline_reason = order_decline_reason;
    }

    public CategoryListRequestParams(String userId, String authKey, String status) {
        this.userId = userId;
        this.authKey = authKey;
        this.status = status;
    }

    public CategoryListRequestParams(String userId, String authKey) {
        this.userId = userId;
        this.authKey = authKey;
    }

    public CategoryListRequestParams(String authKey) {
        this.authKey = authKey;
    }

    public String getOrder_decline_reason() {
        return order_decline_reason;
    }

    public void setOrder_decline_reason(String order_decline_reason) {
        this.order_decline_reason = order_decline_reason;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}