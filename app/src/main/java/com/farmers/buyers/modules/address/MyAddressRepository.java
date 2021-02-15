package com.farmers.buyers.modules.address;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class MyAddressRepository extends BaseRepository {

    public void getCategoryList(MyAddressRequestParams params, ApiResponseCallback<LoginApiModel> responseCallback) {
        Call<LoginApiModel> call = RetrofitBuilder.createServiceContract().getAddressList(ApiConstants.ADDRESS_LIST,
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
