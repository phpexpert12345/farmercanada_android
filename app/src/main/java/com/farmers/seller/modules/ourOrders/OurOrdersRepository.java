package com.farmers.seller.modules.ourOrders;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;

import retrofit2.Call;

public class OurOrdersRepository extends BaseRepository {

    public void getUserInformation(CategoryListRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().getUserInformation(ApiConstants.USER_INFORMATION,
                params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void newOrdersData(CategoryListRequestParams params, ApiResponseCallback<AllOrderResponse> responseCallback) {
        Call<AllOrderResponse> call = RetrofitBuilder.createServiceContract().getNewOrdersList(ApiConstants.NEW_ORDERS_LIST,
                params.getUserId(),
                params.getAuthKey(),
                params.getStatus()
        );
        makeRequest(call, responseCallback);
    }

    public void orderDetailsData(CategoryListRequestParams params, ApiResponseCallback<AllOrderResponse> responseCallback) {
        Call<AllOrderResponse> call = RetrofitBuilder.createServiceContract().getOrderDetails(ApiConstants.ORDERS_DETAILS_DATA,
                params.getUserId(),
                params.getAuthKey(),
                params.getStatus()
        );
        makeRequest(call, responseCallback);
    }

    public void orderAccept(CategoryListRequestParams params, ApiResponseCallback<AllOrderResponse> responseCallback) {
        Call<AllOrderResponse> call = RetrofitBuilder.createServiceContract().orderAccept(ApiConstants.ORDERS_ACCEPT,
                params.getUserId(),
                params.getAuthKey(),
                params.getStatus()
        );
        makeRequest(call, responseCallback);
    }

    public void orderDecline(CategoryListRequestParams params, ApiResponseCallback<AllOrderResponse> responseCallback) {
        Call<AllOrderResponse> call = RetrofitBuilder.createServiceContract().orderDecline(ApiConstants.ORDERS_DECLINE,
                params.getUserId(),
                params.getAuthKey(),
                params.getStatus(),
                params.getOrder_decline_reason()
        );
        makeRequest(call, responseCallback);
    }
}