package com.farmers.buyers.modules.seller.coupon;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 07-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ManageCouponRepository extends BaseRepository {

    public void getCouponList(CouponListRequestParams params, ApiResponseCallback<CouponListApiModel> responseCallback) {
        Call<CouponListApiModel> call = RetrofitBuilder.createServiceContract().getCouponList(ApiConstants.COUPON_LIST, params.getLoginId(), params.getFarmId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

}
