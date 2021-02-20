
package com.farmers.buyers.modules.profile.view;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentViewModel extends BaseViewModel {

    private ProfileFragmentRepository repository = new ProfileFragmentRepository();
    private AppController appController = AppController.get();

    public void changeUserType(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine, ProfileRequestParams profileRequestParams) {
        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        repository.changeUserType(profileRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllDataModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }
}
