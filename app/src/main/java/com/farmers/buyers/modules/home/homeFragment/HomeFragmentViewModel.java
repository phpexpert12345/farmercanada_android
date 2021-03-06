
package com.farmers.buyers.modules.home.homeFragment;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;

import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends BaseViewModel {

    private HomeFragmentRepository repository = new HomeFragmentRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();

    private void prepareListItems() {
        //  items.add(HomeTransformer.getCategoryList());
        //  items.add(HomeTransformer.getTopOffers());
        items.add(new DeliveryTypeItems());
        items.add(new HomeFarmTypeItem());
        items.addAll(HomeTransformer.getHomeFarmListItem());
    }

    public void getCategoryList(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<AllDataModel>loading());

        //   items.add(HomeTransformer.getHeaderItems());
        items.add(HomeTransformer.getSearchItems());
        items.add(HomeTransformer.getFilterItems());

        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getAuthenticationKey());
        repository.getCategoryList(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    items.add(HomeTransformer.getCategoryList(response.getmData().CategoryList));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.<AllDataModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getOffersList(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getAuthenticationKey());
        repository.getOffersList(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    items.add(new SimpleTitleItem("Top Offers"));
                    items.add(HomeTransformer.getTopOffers(response.getmData().getBannerList));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));

                    items.add(new DeliveryTypeItems());
                    items.add(new HomeFarmTypeItem());
                    items.addAll(HomeTransformer.getHomeFarmListItem());

                } else {
                    stateMachine.postValue(DataFetchState.<AllDataModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getUserInformation(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine) {

        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        CategoryListRequestParams loginRequestParams = new CategoryListRequestParams(appController.getLoginId(),
                appController.getAuthenticationKey());
        repository.getUserInformation(loginRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {

                    SharedPreferenceManager.getInstance().setWalletAmount(response.getmData().wallet_amount);
                    SharedPreferenceManager.getInstance().setProfilePic(response.getmData().login_photo);
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_NAME", response.getmData().login_name);
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_EMAIL", response.getmData().login_email);
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_TYPE", response.getmData().account_type_name);
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_MOBILE", response.getmData().login_phone);
                    SharedPreferenceManager.getInstance().setSharedPreference("MOBILE_CODE", response.getmData().login_phone_code);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_FOLLOWERS", response.getmData().Total_followers);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_FOLLOWED", response.getmData().Total_following);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_MESSAGE_INBOX", response.getmData().Total_Inbox_Message);

                    items.add(HomeTransformer.getHeaderItems(response.getmData().login_name,
                            response.getmData().login_email, response.getmData().account_type_name));

                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));

                } else {
                    stateMachine.postValue(DataFetchState.<AllDataModel>error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }

    public void getFarmList(final MutableLiveData<DataFetchState<FarmListResponse>> stateMutableLiveData,FarmListRequest farmListRequest){
        stateMutableLiveData.postValue(DataFetchState.<FarmListResponse>loading());
        repository.farmListRequest(farmListRequest, new ApiResponseCallback<FarmListResponse>() {
            @Override
            public void onSuccess(FarmListResponse response) {
                if (response.getFarmData()!=null)
                    stateMutableLiveData.postValue(DataFetchState.success(response,response.getStatusMessage()));
                else
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatusMessage(), new FarmListResponse()));
            }
            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<FarmListResponse>error(standardError.getDisplayError(), null));

            }
        });

    }
}
