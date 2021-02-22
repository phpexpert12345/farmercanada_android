package com.farmers.buyers.modules.cart.myCart;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxRequestParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.FarmDetailRepository;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.remote.StandardError;
import com.google.gson.Gson;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class MyCartViewModel extends BaseViewModel {
    private MyCartRepository repository = new MyCartRepository();

    public void validateCoupon(final MutableLiveData<DataFetchState<ApplyCouponResponse>> stateMutableLiveData, ApplyCouponReqParams applyCouponReqParams) {
        stateMutableLiveData.postValue(DataFetchState.<ApplyCouponResponse>loading());
        repository.validateCoupanCode(applyCouponReqParams, new ApiResponseCallback<ApplyCouponResponse>() {
            @Override
            public void onSuccess(ApplyCouponResponse response) {
                if (response.getData() != null)
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new ApplyCouponResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<ApplyCouponResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

    public void applyServiceAndTax(final MutableLiveData<DataFetchState<TaxResponse>> stateMutableLiveData, TaxRequestParam taxRequestParam) {
        stateMutableLiveData.postValue(DataFetchState.<TaxResponse>loading());
        repository.applyServiceTax(taxRequestParam, new ApiResponseCallback<TaxResponse>() {
            @Override
            public void onSuccess(TaxResponse response) {
                if (response.getTaxData() != null)
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new TaxResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<TaxResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

    public void getCartListItems(final MutableLiveData<DataFetchState<CartListResponse>> stateMutableLiveData, CartReqParam taxRequestParam) {
        stateMutableLiveData.postValue(DataFetchState.<CartListResponse>loading());
        repository.cartItemLists(taxRequestParam, new ApiResponseCallback<CartListResponse>() {
            @Override
            public void onSuccess(CartListResponse response) {
                if (response!=null) {
                        stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }else {
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<CartListResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

    public void increaseDecrease(final MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> stateMutableLiveData, IncreaseDecreaseParams param) {
        stateMutableLiveData.postValue(DataFetchState.<IncreaseDecreaseApiModel>loading());
        Log.d("MyCartRequest : ",new Gson().toJson(param));
        repository.increaseDecrease(param, new ApiResponseCallback<IncreaseDecreaseApiModel>() {
            @Override
            public void onSuccess(IncreaseDecreaseApiModel response) {
                Log.d("MyCartResponse : ",new Gson().toJson(response));
                if (response.getStatus())
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatus_message()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatus_message(), null));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.error(standardError.getDisplayError(), null));
            }
        });
    }

}
