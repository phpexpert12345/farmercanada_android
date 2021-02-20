package com.farmers.buyers.modules.saveFarms;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListRequestParams;
import com.farmers.buyers.modules.saveFarms.model.SaveUnSaveFarmRequestModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.remote.StandardError;

public class SavedFarmListViewModel extends BaseViewModel {

    private SaveFarmRepository repository = new SaveFarmRepository();
    private AppController appController = AppController.get();


    void getSavedFarmList(MutableLiveData<DataFetchState<SaveFarmListApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.loading());

        SaveFarmListRequestParams params = new SaveFarmListRequestParams(appController.getUserId(), appController.getAuthenticationKey());


        repository.getSavedFarmList(params, new ApiResponseCallback<SaveFarmListApiModel>() {
            @Override
            public void onSuccess(SaveFarmListApiModel response) {
             stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SaveFarmListApiModel()));
            }
        });
    }

    public void saveUnSaveFarm(MutableLiveData<DataFetchState<SaveUnsaveFarmApiModel>> stateMachine, String farmId, int status) {
        stateMachine.postValue(DataFetchState.loading());

        SaveUnSaveFarmRequestModel params = new SaveUnSaveFarmRequestModel(farmId, appController.getLoginId(), status, appController.getAuthenticationKey());

        repository.saveUnSaveFarm(params, new ApiResponseCallback<SaveUnsaveFarmApiModel>() {
            @Override
            public void onSuccess(SaveUnsaveFarmApiModel response) {
                stateMachine.postValue(DataFetchState.success(new SaveUnsaveFarmApiModel(), ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SaveUnsaveFarmApiModel()));

            }
        });
    }
}
