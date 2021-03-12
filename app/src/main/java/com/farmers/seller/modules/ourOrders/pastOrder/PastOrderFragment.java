package com.farmers.seller.modules.ourOrders.pastOrder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.review.ReviewFragment;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.ourOrders.adapter.PastOrderListAdapter;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.ourOrders.model.OngoingOrderListItem;
import com.farmers.seller.modules.ourOrders.model.PastOrderListItem;
import com.farmers.seller.modules.ourOrders.view.PastOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class PastOrderFragment extends BaseFragment implements PastOrderListViewHolder.PastOrderItemClickListener, TextWatcher {
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
    private MutableLiveData<DataFetchState<AllOrderResponse>> pastOrderStateMachine = new MutableLiveData<>();
    public EditText home_search_bottom_sheet_search_et;
    private RecyclerView rv_review_list;
    private PastOrderListAdapter adapter;

    public PastOrderFragment get() {
        return new PastOrderFragment();
    }

    public PastOrderFragment() {
    }

    @Override
    public String getTitle() {
        return "Past";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_past_order;
    }

    @Override
    public void bindView(View view) {

        home_search_bottom_sheet_search_et = view.findViewById(R.id.home_search_bottom_sheet_search_et);
        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new PastOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        pastOrderStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    //  dismissLoader();
                    Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    // dismissLoader();
                    adapter.updateData(viewModel.pastItems);
                    break;
                case LOADING:
                    // showLoader();
                    break;
            }
        });
        home_search_bottom_sheet_search_et.addTextChangedListener(this);
    }

    private void prepareItems() {
        viewModel.getPastOrders(pastOrderStateMachine);
    }

    public void getPastOrder() {
        prepareItems();
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        // Toast.makeText(ProductListActivity.this, s.toString().trim(), Toast.LENGTH_SHORT).show();
        filter(s.toString());
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<RecyclerViewListItem> filterdNames = new ArrayList<>();
        for (int i = 0; i < viewModel.pastItems.size(); i++) {
            PastOrderListItem item = (PastOrderListItem) viewModel.pastItems.get(i);
            if (item.order_number.contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(item);
            }
        }
        //calling a method of the adapter class and passing the filtered list
        try {
            adapter.updateData(filterdNames);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void onPastOrderItemClicked(PastOrderListItem item) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("ORDER_NUMBER", item.order_number).
                putExtra("KEY", "order_delivered"));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


    @Override
    public void afterTextChanged(Editable editable) {

    }
}