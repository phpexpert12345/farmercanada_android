package com.farmers.buyers.modules.farmDetail;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.CategoryList;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentRepository;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.remote.StandardError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/17/2021.
 */
public class FarmDetailViewModel extends BaseViewModel {

    private FarmDetailRepository repository = new FarmDetailRepository();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    public void getFarmProductList(MutableLiveData<DataFetchState<FarmListProductResponse>> stateMutableLiveData,
                                   FarmProductListReq farmProductListReq) {
        stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>loading());
        repository.getFarmProductList(farmProductListReq, new ApiResponseCallback<FarmListProductResponse>() {
            @Override
            public void onSuccess(FarmListProductResponse response) {
                items.clear();
                Intent intent = farmProductListReq.getContext().getIntent();
                items.add(FarmDetailTransformer.getHeaderItems(intent.getStringExtra("FARM_ADDRESS"),
                        intent.getStringExtra("farm_image"),
                        intent.getStringExtra("farm_cover_image"),
                        intent.getStringExtra("farm_followed_status"),
                        intent.getStringExtra("followed_id")));

                items.add(FarmDetailTransformer.getFarmDetailItems(intent.getStringExtra("FARM_NAME"),
                        intent.getStringExtra("FARM_ADDRESS"), intent.getStringExtra("RATING_ANG"),
                        intent.getStringExtra("farm_opening_hours"),
                        intent.getStringExtra("farm_estimate_delivery_time"),
                        intent.getStringExtra("farm_followed_status"),
                        intent.getStringExtra("farm_delivery_radius_text"),
                        intent.getStringExtra("farm_hosted_by"),
                        intent.getStringExtra("farm_image")));

                if (response.getStatus()) {
                    if (!response.getData().getCategoryList().isEmpty()) {
                        for (int i = 0; i < response.getData().getCategoryList().size(); i++) {
                            CategoryList currentList = response.getData().getCategoryList().get(i);
                            if (!currentList.getSubProductItemsRecord().isEmpty()) {
                                items.add(new SingleTextItem(currentList.getCategoryName()));
                                items.add(FarmDetailTransformer.getFarmDetailVegList(currentList.getSubProductItemsRecord()));
                            }
                        }
                    }

                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                } else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new FarmListProductResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>error(standardError.getDisplayError(), null));

            }
        });
    }

    public void addToCartItems(MutableLiveData<DataFetchState<FarmListProductResponse>> stateMutableLiveData,
                               FarmProductListReq farmProductListReq) {
        stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>loading());
        repository.saveToCard(farmProductListReq, new ApiResponseCallback<FarmListProductResponse>() {
            @Override
            public void onSuccess(FarmListProductResponse response) {
                if (response.getStatus()) {
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatusMessage()));
                } else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new FarmListProductResponse()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>error(standardError.getDisplayError(), null));

            }
        });
    }

    public void increaseDecrease(final MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> stateMutableLiveData,
                                 IncreaseDecreaseParams param) {
        stateMutableLiveData.postValue(DataFetchState.<IncreaseDecreaseApiModel>loading());
        repository.increaseDecrease(param, new ApiResponseCallback<IncreaseDecreaseApiModel>() {
            @Override
            public void onSuccess(IncreaseDecreaseApiModel response) {
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

    public void clearAllCartItems(final MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> stateMutableLiveData,
                                  IncreaseDecreaseParams param) {
        stateMutableLiveData.postValue(DataFetchState.<IncreaseDecreaseApiModel>loading());
        repository.clearAllCartItems(param, new ApiResponseCallback<IncreaseDecreaseApiModel>() {
            @Override
            public void onSuccess(IncreaseDecreaseApiModel response) {
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
    public void getCartListItems(final MutableLiveData<DataFetchState<CartListResponse>> stateMutableLiveData, CartReqParam taxRequestParam) {
        stateMutableLiveData.postValue(DataFetchState.<CartListResponse>loading());
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