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
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.CheckOutFromCartActivity;
import com.farmers.buyers.modules.cart.myCart.adapter.MyCartAdapter;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponData;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxRequestParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.storage.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:39
 * mohammadsajjad679@gmail.com
 */

public class MyCartFragment extends BaseFragment implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners,MyCartCheckoutViewHolder.MyCoupounClickListeners {

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
    private MutableLiveData<DataFetchState<ApplyCouponResponse>> applyCouponMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<TaxResponse>> taxServiceMachine = new MutableLiveData<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_cart, container,false);
        prepareData();
        bindView(view);
        return view;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getResourceFile() {
        return 0;
    }

    public void bindView(View view) {
        recyclerView = view.findViewById(R.id._my_cart_recyclerView);
        adapter = new MyCartAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        adapter.updateData(items);
        getServicesAndTax();

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
        applyCouponMachine.observe(this, new Observer<DataFetchState<ApplyCouponResponse>>() {
            @Override
            public void onChanged(DataFetchState<ApplyCouponResponse> response) {
                switch (response.status){
                    case SUCCESS:
                        dismissLoader();
                        if (response.data!=null){
                            Intent intent = new Intent("CouponSubmit");
                            intent.putExtra("data",(ApplyCouponData)response.data.getData());
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                        }
                        break;
                    case ERROR:
                        dismissLoader();
                        break;
                    case LOADING:
                        showLoader();
                        break;

                }
            }
        });

        taxServiceMachine.observe(this, new Observer<DataFetchState<TaxResponse>>() {
            @Override
            public void onChanged(DataFetchState<TaxResponse> taxRes) {

                switch (taxRes.status){
                    case SUCCESS:
                        dismissLoader();
                        if (taxRes.data!=null){
                            Intent intent = new Intent(Constant.TAX_INTENT);
                            intent.putExtra("data",(TaxData)taxRes.data.getTaxData());
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                        }
                        break;
                    case LOADING:
                        showLoader();
                        break;
                    case ERROR:
                        dismissLoader();

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
        viewModel.validateCoupon(applyCouponMachine,applyCouponReqParams);
    }

    void getServicesAndTax(){
        TaxRequestParam requestParam=new TaxRequestParam(appController.getAuthenticationKey(),"1","","1","300");
        viewModel.applyServiceAndTax(taxServiceMachine,requestParam);
    }
}
