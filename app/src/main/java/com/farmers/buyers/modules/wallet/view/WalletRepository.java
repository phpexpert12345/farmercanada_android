package com.farmers.buyers.modules.wallet.view;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class WalletRepository extends BaseRepository {

    public void getWalletHistoryList(CategoryListRequestParams params, ApiResponseCallback<AllDataModel> responseCallback) {
        Call<AllDataModel> call = RetrofitBuilder.createServiceContract().getWalletHistory(ApiConstants.WALLET_HISTORY,
                params.getUserId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }
}
