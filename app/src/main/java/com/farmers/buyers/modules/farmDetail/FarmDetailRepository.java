package com.farmers.buyers.modules.farmDetail;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmDetailRepository extends BaseRepository {

    public void getFarmProductList(FarmProductListReq params, ApiResponseCallback<FarmListProductResponse> responseCallback) {
        Call<FarmListProductResponse> call = RetrofitBuilder.createServiceContract().getFarmProductList(ApiConstants.ALL_FARM_PRODUCT_LIST_URL,
                params.getAuth_key(), params.getFarm_id());
        makeRequest(call, responseCallback);
    }

    public void saveToCard(FarmProductListReq params, ApiResponseCallback<FarmListProductResponse> responseCallback) {
        Call<FarmListProductResponse> call = RetrofitBuilder.createServiceContract().addToCart(ApiConstants.ADD_TO_CART,
                params.getAuth_key(),
                params.getFarm_id(),
                params.LoginId,
                params.item_price,
                params.getItem_quantity(),
                params.item_price,
                params.farm_id,
                params.getItem_quantity(),
                params.order_type);
        makeRequest(call, responseCallback);
    }
}
