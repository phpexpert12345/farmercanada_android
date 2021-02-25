package com.farmers.buyers.modules.saveFarms;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.FollowersRepository;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListRequestParams;
import com.farmers.buyers.modules.saveFarms.model.SaveUnSaveFarmRequestModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

public class SavedFarmListViewModel extends BaseViewModel {

    private SaveFarmRepository repository = new SaveFarmRepository();
    private FollowersRepository followersRepository = new FollowersRepository();
    private AppController appController = AppController.get();
    List<RecyclerViewListItem> items = new ArrayList<>();


    void getSavedFarmList(MutableLiveData<DataFetchState<SaveFarmListApiModel>> stateMachine) {
        items.clear();
        stateMachine.postValue(DataFetchState.loading());

        SaveFarmListRequestParams params = new SaveFarmListRequestParams(appController.getLoginId(), appController.getAuthenticationKey());

        items.clear();

        repository.getSavedFarmList(params, new ApiResponseCallback<SaveFarmListApiModel>() {
            @Override
            public void onSuccess(SaveFarmListApiModel response) {
                if (response.getStatus()) {
                    items.addAll(SaveFarmTransformer.getFarmListItem(response.getData().getFarmFavouriteList()));
                    stateMachine.postValue(DataFetchState.success(response, ""));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SaveFarmListApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SaveFarmListApiModel()));
            }
        });
    }

    public void saveUnSaveFarm(MutableLiveData<DataFetchState<SaveUnsaveFarmApiModel>> stateMachine, String farmId, int status, String favoriteId) {
        stateMachine.postValue(DataFetchState.loading());

        SaveUnSaveFarmRequestModel params = new SaveUnSaveFarmRequestModel(farmId, appController.getLoginId(), status, appController.getAuthenticationKey(), favoriteId);

        repository.saveUnSaveFarm(params, new ApiResponseCallback<SaveUnsaveFarmApiModel>() {
            @Override
            public void onSuccess(SaveUnsaveFarmApiModel response) {
                if (response.getStatus())
                {
                    stateMachine.postValue(DataFetchState.success(new SaveUnsaveFarmApiModel(), ""));
                }
                else  {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SaveUnsaveFarmApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SaveUnsaveFarmApiModel()));

            }
        });
    }

    public void followUnFollowFarm(MutableLiveData<DataFetchState<FollowUnFollowApiModel>> stateMachine, String farmId, String status, String followId) {
        stateMachine.postValue(DataFetchState.loading());

        FollowUnFollowRequestParams params = new FollowUnFollowRequestParams(farmId, appController.getLoginId(), appController.getAuthenticationKey(), status, followId);

        followersRepository.followUnFollowFarm(params, new ApiResponseCallback<FollowUnFollowApiModel>() {
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
