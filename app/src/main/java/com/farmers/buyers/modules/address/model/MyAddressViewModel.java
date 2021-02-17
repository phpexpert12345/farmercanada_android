package com.farmers.buyers.modules.address.model;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.MyAddressRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.remote.StandardError;

public class MyAddressViewModel extends BaseViewModel {

    private MyAddressRepository repository = new MyAddressRepository();
    private AppController appController = AppController.get();

    public void getAddressList(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        MyAddressRequestParams myAddressRequestParams = new MyAddressRequestParams(appController.getLoginId(),appController.getAuthenticationKey());
        repository.getAddressList(myAddressRequestParams, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AddressApiModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void addAddress(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine, AddAddressRequestParams addAddressRequestParams) {

        if (addAddressRequestParams.getName_of_address().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Address Type", new AddressApiModel()));
            return;
        }
        if (addAddressRequestParams.getComplete_address().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Complete Address", new AddressApiModel()));
            return;
        }
        if (addAddressRequestParams.getAddress_city().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter City name", new AddressApiModel()));
            return;
        }
        if (addAddressRequestParams.getAddress_state().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter City State", new AddressApiModel()));
            return;
        }
        if (addAddressRequestParams.getAddress_postcode().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter Postcode", new AddressApiModel()));
            return;
        }

        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        repository.addAddress(addAddressRequestParams, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AddressApiModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void deleteAddress(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine,AddAddressRequestParams addAddressRequestParams) {

        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        repository.deleteAddress(addAddressRequestParams, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AddressApiModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));
            }
        });
    }
}
