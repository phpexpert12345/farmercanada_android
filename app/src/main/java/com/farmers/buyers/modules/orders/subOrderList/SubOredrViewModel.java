package com.farmers.buyers.modules.orders.subOrderList;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.MyAddressRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.remote.StandardError;

public class SubOredrViewModel extends BaseViewModel {

    private SubOredrRepository repository = new SubOredrRepository();

    public void getSubOrder(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine, SubOrderRequestParams subOrderRequestParams) {

        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        repository.getSubOrder(subOrderRequestParams, new ApiResponseCallback<AddressApiModel>() {
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
