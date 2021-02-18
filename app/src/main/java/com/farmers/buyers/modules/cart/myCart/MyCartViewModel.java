package com.farmers.buyers.modules.cart.myCart;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.farmDetail.FarmDetailRepository;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.remote.StandardError;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class MyCartViewModel extends BaseViewModel {
    private MyCartRepository repository = new MyCartRepository();

    public void validateCoupon(final MutableLiveData<DataFetchState<ApplyCouponResponse>> stateMutableLiveData, ApplyCouponReqParams applyCouponReqParams){
        stateMutableLiveData.postValue(DataFetchState.<ApplyCouponResponse>loading());
        repository.validateCoupanCode(applyCouponReqParams, new ApiResponseCallback<ApplyCouponResponse>() {
            @Override
            public void onSuccess(ApplyCouponResponse response) {
                if (response.getData()!=null)
                    stateMutableLiveData.postValue(DataFetchState.success(response,response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new ApplyCouponResponse()));
            }
            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<ApplyCouponResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

}
