package com.farmers.seller.modules.broadcastMessage.activity;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageRequest;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;

import retrofit2.Call;

public class BroadcastMessageRepository extends BaseRepository {

    public void getBroadcastMessage(BroadcastMessageRequest params, ApiResponseCallback<BroadcastMessageResponse> responseCallback) {
        Call<BroadcastMessageResponse> call = RetrofitBuilder.createServiceContract().getBroadcastMessage(ApiConstants.BROADCAST_MESSAGE_LIST,
                params.getLoginId(),
                params.getAuth_key(),
                params.getFarm_id()
        );
        makeRequest(call, responseCallback);
    }

    public void publishBroadcastMessage(BroadcastMessageRequest params, ApiResponseCallback<BroadcastMessageResponse> responseCallback) {
        Call<BroadcastMessageResponse> call = RetrofitBuilder.createServiceContract().publishBroadcastMessage(ApiConstants.PUBLISH_BROADCAST_MESSAGE,
                params.getLoginId(),
                params.getAuth_key(),
                params.getFarm_id(),
                params.getMessage_title(),
                params.getMessage_content(),
                params.getMessage_target(),
                params.getMessage_status()
        );
        makeRequest(call, responseCallback);
    }

    public void deleteBroadcastMessage(BroadcastMessageRequest params, ApiResponseCallback<BroadcastMessageResponse> responseCallback) {
        Call<BroadcastMessageResponse> call = RetrofitBuilder.createServiceContract().deleteBroadcastMessage(ApiConstants.DELETE_BROADCAST_MESSAGE,
                params.getLoginId(),
                params.getAuth_key(),
                params.getFarm_id()
        );
        makeRequest(call, responseCallback);
    }

    public void editBroadcastMessage(BroadcastMessageRequest params, ApiResponseCallback<BroadcastMessageResponse> responseCallback) {
        Call<BroadcastMessageResponse> call = RetrofitBuilder.createServiceContract().editBroadcastMessage(ApiConstants.EDIT_BROADCAST_MESSAGE,
                params.getLoginId(),
                params.getAuth_key(),
                params.getFarm_id(),
                params.getMessage_title(),
                params.getMessage_content(),
                params.getMessage_target(),
                params.getMessage_status(),
                params.getMessageId()
        );
        makeRequest(call, responseCallback);
    }
}