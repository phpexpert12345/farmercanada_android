package com.farmers.seller.modules.ourOrders.ourOrder;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentViewModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.seller.modules.ourOrders.OurOrdersTransformer;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.ourOrders.adapter.OurOrderListAdapter;
import com.farmers.seller.modules.ourOrders.model.AllOrderResponse;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;
import com.farmers.seller.modules.ourOrders.view.OurOrderListViewHolder;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class OurOrdersFragment extends BaseFragment implements OurOrderListViewHolder.OurOrderItemClickListener {
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
    private MutableLiveData<DataFetchState<AllOrderResponse>> newOrderStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllOrderResponse>> orderAcceptStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AllOrderResponse>> orderDeclineStateMachine = new MutableLiveData<>();

    private RecyclerView rv_review_list;
    private OurOrderListAdapter adapter;

    public OurOrdersFragment get() {
        return new OurOrdersFragment();
    }

    public OurOrdersFragment() {
    }

    @Override
    public String getTitle() {
        return "My Order's";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_our_orders;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new OurOrderListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();

        newOrderStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    dismissLoader();
                    adapter.updateData(viewModel.items);
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        orderAcceptStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(getContext(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    prepareItems();
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        orderDeclineStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(getContext(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    prepareItems();
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });
    }

    private void prepareItems() {
        viewModel.getNewOrders(newOrderStateMachine);
    }

    public void getOurOrder() {
        // items.addAll(OurOrdersTransformer.getOurOrderList());
    }

    @Override
    public void onOurOrderItemClicked(OurOrderListItem item) {
        startActivity(new Intent(getContext(), ViewOrderDetailsActivity.class).
                putExtra("ORDER_NUMBER", item.order_number).
                putExtra("KEY", "reject_accept"));
    }

    @Override
    public void onOurOrderItemAcceptClicked(OurOrderListItem item) {
        AlertHelper.showAlert(getContext(), "Order Details",
                "Are you sure you want to accept this order", true, "Ok",
                true, "Cancel", true, new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        viewModel.orderAccept(orderAcceptStateMachine, item.order_number);
                    }
                });
    }

    @Override
    public void onOurOrderItemDeclineClicked(OurOrderListItem item) {
        order_decline_dialog(getActivity(), item.order_number);
    }

    public void order_decline_dialog(Activity activity, String orderNumber) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_decline_dialog);

        EditText ed_message = dialog.findViewById(R.id.ed_message);
        Button bt_order_track = dialog.findViewById(R.id.bt_order_track);
        Button bt_continue = dialog.findViewById(R.id.bt_continue);

        bt_continue.setOnClickListener(view -> {
            if (TextUtils.isEmpty(ed_message.getText().toString().trim())) {
                Toast.makeText(getContext(), "Please enter message", Toast.LENGTH_SHORT).show();
            } else {
                dialog.dismiss();
                viewModel.orderDecline(orderAcceptStateMachine, orderNumber,
                        ed_message.getText().toString().trim());
            }
        });

        bt_order_track.setOnClickListener(view -> dialog.dismiss());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}