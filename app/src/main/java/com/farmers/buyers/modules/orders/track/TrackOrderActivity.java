package com.farmers.buyers.modules.orders.track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.orders.OrdersTransformer;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderRequestParams;
import com.farmers.buyers.modules.orders.subOrderList.SubOredrViewModel;
import com.farmers.buyers.modules.orders.track.adapter.TrackOrderAdapter;
import com.farmers.buyers.modules.orders.track.model.OrderTrackRequestParams;
import com.farmers.buyers.modules.orders.track.model.TrackOrderCountItem;
import com.farmers.buyers.modules.orders.track.model.TrackOrderHeaderItems;
import com.farmers.buyers.modules.orders.track.view.OrderTrackViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrackOrderActivity extends BaseActivity {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(OrderTrackViewModel.class)) {
                return (T) new OrderTrackViewModel();
            }
            return null;
        }
    };
    public OrderTrackViewModel viewModel = factory.create(OrderTrackViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();

    private RecyclerView recyclerView;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private TrackOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

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

        //   prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.track_order_recyclerView);
        adapter = new TrackOrderAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(divider);
        //  adapter.updateData(items);

        getOrderDetails();

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    // dismissLoader();
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

        items.add(TrackOrderTransformer.getTackOrderHeader(dataFetchState.data.getData().getAllOrderList()));
        items.add(new TrackOrderCountItem());
        items.addAll(TrackOrderTransformer.getTrackOrder(dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList()));
        adapter.updateData(items);
    }

    private void getOrderDetails() {
        OrderTrackRequestParams orderTrackRequestParams = new OrderTrackRequestParams(AppController.get().getLoginId(),
                "1", getIntent().getStringExtra("ORDER_NUMBER"), AppController.get().getAuthenticationKey());

        viewModel.getOrderDetails(stateMachine, orderTrackRequestParams);
    }

    private void prepareItems() {
        items.add(TrackOrderTransformer.getTackOrderHeader());
        items.add(new TrackOrderCountItem());
        items.addAll(TrackOrderTransformer.getTrackOrder());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}