package com.farmers.buyers.modules.farmDetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDeliveryStatus;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;
import com.farmers.buyers.storage.SharedPreferenceManager;

public  class FarmDetailActivity extends BaseActivity implements HomeHeaderViewHolder.HeaderItemClickListener,
        FarmDetailHeaderViewHolder.FarmHeaderClickListener, FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener {
    private RecyclerView recyclerView;
    RelativeLayout  constraintLayoutCart;
    TextView txt_count,txt_price;
    private FarmDetailsAdapter adapter;
    public String farm_id;
    int farm_delivery_radius;
    double farm_lat,farm_long;
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
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> increaseDecreaseMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> clearAllCartItemsMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);
        cartDataListRequest();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.farmers_detail_recyclerView);
        constraintLayoutCart=findViewById(R.id.constraintLayoutCart);
        constraintLayoutCart.setOnClickListener(v->{
            Intent intent=new Intent();
            intent.putExtra("open","cart");
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
        txt_count=findViewById(R.id.txt_count);
        txt_price=findViewById(R.id.txt_price);
        adapter = new FarmDetailsAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartListMachine.observe(this, data -> {
            switch (data.status) {
                case SUCCESS:

                    if (data.data.getStatus()) {
                        if(data.data.getData().getFarmProductCartList().size()>0){
                            constraintLayoutCart.setVisibility(View.VISIBLE);
                            txt_count.setText(data.data.getData().getFarmProductCartList().size() + " Items");
                            Double subTotalAmount = 0.0;
                            for(FarmProductCartList farmProductCartList:data.data.getData().getFarmProductCartList()){
                                subTotalAmount = subTotalAmount + Double.parseDouble(farmProductCartList.getItemPrice())*Integer.parseInt(farmProductCartList.getItemQuantity());
                            }
                            txt_price.setText("$"+String.format("%.2f",subTotalAmount));
                        }


                    } else {
                       constraintLayoutCart.setVisibility(View.GONE);
                    }
                    break;
                case LOADING:

                    break;
                case ERROR:
                    constraintLayoutCart.setVisibility(View.GONE);

                    break;
            }
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
                    dismissLoader();
                    getFarmProductDetail();
                    cartDataListRequest();
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
                    getFarmProductDetail();
                    cartDataListRequest();
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    dismissLoader();
                case LOADING:
                    showLoader();
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        clearAllCartItemsMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    constraintLayoutCart.setVisibility(View.GONE);
                    Toast.makeText(FarmDetailActivity.this, response.status_message, Toast.LENGTH_SHORT).show();
                    dismissLoader();
                    getFarmProductDetail();
                case LOADING:
                    showLoader();
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        getFarmProductDetail();
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
                    DroidPrefs.getDefaultInstance(getApplicationContext()).clearkey("delivery_radius");
                })
                .setIcon(getResources().getDrawable(R.drawable.logo))
                .show();
    }

    private void getFarmProductDetail() {
        farm_delivery_radius=getIntent().getIntExtra("farm_delivery_radius",0);
        farm_lat=getIntent().getDoubleExtra("farm_lat",0.0);
        farm_long=getIntent().getDoubleExtra("farm_long",0.0);
        FarmProductListReq farmProductListReq = new FarmProductListReq(FarmDetailActivity.this,
                appController.getAuthenticationKey(),
                appController.getLoginId(),
                getIntent().getStringExtra("FARM_ID"));
        viewModel.getFarmProductList(stateMachine, farmProductListReq);
    }
    void cartDataListRequest() {
        CartReqParam cartReqParam = new CartReqParam(
                appController.getAuthenticationKey(),
                appController.getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")));
        viewModel.getCartListItems(cartListMachine, cartReqParam);
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
    public void onFollowClickListener(String followStatus, String id) {
        Toast.makeText(FarmDetailActivity.this, "Clicked " + followStatus + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFarmDetailVegetableListener(FarmDetailsVegetableItems item, int cnt) {
        FarmProductListReq farmProductListReq = new FarmProductListReq(appController.getAuthenticationKey(),
                item.getFarmId(),
                appController.getLoginId(),
                item.productId,
                String.valueOf(cnt),
                item.getPrice(),
                "1",
                item.price_unit_type,
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")));
        FarmDeliveryStatus farmDeliveryStatus=new FarmDeliveryStatus();
        farmDeliveryStatus.farm_delivery_status=farm_delivery_radius;
        farmDeliveryStatus.farm_lat=farm_lat;
        farmDeliveryStatus.farm_long=farm_long;
        DroidPrefs.apply(getApplicationContext(),"delivery_radius",farmDeliveryStatus);
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
}