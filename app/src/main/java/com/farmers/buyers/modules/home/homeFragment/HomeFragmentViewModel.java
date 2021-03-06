
package com.farmers.buyers.modules.home.homeFragment;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.followers.FollowersRepository;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowUnFollowRequestParams;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRepository;
import com.farmers.buyers.modules.forgotPassword.ForgotPasswordRequestParams;

import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.modules.saveFarms.SaveFarmRepository;
import com.farmers.buyers.modules.saveFarms.model.SaveUnSaveFarmRequestModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;

import com.farmers.buyers.remote.StandardError;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends BaseViewModel {

    private HomeFragmentRepository repository = new HomeFragmentRepository();
    private SaveFarmRepository saveFarmRepository = new SaveFarmRepository();
    private FollowersRepository followersRepository = new FollowersRepository();
    private AppController appController = AppController.get();
    public List<RecyclerViewListItem> items = new ArrayList<>();
    public List<RecyclerViewListItem> farmListItems = new ArrayList<>();
    public List<HomeListItem> homeFarmListItem = new ArrayList<>();

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
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_ACCOUNT_TYPE", response.getmData().account_type);
                    SharedPreferenceManager.getInstance().setSharedPreference("USER_MOBILE", response.getmData().login_phone);
                    SharedPreferenceManager.getInstance().setSharedPreference("MOBILE_CODE", response.getmData().login_phone_code);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_FOLLOWERS", response.getmData().Total_followers);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_FOLLOWED", response.getmData().Total_following);
                    SharedPreferenceManager.getInstance().setSharedPreference("TOTAL_MESSAGE_INBOX", response.getmData().Total_Inbox_Message);

                    items.add(HomeTransformer.getHeaderItems(response.getmData().login_name,
                            response.getmData().login_email, response.getmData().account_type_name,
                            response.getmData().account_type));

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

    public void getFarmList(final MutableLiveData<DataFetchState<AddressApiModel>> stateMutableLiveData, FarmListRequest farmListRequest) {
        farmListItems.clear();
        homeFarmListItem.clear();

        stateMutableLiveData.postValue(DataFetchState.<AddressApiModel>loading());

        repository.farmListRequest(farmListRequest, new ApiResponseCallback<AddressApiModel>() {
            @Override
            public void onSuccess(AddressApiModel response) {
                if (response.isStatus()) {
                    farmListItems.addAll(HomeTransformer.getHomeFarmListItem(response.getData().getSubProductItemRecords()));
                    homeFarmListItem.addAll(HomeTransformer.getHomeFarmListItem(response.getData().getSubProductItemRecords()));
                    stateMutableLiveData.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMutableLiveData.postValue(DataFetchState.error(response.getStatus_message(), new AddressApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMutableLiveData.postValue(DataFetchState.<AddressApiModel>error(standardError.getDisplayError(), null));

            }
        });

    }

    public void saveUnSaveFarm(MutableLiveData<DataFetchState<SaveUnsaveFarmApiModel>> stateMachine, String farmId, int status, String favoriteId) {
        stateMachine.postValue(DataFetchState.loading());

        SaveUnSaveFarmRequestModel params = new SaveUnSaveFarmRequestModel(farmId, appController.getLoginId(), status, appController.getAuthenticationKey(), favoriteId);

        saveFarmRepository.saveUnSaveFarm(params, new ApiResponseCallback<SaveUnsaveFarmApiModel>() {
            @Override
            public void onSuccess(SaveUnsaveFarmApiModel response) {
                stateMachine.postValue(DataFetchState.success(new SaveUnsaveFarmApiModel(), ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new SaveUnsaveFarmApiModel()));

            }
        });
    }

    public void followUnFollowFarm(MutableLiveData<DataFetchState<FollowUnFollowApiModel>> stateMachine, String farmId, String status, String followId) {
        stateMachine.postValue(DataFetchState.loading());

        FollowUnFollowRequestParams params = new FollowUnFollowRequestParams(farmId, appController.getLoginId(), appController.getAuthenticationKey(), status, followId);

        followersRepository.followUnFollowFarm(params, new ApiResponseCallback<FollowUnFollowApiModel>() {
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

    public void changeUserType(final MutableLiveData<DataFetchState<AllDataModel>> stateMachine, ProfileRequestParams profileRequestParams) {
        stateMachine.postValue(DataFetchState.<AllDataModel>loading());
        repository.changeUserType(profileRequestParams, new ApiResponseCallback<AllDataModel>() {
            @Override
            public void onSuccess(AllDataModel response) {
                if (response.isStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatus_message()));
                } else {
                    stateMachine.postValue(DataFetchState.error(response.getStatus_message(), null));
                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.<AllDataModel>error(standardError.getDisplayError(), null));
            }
        });
    }
}
