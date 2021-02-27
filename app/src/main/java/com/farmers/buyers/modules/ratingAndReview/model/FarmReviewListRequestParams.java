package com.farmers.buyers.modules.ratingAndReview.model;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class FarmReviewListRequestParams {

    private String farmId;
    private String authKey;

    public FarmReviewListRequestParams(String farmId, String authKey) {
        this.farmId = farmId;
        this.authKey = authKey;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
