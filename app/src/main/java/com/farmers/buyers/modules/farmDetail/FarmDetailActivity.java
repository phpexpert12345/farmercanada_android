package com.farmers.buyers.modules.farmDetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FarmDetailActivity extends BaseActivity implements HomeHeaderViewHolder.HeaderItemClickListener,
        FarmDetailHeaderViewHolder.FarmHeaderClickListener, FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener, HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener, FarmDetailViewHolder.FarmDetailItemClickListener {
    private RecyclerView recyclerView;
    private FarmDetailsAdapter adapter;
    public String farm_id,pickup_available,delivery_available;
    private AppController appController = AppController.get();
    FarmDetailsVegetableItems veggie;
    int quat;
    int flag=0;
    FloatingActionButton img_go_cart;




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
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> increaseDecreaseMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> clearAllCartItemsMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FollowUnFollowApiModel>> followUnFollowStateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.farmers_detail_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        img_go_cart=findViewById(R.id.img_go_cart);
        pickup_available=getIntent().getStringExtra("pickup_available");
        delivery_available=getIntent().getStringExtra("delivery_available");
        adapter = new FarmDetailsAdapter(this, this, this, this,pickup_available,delivery_available);
        recyclerView.setAdapter(adapter);
        img_go_cart.setOnClickListener(v->{
            App.cart_updated=true;
            finish();
        });
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
                    adapter.updateData(viewModel.items);
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        addToCartStateMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:

                    getFarmProductDetail("allcart");
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case LOADING:

                        showLoader();

                    break;
                case ERROR:
                    dismissLoader();
                    callClearCartDialog(response.status_message);
                    break;
            }
        });

        increaseDecreaseMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    dismissLoader();
                    getFarmProductDetail("add");
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();

                case LOADING:

                        showLoader();

                case ERROR:

                    break;
            }
        });

        clearAllCartItemsMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();

                     AddtoCartItems(veggie,quat);
//                    getFarmProductDetail();
                case LOADING:
                    flag=1;
                    showLoader();
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        followUnFollowStateMachine.observe(this, followUnFollowApiModelDataFetchState -> {
            switch (followUnFollowApiModelDataFetchState.status) {
                case LOADING: {

                    showLoader();
                    break;
                }
                case SUCCESS: {

               /*     Toast.makeText(FarmDetailActivity.this,
                            followUnFollowApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();*/

                    getFarmProductDetail("follow");
                    break;

                }
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(getBaseContext(), followUnFollowApiModelDataFetchState.status_message,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });

        getFarmProductDetail("view");
    }

    private void callClearCartDialog(String msg) {
        new AlertDialog.Builder(FarmDetailActivity.this)
                .setTitle("Farmer Alert")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(),
                            appController.getLoginId());
                    viewModel.clearAllCartItems(clearAllCartItemsMachine, params);
                    dialog.dismiss();
                })
                .setIcon(getResources().getDrawable(R.drawable.logo))
                .show();
    }

    private void getFarmProductDetail(String type) {
        FarmProductListReq farmProductListReq = new FarmProductListReq(FarmDetailActivity.this,
                appController.getAuthenticationKey(),
                appController.getLoginId(),
                getIntent().getStringExtra("FARM_ID"));
        viewModel.getFarmProductList(stateMachine, farmProductListReq,type);
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
        App.updated=true;
        onBackPressed();
    }

    @Override
    public void onFollowClickListener(String followStatus, String id) {
        if (followStatus.equals("No")) {
            viewModel.followUnFollowFarm(followUnFollowStateMachine, getIntent().getStringExtra("FARM_ID"), "1", id);
        } else {
            viewModel.followUnFollowFarm(followUnFollowStateMachine, getIntent().getStringExtra("FARM_ID"), "0", id);
        }
    }

    @Override
    public void onClickFarmDetailVegetableListener(FarmDetailsVegetableItems item, int cnt) {
        veggie=item;
        quat=cnt;
       AddtoCartItems(item,cnt);
    }
    private void AddtoCartItems(FarmDetailsVegetableItems item,int cnt){
        FarmProductListReq farmProductListReq = new FarmProductListReq(appController.getAuthenticationKey(),
                item.getFarmId(),
                appController.getLoginId(),
                item.productId,
                String.valueOf(cnt),
                item.getPrice(),
                "1",
                item.price_unit_type,
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")));
        viewModel.addToCartItems(addToCartStateMachine, farmProductListReq);
    }

    @Override
    public void onClickIncreaseCartListener(FarmDetailsVegetableItems item, int cnt) {
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(
                appController.getAuthenticationKey(),
                item.cart_id,
                "0");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }

    @Override
    public void onClickDecreaseCartListener(FarmDetailsVegetableItems item, int cnt) {
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(),
                item.cart_id, "1");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }

    @Override
    public void onDeliveryTypeCheckedChangeListener(int type) {
        getFarmProductDetail("view");

        SharedPreferenceManager.getInstance().setSharedPreference("SERVICE_TYPE", String.valueOf(type));
    }

    @Override
    public void onFarmDetailItemClicked(String id) {
        Intent intent = new Intent(this, RatingAndReviewActivity.class);
        intent.putExtra("farmId", id);
        SharedPreferenceManager.getInstance().setFarmId(id);
        startActivity(intent);
    }

    @Override
    public void onCallReviewChangeListener() {
        startActivity(new Intent(FarmDetailActivity.this, RatingAndReviewActivity.class));
    }
}