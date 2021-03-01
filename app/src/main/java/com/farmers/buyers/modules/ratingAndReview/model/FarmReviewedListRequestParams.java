package com.farmers.buyers.modules.ratingAndReview.model;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class FarmReviewedListRequestParams {
    private String loginId;
    private String authKey;
    private String farmId;

    public FarmReviewedListRequestParams(String loginId, String authKey, String farmId) {
        this.loginId = loginId;
        this.authKey = authKey;
        this.farmId = farmId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getFarmId() {
        return farmId;
    }
}
