package com.farmers.seller.modules.ourOrders.ongoingOrder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.ourOrders.adapter.OngoingOrderListAdapter;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.ourOrders.model.OngoingOrderListItem;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;
import com.farmers.seller.modules.ourOrders.pastOrder.PastOrderFragment;
import com.farmers.seller.modules.ourOrders.view.OngoingOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class OngoingOrderFragment extends BaseFragment implements OngoingOrderListViewHolder.OngoingOrderItemClickListener {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(OurOrdersViewModel.class)) {
                return (T) new OurOrdersViewModel();
            }
            return null;
        }
    };
    public OurOrdersViewModel viewModel = factory.create(OurOrdersViewModel.class);
    private MutableLiveData<DataFetchState<AllOrderResponse>> runningOrderStateMachine = new MutableLiveData<>();

    private RecyclerView rv_review_list;
    private OngoingOrderListAdapter adapter;

    public OngoingOrderFragment get() {
        return new OngoingOrderFragment();
    }

    public OngoingOrderFragment() {
    }

    @Override
    public String getTitle() {
        return "Running Order's";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_ongoing_order;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new OngoingOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        runningOrderStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    //  dismissLoader();
                    Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    // dismissLoader();
                    adapter.updateData(viewModel.runningItems);
                    break;
                case LOADING:
                    // showLoader();
                    break;
            }
        });
    }

    private void prepareItems() {
        viewModel.getRunningOrders(runningOrderStateMachine);
    }

    public void getOngoingOrder() {
        //   items.addAll(OurOrdersTransformer.getOngoingOrderList());
    }

    @Override
    public void onOngoingOrderItemClicked(OngoingOrderListItem item) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("ORDER_NUMBER", item.order_number).
                putExtra("KEY", "order_preparing"));
    }
}