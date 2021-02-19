
package com.farmers.buyers.modules.wallet.view;

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
import com.farmers.buyers.modules.wallet.WalletTransformer;
import com.farmers.buyers.modules.wallet.model.WalletHeaderItems;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

public class WalletViewModel extends BaseViewModel {

    private WalletRepository repository = new WalletRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void getWalletHistoryList(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getLoginId(),
                appController.getAuthenticationKey());
        repository.getWalletHistoryList(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    items.add(new WalletHeaderItems());
                    items.add(new SimpleTitleItem("Today"));
                    items.addAll(WalletTransformer.getWalletHistory(response.getmData().WalletList));
                    items.add(new SimpleTitleItem("Yesterday"));
                    items.addAll(WalletTransformer.getYesterdayHistory());
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
