package com.farmers.buyers.modules.ratingAndReview.customerReview;


import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class CustomerReviewListRepository extends BaseRepository {

    void getCustomerReview(CustomerReviewListRequestParams params, ApiResponseCallback<CustomerReviewListApiModel> responseCallback) {
        Call<CustomerReviewListApiModel> call = RetrofitBuilder.createServiceContract().customer_review_list(ApiConstants.GET_CUSTOMER_REVIEW_LIST, params.getLoginId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
