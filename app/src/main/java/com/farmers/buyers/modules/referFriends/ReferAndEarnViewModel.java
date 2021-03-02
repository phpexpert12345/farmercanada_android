package com.farmers.buyers.modules.referFriends;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.AddressTransformer;
import com.farmers.buyers.modules.address.MyAddressRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

public class ReferAndEarnViewModel extends BaseViewModel {

    private ReferAndEarnRepository repository = new ReferAndEarnRepository();
    private AppController appController = AppController.get();

    public void getReferAndEarn(final MutableLiveData<DataFetchState<AddressApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AddressApiModel>loading());
        MyAddressRequestParams myAddressRequestParams = new MyAddressRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey());
        repository.getReferAndEarn(myAddressRequestParams, new ApiResponseCallback<AddressApiModel>() {
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
