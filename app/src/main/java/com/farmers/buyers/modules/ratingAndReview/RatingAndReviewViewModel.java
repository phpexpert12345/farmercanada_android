package com.farmers.buyers.modules.ratingAndReview;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.remote.StandardError;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class RatingAndReviewViewModel extends BaseViewModel {

    RatingAndReviewRepository repository = new RatingAndReviewRepository();

    public void getReview(final MutableLiveData<DataFetchState<ReviewListResponse>> stateMutableLiveData, ReviewdListParams reviewdListParams) {
        stateMutableLiveData.postValue(DataFetchState.<ReviewListResponse>loading());
        repository.ratingAndReview(reviewdListParams, new ApiResponseCallback<ReviewListResponse>() {
            @Override
            public void onSuccess(ReviewListResponse response) {
                if (response.getData() != null)
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new ReviewListResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<ReviewListResponse>error(standardError.getDisplayError(), null));

            }
        });

    }
}
