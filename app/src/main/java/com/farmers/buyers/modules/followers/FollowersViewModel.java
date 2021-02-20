package com.farmers.buyers.modules.followers;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
import com.farmers.buyers.modules.followers.model.FollowersApiModel;
import com.farmers.buyers.modules.followers.model.FollowersRequestParams;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

public class FollowersViewModel extends BaseViewModel {
    private FollowersRepository repository = new FollowersRepository();
    private String userId = AppController.get().getLoginId();
    private String authKey = AppController.get().getAuthenticationKey();
    List<RecyclerViewListItem> items = new ArrayList<>();


    public void getFollowers(MutableLiveData<DataFetchState<FollowersApiModel>> stateMachine) {
        items.clear();
        stateMachine.postValue(DataFetchState.loading());

        FollowersRequestParams params = new FollowersRequestParams(userId, authKey);
        repository.getFollowersList(params, new ApiResponseCallback<FollowersApiModel>() {
            @Override
            public void onSuccess(FollowersApiModel response) {
                if (response.getStatus()) {
                    items.addAll(FollowersTransformer.getFollowersItems(response.getData().getFarmFollowedList()));
                    stateMachine.postValue(DataFetchState.success(response, ""));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new FollowersApiModel()));
                }

            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new FollowersApiModel()));
            }
        });
    }

    public void followUnFollowFarm(MutableLiveData<DataFetchState<FollowUnFollowApiModel>> stateMachine, String farmId, String status) {
        stateMachine.postValue(DataFetchState.loading());

        FollowUnFollowRequestParams params = new FollowUnFollowRequestParams(farmId, userId, authKey, status);

        repository.followUnFollowFarm(params, new ApiResponseCallback<FollowUnFollowApiModel>() {
            @Override
            public void onSuccess(FollowUnFollowApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new FollowUnFollowApiModel()));
            }
        });
    }
}
