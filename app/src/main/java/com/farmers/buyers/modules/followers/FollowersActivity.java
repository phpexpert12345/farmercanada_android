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
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private LinearLayout followersErrorLL;
    private TextView errorTv;
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
        followersErrorLL = findViewById(R.id.followers_error_ll);
        errorTv = findViewById(R.id.followers_error_tv);
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
                        success(followersApiModelDataFetchState.data);
                        break;
                    }
                    case ERROR: {
                        error(followersApiModelDataFetchState.status_message);
                        break;
                    }
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
                    getFollowersList();
                    break;
                }
                case ERROR: {
                    error(followUnFollowApiModelDataFetchState.status_message);
                    break;
                }
            }
        });

        getFollowersList();
    }

    private void loading() {
        showLoader();
    }

    private void success(FollowersApiModel msg) {
        dismissLoader();
        if(!msg.getStatus()){
            followersErrorLL.setVisibility(View.VISIBLE);
            errorTv.setText(msg.getStatusMessage());
            recyclerView.setVisibility(View.GONE);
        }
        else {
            followersErrorLL.setVisibility(View.GONE);
            errorTv.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            bindAdapter();
        }
    }

    private void error(String error) {
        dismissLoader();
        followersErrorLL.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.VISIBLE);
        errorTv.setText(error);
        recyclerView.setVisibility(View.GONE);
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
    public void onFollowUnFollowFarmListener(String farmId, String Status, String followId) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, farmId, Status, followId);
    }
}