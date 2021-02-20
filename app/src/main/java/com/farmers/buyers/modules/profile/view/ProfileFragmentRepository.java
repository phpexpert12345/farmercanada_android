package com.farmers.buyers.modules.profile.view;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class ProfileFragmentRepository extends BaseRepository {

    public void changeUserType(ProfileRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().changeUserType(ApiConstants.CHANGE_USER_TYPE,
                params.getLoginId(), params.getAccount_type(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
