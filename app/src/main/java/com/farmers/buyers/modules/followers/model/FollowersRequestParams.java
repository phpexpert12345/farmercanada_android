package com.farmers.buyers.modules.followers.model;

public class FollowersRequestParams {
    String userId;
    String authKey;

    public FollowersRequestParams(String userId, String authKey) {
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
