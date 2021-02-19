package com.farmers.buyers.modules.cart.myCart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.SwipeControllerActions;
import com.farmers.buyers.common.utils.SwipeHelper;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.CheckOutFromCartActivity;
import com.farmers.buyers.modules.cart.myCart.adapter.MyCartAdapter;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponData;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:39
 * mohammadsajjad679@gmail.com
 */

public class MyCartFragment extends Fragment implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners,MyCartCheckoutViewHolder.MyCoupounClickListeners {

    private RecyclerView recyclerView;
    private MyCartAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();



    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MyCartViewModel.class)) {
                return (T) new MyCartViewModel();
            }
            return null;
        }
    };

    private MyCartViewModel viewModel = factory.create(MyCartViewModel.class);
    private AppController appController = AppController.get();
    private MutableLiveData<DataFetchState<ApplyCouponResponse>> stateMachine = new MutableLiveData<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_cart, container,false);
        prepareData();
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id._my_cart_recyclerView);
        adapter = new MyCartAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        adapter.updateData(items);

        SwipeHelper swipeHelper = new SwipeHelper(getContext(), recyclerView, 250) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List buffer) {

                buffer.add(new MyButton(getContext(), R.drawable.ic_delete_round, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);
                            }
                        }
                ));

            }
        };
        stateMachine.observe(this, new Observer<DataFetchState<ApplyCouponResponse>>() {
            @Override
            public void onChanged(DataFetchState<ApplyCouponResponse> response) {
                switch (response.status){
                    case SUCCESS:
                        if (response.data!=null){
                            Intent intent = new Intent("CouponSubmit");
                            intent.putExtra("data",(ApplyCouponData)response.data.getData());
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                        }
                        break;
                    case ERROR:

                        break;
                    case LOADING:
                        break;

                }
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(swipeHelper);
        helper.attachToRecyclerView(recyclerView);
    }




    private void prepareData() {
        items.addAll(MyCartTransformer.getMyCartItem());
        items.add(new MyCartCheckOutItem());
    }


    @Override
    public void onCheckOutClicked() {
        startActivity(new Intent(getContext(), CheckOutFromCartActivity.class));
    }

    @Override
    public void onCouponClicked(String couponCode) {

        ApplyCouponReqParams applyCouponReqParams=new ApplyCouponReqParams(appController.getAuthenticationKey(), couponCode,"1","300");
        viewModel.validateCoupon(stateMachine,applyCouponReqParams);


    }
}
