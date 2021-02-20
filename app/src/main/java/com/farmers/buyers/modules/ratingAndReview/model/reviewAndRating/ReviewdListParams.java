package com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating;

import java.io.Serializable;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ReviewdListParams implements Serializable {

    String auth_key;
    String LoginId;

    public ReviewdListParams(String auth_key, String loginId) {
        this.auth_key = auth_key;
        LoginId = loginId;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public String getLoginId() {
        return LoginId;
    }
}
