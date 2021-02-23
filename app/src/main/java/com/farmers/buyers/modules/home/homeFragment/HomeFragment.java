package com.farmers.buyers.modules.home.homeFragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.view.MultipleTextItemViewHolder;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.adapter.HomeAdapter;
import com.farmers.buyers.modules.home.adapter.HomeFarmListAdapter;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;
import com.farmers.buyers.modules.home.models.farmList.FarmListRequest;
import com.farmers.buyers.modules.home.models.farmList.FarmListResponse;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.modules.home.view.HomeCategoryListItemViewHolder;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;
import com.farmers.buyers.modules.home.view.HomeFarmTypeViewHolder;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;

import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.farmers.buyers.app.App.getAppContext;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 15:09
 * mohammadsajjad679@gmail.com
 */

public class HomeFragment extends BaseFragment implements HomeHeaderViewHolder.HeaderItemClickListener,
        MultipleTextItemViewHolder.FilterItemClickListener, HomeItemsViewHolder.FarmItemClickListener, HomeCategoryListItemViewHolder.CategoryItemClickListener, HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener, HomeFarmTypeViewHolder.FarmTypeCheckedChangeListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
                return (T) new HomeFragmentViewModel();
            }
            return null;
        }
    };

    public HomeFragmentViewModel viewModel = factory.create(HomeFragmentViewModel.class);
    private MutableLiveData<DataFetchState<LoginApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FarmListResponse>> farmListStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<SaveUnsaveFarmApiModel>> saveUnSaveStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FollowUnFollowApiModel>> followUnFollowStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> categoryStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> offerStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> getUserStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> changeUserStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    public GPSTracker gpsTracker;
    private RecyclerView recyclerView, farmRecyclerView;
    private HomeAdapter adapter;
    private HomeFarmListAdapter homeFarmListAdapter;




    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getResourceFile() {
        return R.layout.home_fragment;
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.home_recyclerView);
        farmRecyclerView = view.findViewById(R.id.home_farm_item_recyclerView);

        init();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        getUserData();
    }

    private void farmListDataRequest(String farmType, String serviceType, String categoryId, int page) {


        FarmListRequest farmListRequest=new FarmListRequest(appController.getAuthenticationKey(), gpsTracker.getLatitude(),gpsTracker.getLongitude(),gpsTracker.getAddressLine(baseActivity),gpsTracker.getAdminArea(baseActivity),farmType,serviceType,categoryId,String.valueOf(page),AppController.get().getLoginId());
        viewModel.getFarmList(farmListStateMachine,farmListRequest);
    }

    private void init() {

        adapter = new HomeAdapter(this, this, this, this, this);
        gpsTracker = new GPSTracker(getAppContext());
        SharedPreferenceManager.getInstance().setSharedPreference("Current_Location", gpsTracker.getAddressLine(getAppContext()));
        homeFarmListAdapter = new HomeFarmListAdapter(this);
        recyclerView.setAdapter(adapter);
        farmRecyclerView.setAdapter(homeFarmListAdapter);
        farmRecyclerView.setLayoutManager(new GridLayoutManager(baseActivity, 2));
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));
        farmRecyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemAt(position) instanceof HomeHeaderItem ||
                        adapter.getItemAt(position) instanceof HomeSearchListItem ||
                        adapter.getItemAt(position) instanceof HomeCategoryListItem ||
                        adapter.getItemAt(position) instanceof SimpleTitleItem ||
                        adapter.getItemAt(position) instanceof HomeTopOffersListItems ||
                        adapter.getItemAt(position) instanceof HomeFilterListItems ||
                        adapter.getItemAt(position) instanceof DeliveryTypeItems ||
                        adapter.getItemAt(position) instanceof HomeFarmTypeItem) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(manager);

        farmListStateMachine.observe(this,new Observer<DataFetchState<FarmListResponse>>(){
            @Override
            public void onChanged(DataFetchState<FarmListResponse> farmListResponseDataFetchState) {
                switch (farmListResponseDataFetchState.status){
                    case ERROR:
                        dismissLoader();
                        Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        dismissLoader();
                        homeFarmListAdapter.updateData(viewModel.farmListItems);
                        break;
                    case LOADING:
                        showLoader();
                        break;

                }
            }
        });

        getUserStateMachine.observe(this, allDataModelDataFetchState -> {
            switch (allDataModelDataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(getContext(), allDataModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    // showLoader();
                    break;
                }
                case SUCCESS: {
                    getCategoryData();
                    break;
                }
            }
        });

        categoryStateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(getContext(), dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    categorySuccess();
                    break;
                }
            }
        });

        offerStateMachine.observe(this, allDataModelDataFetchState -> {
            switch (allDataModelDataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    break;
                }
                case LOADING: {
                    //showLoader();
                    break;
                }
                case SUCCESS: {
                    getUserSuccess();
                    farmListDataRequest("","","",0);
                    break;
                }
            }
        });

        saveUnSaveStateMachine.observe(baseActivity, saveUnsaveFarmApiModelDataFetchState -> {

            switch (saveUnsaveFarmApiModelDataFetchState.status) {
                case LOADING: {
                    showLoader();
                }
                case SUCCESS: {
                    dismissLoader();
                }
                case ERROR: {
                    dismissLoader();
                }
            }
        });


        changeUserStateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(getContext(), dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(getContext(), dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    SharedPreferenceManager.getInstance().clearUserInfo();
                    startActivity(new Intent(baseActivity, LoginActivity.class));
                    baseActivity.finish();
                    break;
                }
            }
        });


        followUnFollowStateMachine.observe(this, new Observer<DataFetchState<FollowUnFollowApiModel>>() {
            @Override
            public void onChanged(DataFetchState<FollowUnFollowApiModel> followUnFollowApiModelDataFetchState) {
                switch (followUnFollowApiModelDataFetchState.status) {
                    case LOADING: {
                        showLoader();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                    }
                    case ERROR: {
                        dismissLoader();
                        Toast.makeText(baseActivity, followUnFollowApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getUserSuccess() {
        dismissLoader();
        bindAdapter();
    }

    public void categorySuccess() {
        getOfferData();
    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }

    private void getCategoryData() {
        viewModel.getCategoryList(categoryStateMachine);
    }

    private void getOfferData() {
        viewModel.getOffersList(offerStateMachine);
    }

    private void getUserData() {
        viewModel.getUserInformation(getUserStateMachine);
    }


    @Override
    public void onEditAddressClickListener(int position) {
        Log.e("position", String.valueOf(position));
    }


    public void buyer_seller_switch_dialog(Context activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.buyer_seller_switch_dialog);
        RadioGroup radioGroup = dialog.findViewById(R.id.user_type_radio_group);
        TextView tv_user_type = dialog.findViewById(R.id.tv_user_type);
        RadioButton radio_seller, radio_buyer;
        radio_seller = dialog.findViewById(R.id.radio_seller);
        radio_buyer = dialog.findViewById(R.id.radio_buyer);
        tv_user_type.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_TYPE", "")));

        if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_TYPE", "")).
                equalsIgnoreCase("Seller")) {
            radio_seller.setChecked(true);
        } else {
            radio_buyer.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (radioGroup1.getCheckedRadioButtonId()) {
                case R.id.radio_seller: {//Buyer = 1 & Seller = 2
                    ProfileRequestParams profileRequestParams = new ProfileRequestParams("2",
                            appController.getLoginId(), appController.getAuthenticationKey());
                    viewModel.changeUserType(changeUserStateMachine, profileRequestParams);

                    dialog.dismiss();
                    break;
                }
                case R.id.radio_buyer: {
                    //   Toast.makeText(getContext(), "Buyer", Toast.LENGTH_SHORT).show();
                    ProfileRequestParams profileRequestParams = new ProfileRequestParams("1",
                            appController.getLoginId(), appController.getAuthenticationKey());
                    viewModel.changeUserType(changeUserStateMachine, profileRequestParams);
                    dialog.dismiss();
                    break;
                }
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    @Override
    public void onBecomeSellerClicked() {
        buyer_seller_switch_dialog(getContext());
    }

    @Override
    public void onFilterItemClicked(int position) {
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
    }


    @Override
    public void onSaveFarmClicked(String id, int status) {
        viewModel.saveUnSaveFarm(saveUnSaveStateMachine, id, status);
    }

    @Override
    public void onFollowFarmClicked(String id, String status) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, id, status);

    }

    @Override
    public void onFarmItemClicked(int position) {
        Intent intent = new Intent(baseActivity, FarmDetailActivity.class);
        intent.putExtra("farmData", viewModel.homeFarmListItem.get(position).toString());
        startActivity(intent);
    }

    @Override
    public void onCategoryItemClicked(String id, int position) {
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
        farmListDataRequest("", "", id, 0);
    }

    @Override
    public void onDeliveryTypeCheckedChangeListener(int type) {
        farmListDataRequest("", String.valueOf(type), "", 0);
    }

    @Override
    public void onFarmTypeCheckedChangeListener(int farmType) {
        farmListDataRequest(String.valueOf(farmType), "", "", 0);
    }
}
