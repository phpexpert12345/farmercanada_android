
package com.farmers.seller.modules.broadcastMessage.activity;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.StandardError;
import com.farmers.seller.modules.broadcastMessage.BroadCastMessageTransformer;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageRequest;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;
import com.farmers.seller.modules.ourOrders.OurOrdersRepository;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;

import java.util.ArrayList;
import java.util.List;

public class BroadcastMessageViewModel extends BaseViewModel {

    private BroadcastMessageRepository repository = new BroadcastMessageRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void getBroadcastMessage(final MutableLiveData<DataFetchState<BroadcastMessageResponse>> stateMachine) {
        stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>loading());
        BroadcastMessageRequest broadcastMessageRequest = new BroadcastMessageRequest(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                appController.getFarmId());
        repository.getBroadcastMessage(broadcastMessageRequest, new ApiResponseCallback<BroadcastMessageResponse>() {
            @Override
            public void onSuccess(BroadcastMessageResponse response) {
                items.clear();
                if (response.isStatus()) {
                    items.addAll(BroadCastMessageTransformer.getBroadcastList(response.getData().getBroadcastList()));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void publishBroadcastMessage(final MutableLiveData<DataFetchState<BroadcastMessageResponse>> stateMachine,
                                        BroadcastMessageRequest broadcastMessageRequest) {

        if (broadcastMessageRequest.getMessage_title().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter title", new BroadcastMessageResponse()));
            return;
        }

        if (broadcastMessageRequest.getMessage_content().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter message", new BroadcastMessageResponse()));
            return;
        }
        if (broadcastMessageRequest.getMessage_target().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please choose Audience", new BroadcastMessageResponse()));
            return;
        }

        stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>loading());
        repository.publishBroadcastMessage(broadcastMessageRequest, new ApiResponseCallback<BroadcastMessageResponse>() {
            @Override
            public void onSuccess(BroadcastMessageResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void deleteBroadcastMessage(final MutableLiveData<DataFetchState<BroadcastMessageResponse>> stateMachine,String msgId) {
        stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>loading());
        BroadcastMessageRequest broadcastMessageRequest = new BroadcastMessageRequest(
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                msgId);
        repository.deleteBroadcastMessage(broadcastMessageRequest, new ApiResponseCallback<BroadcastMessageResponse>() {
            @Override
            public void onSuccess(BroadcastMessageResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void editBroadcastMessage(final MutableLiveData<DataFetchState<BroadcastMessageResponse>> stateMachine,
                                        BroadcastMessageRequest broadcastMessageRequest) {

        if (broadcastMessageRequest.getMessage_title().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter title", new BroadcastMessageResponse()));
            return;
        }

        if (broadcastMessageRequest.getMessage_content().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter message", new BroadcastMessageResponse()));
            return;
        }
        if (broadcastMessageRequest.getMessage_target().isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please choose Audience", new BroadcastMessageResponse()));
            return;
        }

        stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>loading());
        repository.editBroadcastMessage(broadcastMessageRequest, new ApiResponseCallback<BroadcastMessageResponse>() {
            @Override
            public void onSuccess(BroadcastMessageResponse response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<BroadcastMessageResponse>error(standardError.getDisplayError(), null));
            }
        });
    }
}