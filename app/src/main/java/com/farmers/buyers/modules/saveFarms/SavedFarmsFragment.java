package com.farmers.buyers.modules.saveFarms;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.modules.saveFarms.adapter.SavedFarmsAdapter;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;

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
                    break;
                }
                case SUCCESS: {
                    success();
                    break;
                }
                case ERROR: {
                    error(saveFarmListApiModelDataFetchState.status_message);
                    break;
                }
            }
        });

        saveUnSaveStateMachine.observe(baseActivity, saveUnsaveFarmApiModelDataFetchState -> {

            switch (saveUnsaveFarmApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(baseActivity, "", Toast.LENGTH_SHORT).show();
                    getSavedFarmList();
                    break;
                }
                case ERROR: {
                    error(saveUnsaveFarmApiModelDataFetchState.status_message);
                    break;
                }
            }
        });


        followUnFollowStateMachine.observe(this, followUnFollowApiModelDataFetchState -> {
            switch (followUnFollowApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    getSavedFarmList();
                    break;
                }
                case ERROR: {
                    error(followUnFollowApiModelDataFetchState.status_message);
                    break;
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
    public void onSaveFarmClicked(String id, int status, String favoriteId) {
        viewModel.saveUnSaveFarm(saveUnSaveStateMachine, id, status, favoriteId);
    }

    @Override
    public void onFollowFarmClicked(String id, String status, String followId) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, id, status, followId);

    }

    @Override
    public void onFarmItemClicked(int position) {

    }
}
