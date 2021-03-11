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
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.CategoryList;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.SubProductItemsRecord;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
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
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();


    public void getFarmProductList(MutableLiveData<DataFetchState<FarmListProductResponse>> stateMutableLiveData,
                                   FarmProductListReq farmProductListReq,String type) {
        if(type.equalsIgnoreCase("view")) {
            stateMutableLiveData.postValue(DataFetchState.<FarmListProductResponse>loading());
        }
        repository.getFarmProductList(farmProductListReq, new ApiResponseCallback<FarmListProductResponse>() {
            @Override
            public void onSuccess(FarmListProductResponse response) {
                items.clear();
                Intent intent = farmProductListReq.getContext().getIntent();
                String farm_followed_status="";
                String favourite_id="";

                if(response.getData().getCategoryList().size()>0){
                    for(int i=0;i<response.getData().getCategoryList().size();i++){
                        CategoryList categoryList=response.getData().getCategoryList().get(i);
                        if(categoryList.getCategoryItemAvailable().equalsIgnoreCase("Yes")){
                            SubProductItemsRecord subProductItemsRecord=categoryList.getSubProductItemsRecord().get(0);
                            farm_followed_status=subProductItemsRecord.getFarm_followed_status();
                            favourite_id=subProductItemsRecord.getFollowed_id();
                            break;

                        }
                    }
                }
                items.add(FarmDetailTransformer.getHeaderItems(intent.getStringExtra("FARM_ADDRESS"),
                        intent.getStringExtra("farm_image"),
                        intent.getStringExtra("farm_cover_image"),
                        farm_followed_status,
                        favourite_id));

                items.add(FarmDetailTransformer.getFarmDetailItems(intent.getStringExtra("FARM_NAME"),
                        intent.getStringExtra("FARM_ADDRESS"), intent.getStringExtra("RATING_ANG"),
                        intent.getStringExtra("farm_opening_hours"),
                        intent.getStringExtra("farm_estimate_delivery_time"),
                        intent.getStringExtra("farm_followed_status"),
                        intent.getStringExtra("farm_delivery_radius_text"),
                        intent.getStringExtra("farm_hosted_by"),
                        intent.getStringExtra("farm_image"),
                        intent.getStringExtra("FARM_ID"),intent.getDoubleExtra("farm_lat",0.0),intent.getDoubleExtra("farm_long",0.0),intent.getStringExtra("delivery_available"),intent.getStringExtra("pickup_available")));

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

    public void followUnFollowFarm(MutableLiveData<DataFetchState<FollowUnFollowApiModel>> stateMachine, String farmId,
                                   String status, String followId) {
        stateMachine.postValue(DataFetchState.loading());

        FollowUnFollowRequestParams params = new FollowUnFollowRequestParams(
                farmId,
                appController.getLoginId(),
                appController.getAuthenticationKey(),
                status, followId);

        repository.followUnFollowFarm(params, new ApiResponseCallback<FollowUnFollowApiModel>() {
            @Override
            public void onSuccess(FollowUnFollowApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new FollowUnFollowApiModel()));

            }
        });
    }

}