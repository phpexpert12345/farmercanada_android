package com.farmers.buyers.modules.cart.order;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.remote.StandardError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/19/2021.
 */
public class SubmitOrderViewModel extends BaseViewModel {
    private SubmitOrderRepository repository = new SubmitOrderRepository();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void getOrderDate(final MutableLiveData<DataFetchState<AddressApiModel>> stateMutableLiveData,
                             SubmitRequestParam requestParam) {
        stateMutableLiveData.postValue(DataFetchState.loading());
        repository.getOrderDate(requestParam, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    items.clear();
                    items.add(new SimpleTitleItem("Choose delivery slot for this address"));
                    items.add(MyCartTransformer.getPlaceOrderSlot(response.getData().getAllDateList()));
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatus_message(), new AddressApiModel()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));

            }
        });
    }

    public void getOrderTimeByDate(final MutableLiveData<DataFetchState<AddressApiModel>> stateMutableLiveData,
                                   SubmitRequestParam requestParam) {
        stateMutableLiveData.postValue(DataFetchState.loading());
        repository.getOrderTimeByDate(requestParam, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatus_message(), new AddressApiModel()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));

            }
        });
    }

    public void submitOrder(final MutableLiveData<DataFetchState<SubmitResponse>> stateMutableLiveData,
                            SubmitRequestParam requestParam) {
        stateMutableLiveData.postValue(DataFetchState.loading());
        repository.submitOrder(requestParam, new ApiResponseCallback<SubmitResponse>() {
            @Override
            public void onSuccess(SubmitResponse response) {
                if (response.getData() != null)
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new SubmitResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<SubmitResponse>error(standardError.getDisplayError(), null));

            }
        });

    }

    public void getCartListItems(final MutableLiveData<DataFetchState<CartListResponse>> stateMutableLiveData, CartReqParam taxRequestParam) {

        repository.cartItemLists(taxRequestParam, new ApiResponseCallback<CartListResponse>() {
            @Override
            public void onSuccess(CartListResponse response) {
                if (response.getStatus()) {
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                } else {
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<CartListResponse>error(standardError.getDisplayError(), null));
            }
        });
    }
}
