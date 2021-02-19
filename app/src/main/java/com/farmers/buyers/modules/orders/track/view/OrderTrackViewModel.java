package com.farmers.buyers.modules.orders.track.view;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderRequestParams;
import com.farmers.buyers.modules.orders.subOrderList.SubOredrRepository;
import com.farmers.buyers.modules.orders.track.model.OrderTrackRequestParams;
import com.farmers.buyers.remote.StandardError;

public class OrderTrackViewModel extends BaseViewModel {

    private OrderTrackRepository repository = new OrderTrackRepository();

    public void getOrderDetails(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine, OrderTrackRequestParams orderTrackRequestParams) {

        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        repository.getOrderDetails(orderTrackRequestParams, new ApiResponseCallback<AddressApiModel>() {
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
