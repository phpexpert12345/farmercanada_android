
package com.farmers.seller.modules.ourOrders;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.followers.FollowersRepository;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.search.HomeSearchRepository;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.modules.saveFarms.SaveFarmRepository;
import com.farmers.buyers.modules.saveFarms.model.SaveUnSaveFarmRequestModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;

import java.util.ArrayList;
import java.util.List;

public class OurOrdersViewModel extends BaseViewModel {

    private OurOrdersRepository repository = new OurOrdersRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();
    public List<RecyclerViewListItem> runningItems = new ArrayList<>();
    public List<RecyclerViewListItem> pastItems = new ArrayList<>();

    public void getNewOrders(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                "0");
        repository.newOrdersData(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                items.clear();
                if (response.isStatus()) {
                    items.addAll(OurOrdersTransformer.getOurOrderList(response.getData().getGetOrderList()));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getRunningOrders(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                "1");
        repository.newOrdersData(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                if (response.isStatus()) {
                    runningItems.addAll(OurOrdersTransformer.getOngoingOrderList(response.getData().getGetOrderList()));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getPastOrders(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                "2");
        repository.newOrdersData(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                if (response.isStatus()) {
                    pastItems.addAll(OurOrdersTransformer.getPastOrderList(response.getData().getGetOrderList()));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getOrderDetails(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine, String orderNumber) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                orderNumber);
        repository.orderDetailsData(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void orderAccept(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine, String orderNumber) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                orderNumber);
        repository.orderAccept(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void orderDecline(final MutableLiveData<DataFetchState<AllOrderResponse>> stateMachine, String orderNumber, String message) {
        stateMachine.postValue(DataFetchState.<AllOrderResponse>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                orderNumber, message);
        repository.orderDecline(loginRequestParams, new ApiResponseCallback<AllOrderResponse>() {
            @Override
            public void onSuccess(AllOrderResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllOrderResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllOrderResponse>error(standardError.getDisplayError(), null));
            }
        });
    }
}