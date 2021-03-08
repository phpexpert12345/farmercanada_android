package com.farmers.buyers.modules.seller.coupon;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListRequestParams;
import com.farmers.buyers.modules.seller.coupon.odel.EditCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.odel.EditCouponRequestParams;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.DeleteProductRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.google.android.gms.common.api.Api;

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

    public void deleteCoupon(DeleteProductRequestParams params, ApiResponseCallback<DeleteProductApiModel> responseCallback) {
        Call<DeleteProductApiModel> call = RetrofitBuilder.createServiceContract().deleteCoupon(ApiConstants.DELETE_COUPON, params.getProductId(), params.getAuthKey());
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
