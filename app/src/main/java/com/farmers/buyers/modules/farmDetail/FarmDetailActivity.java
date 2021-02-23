package com.farmers.buyers.modules.farmDetail;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;

public class FarmDetailActivity extends BaseActivity implements HomeHeaderViewHolder.HeaderItemClickListener,
        FarmDetailHeaderViewHolder.FarmHeaderClickListener, FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener {
    private RecyclerView recyclerView;
    private FarmDetailsAdapter adapter;
    public String farm_id;
    private AppController appController = AppController.get();

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(FarmDetailViewModel.class)) {
                return (T) new FarmDetailViewModel();
            }
            return null;
        }
    };

    private FarmDetailViewModel viewModel = factory.create(FarmDetailViewModel.class);

    private MutableLiveData<DataFetchState<FarmListProductResponse>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FarmListProductResponse>> addToCartStateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.farmers_detail_recyclerView);
        adapter = new FarmDetailsAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    dismissLoader();
                    adapter.updateData(viewModel.items);
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    dismissLoader();
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        addToCartStateMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    dismissLoader();
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    dismissLoader();
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        getFarmProductDetail();
    }

    private void getFarmProductDetail() {
        FarmProductListReq farmProductListReq = new FarmProductListReq(FarmDetailActivity.this,
                appController.getAuthenticationKey(), getIntent().getStringExtra("FARM_ID"));
        viewModel.getFarmProductList(stateMachine, farmProductListReq);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onEditAddressClickListener(int position) {
    }

    @Override
    public void onBecomeSellerClicked() {

    }

    @Override
    public void onOnBackClickListener() {
        onBackPressed();
    }

    @Override
    public void onFollowClickListener(String id) {
        Toast.makeText(FarmDetailActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFarmDetailVegetableListener(FarmDetailsVegetableItems item) {
        FarmProductListReq farmProductListReq = new FarmProductListReq(appController.getAuthenticationKey(),
                getIntent().getStringExtra("FARM_ID"),
                appController.getLoginId(),
                item.getPrice(),
                getIntent().getStringExtra("FARM_ID"),
                item.getPrice(),
                "",
                getIntent().getStringExtra("FARM_ID"),
                item.product_code);
        viewModel.addToCartItems(addToCartStateMachine, farmProductListReq);
    }
}