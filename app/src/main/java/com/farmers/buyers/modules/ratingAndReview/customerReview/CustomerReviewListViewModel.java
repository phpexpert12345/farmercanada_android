package com.farmers.buyers.modules.ratingAndReview.customerReview;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListRequestParams;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 27-02-2021.
 * mohammadsajjad679@gmail.com
 */
public class CustomerReviewListViewModel extends BaseViewModel {
    private CustomerReviewListRepository repository = new CustomerReviewListRepository();
    private AppController appController = AppController.get();
    List<RecyclerViewListItem> items = new ArrayList<>();

    void getCustomerReviewList(MutableLiveData<DataFetchState<CustomerReviewListApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.loading());
        CustomerReviewListRequestParams params = new CustomerReviewListRequestParams(appController.getLoginId(), appController.getAuthenticationKey());
        repository.getCustomerReview(params, new ApiResponseCallback<CustomerReviewListApiModel>() {
            @Override
            public void onSuccess(CustomerReviewListApiModel response) {
                if (response.getStatus()) {
                    if (!response.getData().getReviewList().isEmpty()) {
                        items.addAll(ReviewTransfarmer.transformApiModelToCustomerReviewList(response.getData().getReviewList()));
                        stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                    }
                    else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new CustomerReviewListApiModel()));
                    }
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new CustomerReviewListApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new CustomerReviewListApiModel()));

            }
        });
    }
}
