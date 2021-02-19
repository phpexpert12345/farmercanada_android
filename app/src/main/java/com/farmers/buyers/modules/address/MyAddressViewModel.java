package com.farmers.buyers.modules.address;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.StandardError;

public class MyAddressViewModel extends BaseViewModel {

    private MyAddressRepository repository = new MyAddressRepository();
    private AppController appController = AppController.get();

    public void getAddressList(final MutableLiveData<DataFetchState<LoginApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<LoginApiModel>loading());
        MyAddressRequestParams myAddressRequestParams = new MyAddressRequestParams(appController.getAuthenticationKey());
        repository.getCategoryList(myAddressRequestParams, new ApiResponseCallback<LoginApiModel>() {
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
}
