package com.farmers.buyers.modules.ratingAndReview;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class RatingAndReviewRepository extends BaseRepository {

    public void ratingAndReview(ReviewdListParams params, ApiResponseCallback<ReviewListResponse> responseCallback) {
        Call<ReviewListResponse> call = RetrofitBuilder.createServiceContract().REVIEWD_LIST_RESPONSE_CALL(
                ApiConstants.REVIEW_AND_RATING_API,
                params.getAuth_key(),
                params.getLoginId());
        makeRequest(call, responseCallback);
    }

    public void farmReview(ReviewdListParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().farm_review_data(
                ApiConstants.FARM_REVIEW,
                params.getAuth_key(),
                params.getLoginId());
        makeRequest(call, responseCallback);
    }

}
