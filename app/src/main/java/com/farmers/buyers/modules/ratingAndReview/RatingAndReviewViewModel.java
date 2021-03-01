package com.farmers.buyers.modules.ratingAndReview;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListRequestParams;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class RatingAndReviewViewModel extends BaseViewModel {

    private RatingAndReviewRepository repository = new RatingAndReviewRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void getFarmReviewList(MutableLiveData<DataFetchState<FarmReviewListApiModel>> stateMachine, String farmId) {
        stateMachine.postValue(DataFetchState.loading());
        items.clear();

        FarmReviewListRequestParams params = new FarmReviewListRequestParams(farmId, appController.getAuthenticationKey());

        repository.getFarmReviewList(params, new ApiResponseCallback<FarmReviewListApiModel>() {
            @Override
            public void onSuccess(FarmReviewListApiModel response) {
                if (response.getStatus()) {
                    if (!response.getData().getFarmReviewList().isEmpty()) {
                        items.addAll(ReviewTransfarmer.transformApiModelToFarmReviewList(response.getData().getFarmReviewList()));
                        stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                    } else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new FarmReviewListApiModel()));

                    }
                }
                    else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new FarmReviewListApiModel()));

                    }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new FarmReviewListApiModel()));
            }
        });
    }

    public void getFarmReviewedList(MutableLiveData<DataFetchState<FarmReviewedListApiModel>> stateMachine) {
        items.clear();
        stateMachine.postValue(DataFetchState.loading());

        FarmReviewedListRequestParams params = new FarmReviewedListRequestParams(appController.getLoginId(), appController.getAuthenticationKey(), SharedPreferenceManager.getInstance().getFarmId());
        repository.getFarmReviewedList(params, new ApiResponseCallback<FarmReviewedListApiModel>() {
            @Override
            public void onSuccess(FarmReviewedListApiModel response) {
                if (response.getStatus()) {
                    if (!response.getData().getFarmReviewList().isEmpty()) {
                        items.addAll(ReviewTransfarmer.transformApiModelToFarmReviewedItems(response.getData().getFarmReviewList()));

                        stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                    }
                    else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new FarmReviewedListApiModel()));

                    }
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new FarmReviewedListApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new FarmReviewedListApiModel()));

            }
        });


    }
}
