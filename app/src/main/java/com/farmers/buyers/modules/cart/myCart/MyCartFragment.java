package com.farmers.buyers.modules.cart.myCart;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponReqParams;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxRequestParam;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxResponse;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.myCart.view.MyCartItemViewHolder;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:39
 * mohammadsajjad679@gmail.com
 */

public class MyCartFragment extends BaseFragment implements
        MyCartCheckoutViewHolder.MyCartCheckOutClickListeners,
        MyCartCheckoutViewHolder.MyCoupounClickListeners, MyCartItemViewHolder.DecreaseCallback, MyCartItemViewHolder.IncreaseCallback {

    TaxData taxData = null;
    private RecyclerView recyclerView;
    private TextView noDataLabel, myCartInstruction, itemCount, addItem;
    private MyCartAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private List<MyCartItem> cartData = new ArrayList<>();
    LinearLayout linear_order;
    CardView cardViewDelivery, cardViewPickUp;
    TextView  textViewDelivery, textViewPickUp;
    String order_type="";
    private String subTotal = "";
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
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> increaseDecreaseMachine = new MutableLiveData<>();

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getResourceFile() {
        return R.layout.activity_my_cart;
    }

    public void bindView(View view) {
        recyclerView = view.findViewById(R.id._my_cart_recyclerView);
        noDataLabel = view.findViewById(R.id.nodata_label);
        myCartInstruction = view.findViewById(R.id.my_cart_instruction_tv);
        itemCount = view.findViewById(R.id.itemCount);
        addItem = view.findViewById(R.id.add_item);
        linear_order=view.findViewById(R.id.linear_order);
        textViewDelivery=view.findViewById(R.id.textViewDelivery);
        textViewPickUp=view.findViewById(R.id.textViewPickUp);
        textViewDelivery.setOnClickListener(v->{
            order_type="Delivery";
            textViewDelivery.setBackgroundColor(getContext().getColor(R.color.red));
            textViewPickUp.setBackgroundColor(getContext().getColor(R.color.light_gray));
        });
        textViewPickUp.setOnClickListener(v->{
            order_type="Pickup";
            textViewPickUp.setBackgroundColor(getContext().getColor(R.color.red));
            textViewDelivery.setBackgroundColor(getContext().getColor(R.color.light_gray));
        });
        adapter = new MyCartAdapter(this, this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        adapter.updateData(items);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getActivity(),FarmList));
            }
        });
        // getServicesAndTax();
        SwipeHelper swipeHelper = new SwipeHelper(getContext(), recyclerView, 250) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List buffer) {
                buffer.add(new MyButton(getContext(), R.drawable.ic_delete_round, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);
                                showDialog(cartData.get(position).getCartId());
                            }
                        }
                ));

            }
        };
        applyCouponMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    dismissLoader();
                    if (response.data != null) {
                        if (response.data.getStatus()) {
                            taxData.setCouponApplied(true);
                            taxData.setApplyCouponButton(false);
                            taxData.setDiscountTextView(true);
                            taxData.setRemoveDiscountButton(true);
                            taxData.setDiscountAmount(Float.parseFloat(response.data.getData().getCoupon_Discount_Price()));
                        } else {
                            taxData.setCouponApplied(true);
                            taxData.setApplyCouponButton(true);
                            taxData.setDiscountTextView(false);
                            taxData.setRemoveDiscountButton(false);
                            taxData.setDiscountAmount(-1f);
                        }
                        // prepareData(taxData);
                        adapter.updateData(items);
                    }
                    break;
                case ERROR:
                    taxData.setDiscountAmount(-1f);
                    dismissLoader();
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        taxServiceMachine.observe(this, taxRes -> {
            switch (taxRes.status) {
                case SUCCESS:
                    dismissLoader();
                    if (taxRes.data != null) {
                        taxData = taxRes.data.getTaxData();
                        taxData.setRemoveDiscountButton(false);
                        taxData.setDiscountTextView(false);
                        taxData.setApplyCouponButton(true);
                        taxData.setCouponApplied(false);
                        taxData.setDiscountAmount(0f);
                        taxData.setSubTotal(subTotal);

                        // cartDataListRequest();
                    }
                    items.add(MyCartTransformer.getTaxDataItem(taxRes.data.getTaxData()));

                    adapter.updateData(items);
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        cartListMachine.observe(this, data -> {
            switch (data.status) {
                case SUCCESS:
                    dismissLoader();
                    if (data.data.getStatus()) {
                        recyclerView.setVisibility(View.VISIBLE);
                        myCartInstruction.setVisibility(View.VISIBLE);
                        noDataLabel.setVisibility(View.GONE);
                        linear_order.setVisibility(View.VISIBLE);
                        itemCount.setText(data.data.getData().getFarmProductCartList().size() + " Items");
                        cartListData(data.data.getData().getFarmProductCartList());
                        adapter.updateData(items);
                    } else {
                        items.clear();
                        recyclerView.setVisibility(View.GONE);
                        noDataLabel.setText(data.status_message);
                        linear_order.setVisibility(View.GONE);
                        noDataLabel.setVisibility(View.VISIBLE);
                        myCartInstruction.setVisibility(View.GONE);
                        itemCount.setText("0 Items");
                    }
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    itemCount.setText("No Items");
                    items.clear();
                    myCartInstruction.setVisibility(View.GONE);
                    itemCount.setVisibility(View.GONE);
                    linear_order.setVisibility(View.GONE);
                    noDataLabel.setVisibility(View.VISIBLE);
                    noDataLabel.setText(data.status_message);
                    adapter.updateData(items);
                    dismissLoader();
                    break;
            }
        });

        increaseDecreaseMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    cartDataListRequest();
                    dismissLoader();
                case LOADING:
                    showLoader();
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(swipeHelper);
        helper.attachToRecyclerView(recyclerView);

        cartDataListRequest();
    }

    private void cartListData(List<FarmProductCartList> farmProductCartList) {
        items.clear();
        cartData.clear();
        Double subTotalAmount = 0.0;
        for (int i = 0; MyCartTransformer.getMyCartItem(farmProductCartList).size() > i; i++) {
            subTotalAmount = subTotalAmount + Double.parseDouble(MyCartTransformer.getMyCartItem(
                    farmProductCartList).get(i).getItemSubPrice())*MyCartTransformer.getMyCartItem(farmProductCartList).get(i).getCartItemQuantity();
        }

        subTotal = String.valueOf(subTotalAmount);

        TaxRequestParam requestParam = new TaxRequestParam(appController.getAuthenticationKey(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                "",
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")),
                String.valueOf(subTotalAmount));
        cartData.addAll(MyCartTransformer.getMyCartItem(farmProductCartList));
        items.addAll(MyCartTransformer.getMyCartItem(farmProductCartList));
        getServicesAndTax(requestParam);
    }

    @Override
    public void onCheckOutClicked() {
        Intent checkOutIntent = new Intent(getActivity(), CheckOutFromCartActivity.class);
        checkOutIntent.putExtra(Constant.DATA_INTENT, taxData);
        checkOutIntent.putExtra("order_type",order_type);
        startActivity(checkOutIntent);
    }

    @Override
    public void onCouponClicked(String couponCode) {
        ApplyCouponReqParams applyCouponReqParams = new ApplyCouponReqParams(
                appController.getAuthenticationKey(),
                couponCode,
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                subTotal);
        viewModel.validateCoupon(applyCouponMachine, applyCouponReqParams);
    }

    void getServicesAndTax(TaxRequestParam requestParam) {
        viewModel.applyServiceAndTax(taxServiceMachine, requestParam);
    }

    void cartDataListRequest() {
        CartReqParam cartReqParam = new CartReqParam(
                appController.getAuthenticationKey(),
                appController.getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")));
        viewModel.getCartListItems(cartListMachine, cartReqParam);
    }

    void showDialog(String cartId) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Remove Cart Item")
                .setMessage("Are you sure! You want to remove ?")
                .setCancelable(false)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(),
                            cartId, "3");
                    viewModel.increaseDecrease(increaseDecreaseMachine, params);
                    dialog.dismiss();
                })
                .setIcon(getResources().getDrawable(R.drawable.logo))
                .show();
    }

    @Override
    public void increaseCallback(String cartId) {
        //"option_type_developer_help": "0=increase,1=descrease,3=delete
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(), cartId, "0");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }

    @Override
    public void decreseCallback(String cartId) {
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(), cartId, "1");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }
}