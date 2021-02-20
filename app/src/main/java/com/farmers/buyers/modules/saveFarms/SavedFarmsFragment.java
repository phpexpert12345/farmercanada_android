package com.farmers.buyers.modules.saveFarms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.modules.login.LoginViewModel;
import com.farmers.buyers.modules.saveFarms.adapter.SavedFarmsAdapter;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:42
 * mohammadsajjad679@gmail.com
 */

public class SavedFarmsFragment extends BaseFragment implements HomeItemsViewHolder.FarmItemClickListener {
    private RecyclerView recyclerView;
    private SavedFarmsAdapter adapter;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SavedFarmListViewModel.class)) {
                return (T) new SavedFarmListViewModel();
            }
            return null;
        }
    };

    private SavedFarmListViewModel viewModel = factory.create(SavedFarmListViewModel.class);
    private MutableLiveData<DataFetchState<SaveFarmListApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<SaveUnsaveFarmApiModel>> saveUnSaveStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FollowUnFollowApiModel>> followUnFollowStateMachine = new MutableLiveData<>();


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getResourceFile() {
        return R.layout.saved_farms_fragment;
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.saved_farms_recyclerView);
        adapter = new SavedFarmsAdapter(this);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.GRID));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);


        stateMachine.observe(baseActivity, saveFarmListApiModelDataFetchState -> {
            switch (saveFarmListApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                }
                case SUCCESS: {
                    success();
                }
                case ERROR: {
                    error(saveFarmListApiModelDataFetchState.status_message);
                }
            }
        });

        saveUnSaveStateMachine.observe(baseActivity, saveUnsaveFarmApiModelDataFetchState -> {

            switch (saveUnsaveFarmApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                }
                case SUCCESS: {
                    dismissLoader();
                }
                case ERROR: {
                    error(saveUnsaveFarmApiModelDataFetchState.status_message);
                }
            }
        });


        followUnFollowStateMachine.observe(this, new Observer<DataFetchState<FollowUnFollowApiModel>>() {
            @Override
            public void onChanged(DataFetchState<FollowUnFollowApiModel> followUnFollowApiModelDataFetchState) {
                switch (followUnFollowApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }
                    case SUCCESS: {
                        dismissLoader();
                        getSavedFarmList();
                    }
                    case ERROR: {
                        error(followUnFollowApiModelDataFetchState.status_message);
                    }
                }
            }
        });


        getSavedFarmList();

    }

    private void getSavedFarmList(){
        viewModel.getSavedFarmList(stateMachine);

    }

    private void loading() {
     showLoader();
    }

    private void success() {
        dismissLoader();
        bindAdapter();

    }

    private void error(String error) {
        dismissLoader();

    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }


    @Override
    public void onSaveFarmClicked(String id, int status) {
        viewModel.saveUnSaveFarm(saveUnSaveStateMachine, id, status);
        getSavedFarmList();
    }

    @Override
    public void onFollowFarmClicked(String id, String status) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, id, status);

    }
}
