package com.farmers.buyers.modules.cart.myCart;

import android.util.Log;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
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


}
