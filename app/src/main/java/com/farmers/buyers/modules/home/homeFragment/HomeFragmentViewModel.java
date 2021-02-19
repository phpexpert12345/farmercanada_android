package com.farmers.buyers.modules.home.homeFragment;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

public class HomeFragmentViewModel extends BaseViewModel {

    private HomeFragmentRepository repository = new HomeFragmentRepository();
    private AppController appController = AppController.get();

    public void getCategoryList(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getAuthenticationKey());
        repository.getCategoryList(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.getData() != null) {
                    stateMachine.postValue(DataFetchState.success(response, response.getData().getMessage()));
                } else {
                    stateMachine.postValue(DataFetchState.<LoginApiModel>error(response.getData().getMessage(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<LoginApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getOffersList(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getAuthenticationKey());
        repository.getOffersList(loginRequestParams, new ApiResponseCallback<LoginApiModel>() {
            @Override
            public void onSuccess(LoginApiModel response) {
                if (response.getData() != null) {
                    stateMachine.postValue(DataFetchState.success(response, response.getData().getMessage()));
                } else {
                    stateMachine.postValue(DataFetchState.<LoginApiModel>error(response.getData().getMessage(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<LoginApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getFarmList(final MutableLiveData<DataFetchState<FarmListResponse>> stateMutableLiveData,FarmListRequest farmListRequest){
        stateMutableLiveData.postValue(DataFetchState.<FarmListResponse>loading());
        repository.farmListRequest(farmListRequest, new ApiResponseCallback<FarmListResponse>() {
            @Override
            public void onSuccess(FarmListResponse response) {
                if (response.getFarmData()!=null)
                stateMutableLiveData.postValue(DataFetchState.success(response,response.getStatusMessage()));
                else
                stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new FarmListResponse()));
            }
            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<FarmListResponse>error(standardError.getDisplayError(), null));

            }
        });

    }
}
