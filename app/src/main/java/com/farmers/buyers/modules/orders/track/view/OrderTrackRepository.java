package com.farmers.buyers.modules.orders.track.view;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderRequestParams;
import com.farmers.buyers.modules.orders.track.model.OrderTrackRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class OrderTrackRepository extends BaseRepository {

    public void getOrderDetails(OrderTrackRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().orderDetails(ApiConstants.ORDER_DETAILS,
                params.getLoginId(), params.getFarm_followed_status(), params.getOrderId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
