package com.farmers.buyers.modules.orders.subOrderList;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class SubOredrRepository extends BaseRepository {

    public void getSubOrder(SubOrderRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().subOrderList(ApiConstants.SUB_ORDER_LIST,
                params.getLoginId(), params.getFarm_followed_status(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
