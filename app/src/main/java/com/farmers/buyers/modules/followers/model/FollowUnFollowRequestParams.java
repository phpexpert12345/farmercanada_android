package com.farmers.buyers.modules.followers.model;

public class FollowUnFollowRequestParams {
    String farmId;
    String loginId;
    String authKey;
    String status;
    String followId;

    public FollowUnFollowRequestParams(String farmId, String loginId, String authKey, String status, String followId) {
        this.farmId = farmId;
        this.loginId = loginId;
        this.authKey = authKey;
        this.status = status;
        this.followId = followId;
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

    public String getFollowId() {
        return followId;
    }
}
