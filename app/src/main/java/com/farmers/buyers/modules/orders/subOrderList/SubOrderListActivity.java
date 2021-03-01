package com.farmers.buyers.modules.orders.subOrderList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.orders.OrdersTransformer;
import com.farmers.buyers.modules.orders.SubOrderExtra;
import com.farmers.buyers.modules.orders.adapter.SubOrderItemAdapter;
import com.farmers.buyers.modules.orders.model.SubOrdersListItem;
import com.farmers.buyers.modules.orders.track.TrackOrderActivity;
import com.farmers.buyers.modules.orders.view.SubOrderItemViewHolder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SubOrderListActivity extends BaseActivity implements SubOrderItemViewHolder.SubOrderItemClickListener {
   /* TabLayout tabLayout;
    ViewPager viewPager;

    SubOrderListFragment tab1;
    SubOrderListFragment tab2;
    SubOrderListFragment tab3;*/

    private List<RecyclerViewListItem> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private SubOrderItemAdapter adapter;
    private LinearLayout ll_data_not_available;
    private TextView tv_error_msg;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SubOredrViewModel.class)) {
                return (T) new SubOredrViewModel();
            }
            return null;
        }
    };
    public SubOredrViewModel viewModel = factory.create(SubOredrViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_order_list);
        setupToolbar(new ToolbarConfig("Order", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
      /*  tabLayout = findViewById(R.id.sub_order_tab_layout);
        viewPager = findViewById(R.id.sub_order_viewPager);

        setUpTabLayout();*/

        ll_data_not_available = findViewById(R.id.ll_data_not_available);
        tv_error_msg = findViewById(R.id.tv_error_msg);
        recyclerView = findViewById(R.id.sub_order_recyclerView);
        adapter = new SubOrderItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSubOrderApi();

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    recyclerView.setVisibility(View.GONE);
                    tv_error_msg.setText(dataFetchState.status_message);
                    ll_data_not_available.setVisibility(View.VISIBLE);
                    break;
                }
                case LOADING: {
                    showLoader();

                    break;
                }
                case SUCCESS: {
                    callSetData(dataFetchState);
                    break;
                }
            }
        });
    }

    private void callSetData(DataFetchState<AddressApiModel> dataFetchState) {
        dismissLoader();
        items.addAll(OrdersTransformer.getPendingItems(dataFetchState.data.getData().getAllOrderList()));
        adapter.updateData(items);
    }

    private void getSubOrderApi() {
        SubOrderRequestParams subOrderRequestParams = new SubOrderRequestParams(AppController.get().getLoginId(),
                "1", AppController.get().getAuthenticationKey());

        viewModel.getSubOrder(stateMachine, subOrderRequestParams);
    }

    @Override
    public void onSubOrderItemClicked(SubOrdersListItem item) {
        startActivity(new Intent(SubOrderListActivity.this, TrackOrderActivity.class).
                putExtra("ORDER_STATUS_MSG", item.getStatus()).
                putExtra("ORDER_TYPE", item.getOrderId()).
                putExtra("ORDER_NUMBER", item.getOrderId()));
    }

/*
    private void setUpTabLayout() {

        setUpViewPager();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                int count = fm.getBackStackEntryCount();
                if (count > 1) fm.popBackStack();
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.getTabAt(0).select();
            }
        });

    }

    private void setUpViewPager() {
        tab1 = new SubOrderListFragment("Pending", SubOrderExtra.PENDING).get();
        tab2 = new SubOrderListFragment("Accepted", SubOrderExtra.ACCEPTED).get();
        tab3 = new SubOrderListFragment("Rejected", SubOrderExtra.REJECTED).get();

        AppPagerAdapter appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
        appPagerAdapter.addFragment(tab1, tab1.title);
        appPagerAdapter.addFragment(tab2, tab2.title);
        appPagerAdapter.addFragment(tab3, tab3.title);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(appPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);

    }*/
}