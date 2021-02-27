package com.farmers.buyers.modules.ratingAndReview;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListRequestParams;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListRequestParams;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class RatingAndReviewRepository extends BaseRepository {

//    public void ratingAndReview(ReviewdListParams params, ApiResponseCallback<ReviewListResponse> responseCallback) {
//        Call<ReviewListResponse> call = RetrofitBuilder.createServiceContract().getFarmReviewList(
//                ApiConstants.GET_FARM_REVIEW_LIST,
//                params.getAuth_key(),
//                params.getLoginId());
//        makeRequest(call, responseCallback);
//    }

    void getFarmReviewList(FarmReviewListRequestParams params, ApiResponseCallback<FarmReviewListApiModel> responseCallback){
        Call<FarmReviewListApiModel> call = RetrofitBuilder.createServiceContract().getFarmReviewList(ApiConstants.GET_FARM_REVIEW_LIST, params.getFarmId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }


    void getFarmReviewedList(FarmReviewedListRequestParams params, ApiResponseCallback<FarmReviewedListApiModel> responseCallback) {
        Call<FarmReviewedListApiModel> call = RetrofitBuilder.createServiceContract().getFarmReviewedList(ApiConstants.GET_FARM_REVIEWED_LIST, params.getFarmId(), params.getAuthKey(), params.getLoginId());
        makeRequest(call, responseCallback);
    }
}
