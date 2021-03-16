package com.farmers.buyers.modules.saveFarms;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.home.view.HomeItemsViewHolder;
import com.farmers.buyers.modules.saveFarms.adapter.SavedFarmsAdapter;
import com.farmers.buyers.modules.saveFarms.model.SaveFarmListApiModel;
import com.farmers.buyers.modules.saveFarms.model.SaveUnsaveFarmApiModel;
import com.farmers.buyers.remote.ApiConstants;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:42
 * mohammadsajjad679@gmail.com
 */

public class SavedFarmsFragment extends BaseFragment implements HomeItemsViewHolder.FarmItemClickListener {
    private RecyclerView recyclerView;
    private SavedFarmsAdapter adapter;
    private TextView txt_no_saved_farms;
    LinearLayout ll_data_not_available;
    ImageView saved_back;
    TextView text_saved_farms;
    HomeActivity homeActivity;
    int flag=0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        homeActivity= (HomeActivity) context;
    }

    private AppController appController = AppController.get();


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
        txt_no_saved_farms=view.findViewById(R.id.txt_no_saved_farms);
        ll_data_not_available=view.findViewById(R.id.ll_data_not_available);
        saved_back=view.findViewById(R.id.saved_back);
        text_saved_farms=view.findViewById(R.id.text_saved_farms);
        text_saved_farms.setText("Saved Farms");
        saved_back.setOnClickListener(v->{
            homeActivity.onBackPressed();
        });
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
                    flag=1;
                    loading();
                    break;
                }
                case SUCCESS: {
dismissLoader();
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
                    flag=1;
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
        ll_data_not_available.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        bindAdapter();

    }

    private void error(String error) {
        dismissLoader();
        ll_data_not_available.setVisibility(View.VISIBLE);
        txt_no_saved_farms.setText(error);
        recyclerView.setVisibility(View.GONE);
    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }


    @Override
    public void onSaveFarmClicked(String id, int status, String favoriteId) {
        stateMachine.postValue(DataFetchState.loading());
        String url= ApiConstants.BASE_URL+ ApiConstants.SAVE_UN_SAVE_FARM+"?LoginId="+appController.getLoginId()+"&auth_key="+appController.getAuthenticationKey()+"&farm_id="+id+"&farm_favourite_status="+status+"&favourite_id="+favoriteId;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
if(response!=null){
    saveUnSaveStateMachine.postValue(DataFetchState.success(new SaveUnsaveFarmApiModel(), ""));
}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                saveUnSaveStateMachine.postValue(DataFetchState.error("", new SaveUnsaveFarmApiModel()));
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
//        viewModel.saveUnSaveFarm(saveUnSaveStateMachine, id, status, favoriteId);
    }

    @Override
    public void onFollowFarmClicked(String id, String status, String followId) {
        viewModel.followUnFollowFarm(followUnFollowStateMachine, id, status, followId);

    }

    @Override
    public void onFarmItemClicked(int position) {

    }
}
