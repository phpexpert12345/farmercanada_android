
package com.farmers.buyers.modules.wallet.view;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.DroidPrefs;
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

    public void getWalletHistoryList(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine, Context context) {
        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getLoginId(),
                appController.getAuthenticationKey());
        repository.getWalletHistoryList(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if(items.size()>0){
                    items.clear();
                }
                if (response.isStatus()) {
                    WalletHeaderItems walletHeaderItems=new WalletHeaderItems();
                    String amount= DroidPrefs.get(context,"wallet_amount",String.class);
                    walletHeaderItems.wallet_amount=amount;
                    items.add(walletHeaderItems);
                    items.add(new SimpleTitleItem("Recent"));
                    items.addAll(WalletTransformer.getWalletHistory(response.getmData().WalletList));
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
