package com.farmers.buyers.modules.home.search;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.FarmDetailRepository;
import com.farmers.buyers.modules.farmDetail.FarmDetailTransformer;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.CategoryList;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.homeFragment.CategoryListRequestParams;
import com.farmers.buyers.modules.home.search.model.HomeSearchApiModel;
import com.farmers.buyers.modules.home.search.model.HomeSearchCategoryList;
import com.farmers.buyers.modules.home.search.model.HomeSearchRequestParams;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchViewModel extends BaseViewModel {
    private HomeSearchRepository repository = new HomeSearchRepository();
    private FarmDetailRepository farmDetailrepository = new FarmDetailRepository();

    private AppController appController = AppController.get();
    List<RecyclerViewListItem> items = new ArrayList<>();



    public void addToCartItems(MutableLiveData<DataFetchState<FarmListProductResponse>> stateMutableLiveData,
                               FarmProductListReq farmProductListReq) {
        stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>loading());
        farmDetailrepository.saveToCard(farmProductListReq, new ApiResponseCallback<FarmListProductResponse>() {
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
        farmDetailrepository.increaseDecrease(param, new ApiResponseCallback<IncreaseDecreaseApiModel>() {
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

    public void doSearch(MutableLiveData<DataFetchState<HomeSearchApiModel>> stateMachine, String searchQuery) {
        items.clear();

        stateMachine.postValue(DataFetchState.loading());

        if (searchQuery.isEmpty()) {
            stateMachine.postValue(DataFetchState.error("Please enter some text to search", new HomeSearchApiModel()));
            return;
        }

        HomeSearchRequestParams params = new HomeSearchRequestParams(appController.getAuthenticationKey(), searchQuery, appController.getLoginId());

        repository.doSearch(params, new ApiResponseCallback<HomeSearchApiModel>() {
            @Override
            public void onSuccess(HomeSearchApiModel response) {
                if (response.getStatus()) {
                    if (!response.getData().getCategoryList().isEmpty()) {
                        for (int i = 0; i < response.getData().getCategoryList().size(); i++) {
                            HomeSearchCategoryList currentList = response.getData().getCategoryList().get(i);
                            if (!currentList.getSubProductItemsRecord().isEmpty()) {
                                items.add(new SingleTextItem(currentList.getCategoryName()));
                                items.add(HomeTransformer.transformApiModelToHomeSearchItems(currentList.getSubProductItemsRecord()));
                            }
                        }
                        stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));

                    }
                    else {
                        stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new HomeSearchApiModel()));
                    }
                } else
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new HomeSearchApiModel()));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new HomeSearchApiModel()));
            }
        });
    }




}
