package com.farmers.buyers.modules.home.homeFragment;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.view.MultipleTextItemViewHolder;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.adapter.HomeAdapter;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;
import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;

import static com.farmers.buyers.app.App.getAppContext;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 15:09
 * mohammadsajjad679@gmail.com
 */

public class HomeFragment extends BaseFragment implements HomeHeaderViewHolder.HeaderItemClickListener,
        MultipleTextItemViewHolder.FilterItemClickListener, HomeItemsViewHolder.FarmItemClickListener {

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
    private MutableLiveData<DataFetchState<AllDataModel>> categoryStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> offerStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllDataModel>> getUserStateMachine = new MutableLiveData<>();
    public GPSTracker gpsTracker;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

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
        init();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        getUserData();
    }

    private void init() {
        gpsTracker = new GPSTracker(getAppContext());
        SharedPreferenceManager.getInstance().setSharedPreference("Current_Location", gpsTracker.getAddressLine(getAppContext()));
        adapter = new HomeAdapter(HomeFragment.this, this, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));

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
                    break;
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

    @Override
    public void onBecomeSellerClicked() {

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.buyer_seller_switch_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onFilterItemClicked(int position) {
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onSaveFarmClicked(String id, int status) {

    }
}