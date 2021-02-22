package com.farmers.buyers.modules.address;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class MyAddressRepository extends BaseRepository {

    public void addAddress(AddAddressRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().addAddress(ApiConstants.ADD_ADDRESS,
                params.getLoginId(), params.getName_of_address(), params.getComplete_address(),
                params.getAddress_city(), params.getAddress_state(), params.getAddress_postcode(),
                params.getAccount_phone_number(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void getAddressList(MyAddressRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().getAddressList(ApiConstants.ADDRESS_LIST,
                params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void editAddress(AddAddressRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().editAddress(ApiConstants.EDIT_ADDRESS,
                params.getLoginId(), params.getAddressId(), params.getName_of_address(), params.getComplete_address(),
                params.getAddress_city(), params.getAddress_state(), params.getAddress_postcode(),
                params.getAccount_phone_number(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void deleteAddress(AddAddressRequestParams params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().deleteAddress(ApiConstants.DELETE_ADDRESS,
                params.getLoginId(), params.getAddress_postcode(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
