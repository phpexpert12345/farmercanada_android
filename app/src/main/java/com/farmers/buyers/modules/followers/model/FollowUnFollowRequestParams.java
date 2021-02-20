package com.farmers.buyers.modules.followers.model;

public class FollowUnFollowRequestParams {
    String farmId;
    String loginId;
    String authKey;
    String status;

    public FollowUnFollowRequestParams(String farmId, String loginId, String authKey, String status) {
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
        this.status = status;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getStatus() {
        return status;
    }
}
