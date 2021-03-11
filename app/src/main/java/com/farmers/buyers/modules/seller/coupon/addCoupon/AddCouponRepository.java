package com.farmers.buyers.modules.seller.coupon.addCoupon;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponRequestParams;
import com.farmers.buyers.modules.seller.coupon.odel.EditCouponRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddCouponRepository extends BaseRepository {


    public void addCoupon(AddCouponRequestParams params, ApiResponseCallback<AddCouponApiModel> responseCallback) {
        Call<AddCouponApiModel> call = RetrofitBuilder.createServiceContract().addCoupon(ApiConstants.ADD_COUPON, params.getCouponCode(), params.getDiscountType(), params.getDiscountAmount(), params.getMinimumOrder(), params.getTermsCondition(), params.getStartDate(), params.getEndDate(), params.getFarmId(), params.getLoginId(), params.getAuthKey());
        makeRequest(call, responseCallback);
    }

    public void editCoupon(EditCouponRequestParams params, ApiResponseCallback<AddCouponApiModel> responseCallback) {
        Call<AddCouponApiModel> call = RetrofitBuilder.createServiceContract().editCoupon(ApiConstants.EDIT_COUPON,
                params.getCouponCode(),
                params.getDiscountType(),
                params.getAmount(),
                params.getMinimumOrder(),
                params.getTermsCondition(),
                params.getStartDate(),
                params.getEndDate(),
                params.getCouponId(),
                params.getFarmId(),
                params.getLoginId(),
                params.getAuthKey());
        makeRequest(call, responseCallback);
    }


}
