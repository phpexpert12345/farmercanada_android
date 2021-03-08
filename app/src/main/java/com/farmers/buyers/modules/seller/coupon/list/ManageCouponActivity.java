package com.farmers.buyers.modules.seller.coupon.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.ManageCouponViewModel;
import com.farmers.buyers.modules.seller.coupon.addCoupon.AddNewCouponActivity;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponTransformer;
import com.farmers.buyers.modules.seller.coupon.list.adapter.ManageCouponAdapter;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;

import java.util.ArrayList;
import java.util.List;

public class ManageCouponActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ManageCouponAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private LinearLayout ll_add_coupon;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ManageCouponViewModel.class)){
                return (T) new ManageCouponViewModel();
            }
            return null;
        }
    };

    private ManageCouponViewModel viewModel = factory.create(ManageCouponViewModel.class);
    private MutableLiveData<DataFetchState<CouponListApiModel>> stateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_coupon);
        setupToolbar(new ToolbarConfig("Manage Coupon", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.manage_coupon_recyclerView);
        adapter = new ManageCouponAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ll_add_coupon = findViewById(R.id.ll_add_coupon);

        ll_add_coupon.setOnClickListener(view -> startActivity(new Intent(ManageCouponActivity.this, AddNewCouponActivity.class)));

        stateMachine.observe(this, new Observer<DataFetchState<CouponListApiModel>>() {
            @Override
            public void onChanged(DataFetchState<CouponListApiModel> couponListApiModelDataFetchState) {
                switch (couponListApiModelDataFetchState.status) {
                    case LOADING: loading(); break;
                    case SUCCESS: success(); break;
                    case ERROR:   error(couponListApiModelDataFetchState.status_message); break;

                }
            }
        });


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
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }


    @Override
    public Boolean showToolbar() {
        return true;
    }
}