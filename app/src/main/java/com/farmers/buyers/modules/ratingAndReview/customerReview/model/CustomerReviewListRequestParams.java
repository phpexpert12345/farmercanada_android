package com.farmers.buyers.modules.ratingAndReview.customerReview.model;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class CustomerReviewListRequestParams {
    private String loginId;
    private String authKey;

    public CustomerReviewListRequestParams(String loginId, String authKey) {
        this.loginId = loginId;
        this.authKey = authKey;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getAuthKey() {
        return authKey;
    }
}
