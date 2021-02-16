package com.farmers.buyers.modules.home.homeFragment;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
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
}
