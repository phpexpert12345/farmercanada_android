package com.farmers.buyers.modules.seller.coupon.addCoupon;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponRequestParams;
import com.farmers.buyers.remote.StandardError;

/**
 * Created by Mohammad sajjad on 08-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddCouponViewModel extends BaseViewModel {
    private AddCouponRepository repository = new AddCouponRepository();
    private AppController appController = AppController.get();

    public void addCoupon(MutableLiveData<DataFetchState<AddCouponApiModel>> stateMachine, String couponCode, String discountType, String discountAmount, String minimumOrder, String isTermsCondition, String startDate, String endDate) {
        stateMachine.postValue(DataFetchState.loading());

        if (couponCode.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please Select coupon code", new AddCouponApiModel()));
            return;
        }
        if (discountType.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please enter discount type", new AddCouponApiModel()));
            return;
        }
        if (discountAmount.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please enter discount amount", new AddCouponApiModel()));
            return;
        }
        if (minimumOrder.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please enter minimum order", new AddCouponApiModel()));
            return;
        }
        if (isTermsCondition.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please enter terms and condition", new AddCouponApiModel()));
            return;
        }
        if (startDate.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please Select start date ", new AddCouponApiModel()));
            return;
        }
        if (endDate.isEmpty()){
            stateMachine.postValue(DataFetchState.error("Please Select end date", new AddCouponApiModel()));
            return;
        }
        AddCouponRequestParams params = new AddCouponRequestParams(
                couponCode,
                discountType,
                discountAmount,
                minimumOrder,
                isTermsCondition,
                startDate,
                endDate,
                appController.getFarmId(),
                appController.getLoginId(),
                appController.getAuthenticationKey());

        repository.addCoupon(params, new ApiResponseCallback<AddCouponApiModel>() {
            @Override
            public void onSuccess(AddCouponApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new AddCouponApiModel()));
            }
        });
    }
}
