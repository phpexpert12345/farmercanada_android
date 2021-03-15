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
import android.widget.Button;

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
    private Button bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        setupToolbar(new ToolbarConfig("Order", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

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
        bt_cancel=findViewById(R.id.bt_cancel);

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

//if(TrackOrderTransformer.getTackOrderHeader(dataFetchState.data.getData().getAllOrderList()).order_status_msg.equalsIgnoreCase("Pending")){
//    bt_cancel.setVisibility(View.VISIBLE);
//}
//else{
//    bt_cancel.setVisibility(View.GONE);
//}
        bt_cancel.setVisibility(View.GONE);
        items.add(TrackOrderTransformer.getTackOrderHeader(dataFetchState.data.getData().getAllOrderList()));
        TrackOrderCountItem trackOrderCountItem=new TrackOrderCountItem();
        trackOrderCountItem.count=dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList().size();
        double price=0.0;
        for(int i=0;i<dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList().size();i++){
            price+=Double.parseDouble(dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList().get(i).getItem_price())*Integer.parseInt(dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList().get(i).getItem_quantity());
        }
        trackOrderCountItem.total_price= dataFetchState.data.getData().getAllOrderList().get(0).getTotal_amount();
        items.add(trackOrderCountItem);
        items.addAll(TrackOrderTransformer.getTrackOrder(
                dataFetchState.data.getData().getAllOrderList().get(0).getAllRecordList()));
        adapter.updateData(items);
    }

    private void getOrderDetails() {
        OrderTrackRequestParams orderTrackRequestParams = new OrderTrackRequestParams(
                AppController.get().getLoginId(),
                "1",
                getIntent().getStringExtra("ORDER_NUMBER"),//ORDER_STATUS_MSG
                AppController.get().getAuthenticationKey());

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