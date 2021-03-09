package com.farmers.seller.modules.setupSellerAccount;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreRequestParams;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreSetupExtra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreViewModel extends BaseViewModel {
    public List<RecyclerViewListItem> items = new ArrayList<>();
    private SetupStoreRepository repository = new SetupStoreRepository();
    private AppController appController = AppController.get();
    private StoreSetupExtra extra;

    public SetupStoreViewModel(StoreSetupExtra extra) {
        this.extra = extra;
    }

    public void prepareRageItems () {
        items.addAll(SetupStoreTransformer.getDeliveryRangeItem());
    }

    public void doSetupStore(MutableLiveData<DataFetchState<SetupStoreApiModel>> stateMachine, File doc1, File doc2, File doc3, File doc4) {
        stateMachine.postValue(DataFetchState.loading());

        SetupStoreRequestParams params = new SetupStoreRequestParams(
                extra.getCompanyType(),
                extra.getName(),
                extra.getLocation(),
                extra.getCity(),
                extra.getState(),
                extra.getCountry(),
                extra.getPostalCode(),
                extra.getDeliveryType(),
                extra.getMinimumPickupOrder() == null ? "": extra.getMinimumPickupOrder(),
                extra.getPickupMessage() == null ? "" : extra.getPickupMessage(),
                extra.getLocation(),
                extra.getRadius() == null ? "" : extra.getRadius(),
                extra.getDeliveryCharges() == null ? "" : extra.getDeliveryCharges(),
                extra.getAdditionalCharges() == null ? "" : extra.getAdditionalCharges(),
                extra.getMinimumDeliveryOrder() == null ? "" : extra.getMinimumDeliveryOrder(),
                extra.getDeliveryMessage() == null ? "" : extra.getDeliveryMessage(),
                extra.getCompanyType(),
                "document1",
                doc1,
                doc2,
                doc3,
                doc4,
                extra.getStoreLogo(),
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                extra.getMapLat(),
                extra.getMapLong()
        );

        Log.e("extra", extra.toString());

        repository.doStoreSetup(params, new ApiResponseCallback<SetupStoreApiModel>() {
            @Override
            public void onSuccess(SetupStoreApiModel response) {
                if (response.getStatus()) {
                    SharedPreferenceManager.getInstance().setIsStoreSetup(true);
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new SetupStoreApiModel()));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SetupStoreApiModel()));
            }
        });
    }


}
