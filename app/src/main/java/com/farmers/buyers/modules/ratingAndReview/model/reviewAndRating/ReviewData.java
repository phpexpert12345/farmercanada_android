package com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class ReviewData implements Serializable {

    @SerializedName("ReviewList")@Expose
    List<ReviewList> reviewData;

    public List<ReviewList> getReviewData() {
        return reviewData;
    }
}
