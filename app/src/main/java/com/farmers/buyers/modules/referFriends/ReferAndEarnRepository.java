package com.farmers.buyers.modules.referFriends;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class ReferAndEarnRepository extends BaseRepository {

    public void getReferAndEarn(MyAddressRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().getReferAndEarn(ApiConstants.REFER_AND_EARN,
                params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
