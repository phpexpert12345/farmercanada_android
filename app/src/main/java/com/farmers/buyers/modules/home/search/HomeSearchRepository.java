package com.farmers.buyers.modules.home.search;


import android.util.Log;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.home.search.model.HomeSearchApiModel;
import com.farmers.buyers.modules.home.search.model.HomeSearchRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchRepository extends BaseRepository {

    public void doSearch(HomeSearchRequestParams params, ApiResponseCallback<HomeSearchApiModel> responseCallback) {
        Call<HomeSearchApiModel> call = RetrofitBuilder.createServiceContract().doSearchProduct(ApiConstants.SEARCH_PRODUCT_ITEM, params.getAuthKey(), params.getSearchQuery(), params.getLoginId());
        String url=ApiConstants.BASE_URL+ApiConstants.SEARCH_PRODUCT_ITEM+"?auth_key="+params.getAuthKey()+"&search_text="+params.getSearchQuery()+"&LoginId="+params.getLoginId();
        Log.i("url",url);
        makeRequest(call, responseCallback);

    }
}
