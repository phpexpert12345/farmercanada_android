package com.farmers.buyers.modules.farmDetail;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
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
                params.getAuth_key(), params.getLoginId(), params.getFarm_id());
        makeRequest(call, responseCallback);
    }

    public void saveToCard(FarmProductListReq params, ApiResponseCallback<FarmListProductResponse> responseCallback) {
        Call<FarmListProductResponse> call = RetrofitBuilder.createServiceContract().addToCart(ApiConstants.ADD_TO_CART,
                params.getAuth_key(),
                params.getFarm_id(),
                params.LoginId,
                params.item_id,
                params.getItem_quantity(),
                params.item_price,
                params.farm_id,
                params.getItem_unit(),
                params.order_type);
        makeRequest(call, responseCallback);
    }

    public void increaseDecrease(IncreaseDecreaseParams params, ApiResponseCallback<IncreaseDecreaseApiModel> apiResponseCallback) {
        Call<IncreaseDecreaseApiModel> call = RetrofitBuilder.createServiceContract().INCREASE_DECREASE_API_MODEL_CALL(ApiConstants.INCREADE_DECREASE_API_URL,
                params.getAuth_key(), params.getCart_id(), params.getOption_type());
        makeRequest(call, apiResponseCallback);
    }

    public void clearAllCartItems(IncreaseDecreaseParams params, ApiResponseCallback<IncreaseDecreaseApiModel> apiResponseCallback) {
        Call<IncreaseDecreaseApiModel> call = RetrofitBuilder.createServiceContract().clearAllCartItems(
                ApiConstants.CLEAR_CART_ITEMS,
                params.getAuth_key(),
                params.getLoginId());
        makeRequest(call, apiResponseCallback);
    }
    public void cartItemLists(CartReqParam params, ApiResponseCallback<CartListResponse> cartResponse) {
        Call<CartListResponse> call = RetrofitBuilder.createServiceContract().CART_LIST_RESPONSE_CALL(
                ApiConstants.CUSTOMER_PRODUCT_CART_LIST_URL,
                params.getAuth_key(),
                params.getLoginId());
        makeRequest(call, cartResponse);
    }

    public void followUnFollowFarm(FollowUnFollowRequestParams params, ApiResponseCallback<FollowUnFollowApiModel> responseCallback) {
        Call<FollowUnFollowApiModel> call = RetrofitBuilder.createServiceContract().followUnFollowFarm(ApiConstants.FOLLOW_UNFOLLOW_USER, params.getLoginId(), params.getAuthKey(), params.getFarmId(), params.getStatus(), params.getFollowId());
        makeRequest(call, responseCallback);

    }
}
