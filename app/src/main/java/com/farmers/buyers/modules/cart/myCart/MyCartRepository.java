package com.farmers.buyers.modules.cart.myCart;

import android.util.Log;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxRequestParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.google.gson.Gson;

import retrofit2.Call;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class MyCartRepository extends BaseRepository {

    public void validateCoupanCode(ApplyCouponReqParams params, ApiResponseCallback<ApplyCouponResponse> responseCallback) {
        Call<ApplyCouponResponse> call = RetrofitBuilder.createServiceContract().APPLY_COUPON_RESPONSE_CALL(ApiConstants.APPLY_COUPON_URL,
                params.getAuth_key(),params.getFarm_id(),params.getCoupon_code(),Integer.parseInt(params.getSubtotal_amount()));
        makeRequest(call, responseCallback);

    }

    public void applyServiceTax(TaxRequestParam params, ApiResponseCallback<TaxResponse> responseCallback) {
        Call<TaxResponse> call = RetrofitBuilder.createServiceContract().TAX_RESPONSE_CALL(ApiConstants.SERVICE_AND_TAX_URL,
                params.getAuth_key(),params.getFarm_id(),params.getDelivery_distance(),params.getOrder_type(),params.getSubtotal_amount());
        makeRequest(call, responseCallback);

    }

 public void cartItemLists(CartReqParam params, ApiResponseCallback<CartListResponse> cartResponse) {
        Call<CartListResponse> call = RetrofitBuilder.createServiceContract().CART_LIST_RESPONSE_CALL(ApiConstants.CUSTOMER_PRODUCT_CART_LIST_URL,
                params.getAuth_key(),params.getLoginId(),params.getFarm_id());
        makeRequest(call, cartResponse);

    }

    public void increaseDecrease(IncreaseDecreaseParams params, ApiResponseCallback<IncreaseDecreaseApiModel> apiResponseCallback){
        Call<IncreaseDecreaseApiModel> call=RetrofitBuilder.createServiceContract().INCREASE_DECREASE_API_MODEL_CALL(ApiConstants.INCREADE_DECREASE_API_URL,
                params.getAuth_key(),params.getCart_id(),params.getOption_type());
        makeRequest(call,apiResponseCallback);
    }


}
