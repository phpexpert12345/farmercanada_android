package com.farmers.buyers.modules.home.homeFragment;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class HomeFragmentRepository extends BaseRepository {

    public void getCategoryList(CategoryListRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().getCategoryList(ApiConstants.CATEGORY_LIST,
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void getOffersList(CategoryListRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().getOffersList(ApiConstants.OFFER_LIST,
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }


    public void getUserInformation(CategoryListRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().getUserInformation(ApiConstants.USER_INFORMATION,
                params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
    public void farmListRequest(FarmListRequest params, ApiResponseCallback<FarmListResponse> responseCallback) {
        Call<FarmListResponse> call = RetrofitBuilder.createServiceContract().FARM_LIST_RESPONSE_CALL(ApiConstants.FARM_LIST_URL,
                params.getAuthKey(),
                params.getCustomer_lat(),
                params.getCustomer_long(),
                params.getCustomer_full_address()
                , params.getCustomer_city(),
                params.getFarm_type()
                , params.getFarm_type_developer_information()
                , params.getFarm_service_type(),
                params.getOrder_type_developer_information(),
                params.getFarm_category_id(),
                params.getPageno(),
                params.getLoginId());

        makeRequest(call, responseCallback);
    }

    public void changeUserType(ProfileRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().changeUserType(ApiConstants.CHANGE_USER_TYPE,
                params.getLoginId(), params.getAccount_type(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
