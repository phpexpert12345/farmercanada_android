package com.farmers.buyers.modules.followers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.adapter.FollowersAdapter;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.followers.model.FollowersApiModel;
import com.farmers.buyers.modules.followers.view.FollowersViewHolder;
import com.farmers.buyers.modules.login.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends BaseActivity implements FollowersViewHolder.FollowerListener {
    private RecyclerView recyclerView;
    private FollowersAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(FollowersViewModel.class)) {
                return (T) new FollowersViewModel();
            }
            return null;
        }
    };

    private FollowersViewModel viewModel = factory.create(FollowersViewModel.class);
    private MutableLiveData<DataFetchState<FollowersApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FollowUnFollowApiModel>> followUnFollowStateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ToolbarConfig config = new ToolbarConfig("Followers", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        setupToolbar(config);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.followers_transformer);
        adapter = new FollowersAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateMachine.observe(this, new Observer<DataFetchState<FollowersApiModel>>() {
            @Override
            public void onChanged(DataFetchState<FollowersApiModel> followersApiModelDataFetchState) {
                switch (followersApiModelDataFetchState.status) {
                    case LOADING: {
                        loading();
                        break;
                    }
                    case SUCCESS: {
                        success();
                    }
                    case ERROR: {
                        error(followersApiModelDataFetchState.status_message);
                    }
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
                        getFollowersList();
                    }
                    case ERROR: {
                        error(followUnFollowApiModelDataFetchState.status_message);
                    }
                }
            }
        });

        getFollowersList();
    }

    private void loading() {
        showToolbar();
    }

    private void success() {
        dismissLoader();
        bindAdapter();
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void getFollowersList() {
        viewModel.getFollowers(stateMachine);
    }

    @Override
    public void onFollowUnFollowFarmListener(String farmId, String Status) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, farmId, Status);
    }
}