package com.farmers.buyers.modules.cart.myCart;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.Helper;
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
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    LinearLayout ll_data_not_available;
    String order_type="";
    private String subTotal = "";
    ImageView cart_back;
    TextView text_cart;
    TextView txt_farm_name,txt_farm_address,txt_farm_distance;
    String delivery_available,pickup_available,farm_latitude,farm_longitude,farm_name,farm_logo,farm_address;
    int farm_delivery_radius;
   RelativeLayout layout_farm_details;
   CircleImageView img_farm_logo;
    private GPSTracker gpsTracker = new GPSTracker(getContext());

    List<FarmProductCartList> farmProductCartList=new ArrayList<>();
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
    private MutableLiveData<DataFetchState<CartListResponse>>cartListMachine = new MutableLiveData<>();
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
        cart_back=view.findViewById(R.id.cart_back);
        cart_back.setVisibility(View.GONE);
        text_cart=view.findViewById(R.id.text_cart);
        text_cart.setText("My Cart");
        layout_farm_details=view.findViewById(R.id.layout_farm_details);
        img_farm_logo=layout_farm_details.findViewById(R.id.img_farm_logo);
        txt_farm_name=layout_farm_details.findViewById(R.id.txt_farm_name);
        txt_farm_address=layout_farm_details.findViewById(R.id.txt_farm_address);
        txt_farm_distance=layout_farm_details.findViewById(R.id.txt_farm_distance);
        ll_data_not_available=view.findViewById(R.id.ll_data_not_available);
        order_type=SharedPreferenceManager.getInstance().getSharedPreferences("order_type","").toString();
        setType();
        textViewDelivery.setOnClickListener(v->{
            if(delivery_available.equalsIgnoreCase("Yes")){
                order_type="Delivery";
                setType();
            }
            else{
                Toast.makeText(baseActivity, "Sorry we are not Providing delivery currently!!", Toast.LENGTH_SHORT).show();

            }

        });
        textViewPickUp.setOnClickListener(v->{
            if(pickup_available.equalsIgnoreCase("Yes")){
                order_type="Pickup";
                setType();
            }
            else{
                Toast.makeText(baseActivity, "Sorry we are not Providing pickup currently!!", Toast.LENGTH_SHORT).show();
            }

        });
        adapter = new MyCartAdapter(this, this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        adapter.updateData(items);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), HomeActivity.class));
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
                            taxData.setTitle("Checkout");
                        } else {
                            taxData.setCouponApplied(true);
                            taxData.setApplyCouponButton(true);
                            taxData.setDiscountTextView(false);
                            taxData.setRemoveDiscountButton(false);
                            taxData.setDiscountAmount(-1f);
                            taxData.setTitle("Checkout");
                            Toast.makeText(getContext(),response.data.getStatusMessage(),Toast.LENGTH_SHORT).show();
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
                        taxData.setTitle("Checkout");

                        // cartDataListRequest();
                    }
                    int  selected=-1;
for(int  i=0;i<items.size();i++){
    if(items.get(i)  instanceof TaxData){
        selected=i;
        break;
    }
}
if(selected>=0){
    items.remove(selected);
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
                        ll_data_not_available.setVisibility(View.GONE);
                        itemCount.setText(data.data.getData().getFarmProductCartList().size() + " Items");
                        if(data.data.getData().getFarmProductCartList().size()>0) {
                            farmProductCartList=data.data.getData().getFarmProductCartList();
                            if(farmProductCartList.size()>0){
                                delivery_available=farmProductCartList.get(0).getDelivery_available();
                                pickup_available=farmProductCartList.get(0).getPickup_available();
                                farm_delivery_radius=farmProductCartList.get(0).getFarm_delivery_radius();
                                farm_latitude=farmProductCartList.get(0).getFarm_latitude();
                                farm_longitude=farmProductCartList.get(0).getFarm_longitude();
                                farm_name=farmProductCartList.get(0).getFarmName();
                                farm_address=farmProductCartList.get(0).getFarmAddress();
                                farm_logo=farmProductCartList.get(0).getFarmLogo();

                                if(farm_name!=null){
                                    setupFarmDetails();
                                }
                            }
//                            adapter.updateData(items);
                            cartListData(data.data.getData().getFarmProductCartList());

//                            adapter.updateData(items);
                        }
//                        adapter.updateData(items);
                    } else {
                        items.clear();
                        recyclerView.setVisibility(View.GONE);
                        ll_data_not_available.setVisibility(View.VISIBLE);
                        noDataLabel.setText(data.status_message);
                        linear_order.setVisibility(View.GONE);
                        noDataLabel.setVisibility(View.VISIBLE);
                        myCartInstruction.setVisibility(View.GONE);
                        layout_farm_details.setVisibility(View.GONE);
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
                    ll_data_not_available.setVisibility(View.VISIBLE);
                    layout_farm_details.setVisibility(View.GONE);
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
    private void setupFarmDetails(){
        layout_farm_details.setVisibility(View.VISIBLE);
        txt_farm_name.setText(farm_name);
        txt_farm_address.setText(farm_address);
        Glide.with(getContext()).load(farm_logo).placeholder(R.drawable.ic_sign_up_logo).into(img_farm_logo);
        if(pickup_available.equalsIgnoreCase("yes")){
            order_type="Pickup";
            setType();
        }
        else if(delivery_available.equalsIgnoreCase("yes")){
            order_type="Delivery";
            setType();
        }
        txt_farm_distance.setText(new DecimalFormat("##.##").format(Helper.getKmFromLatLong(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Double.parseDouble(farm_latitude), Double.parseDouble(farm_longitude)))+ " km away from you");
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
        if(subTotalAmount>0.0) {
            getTax(String.valueOf(subTotalAmount), farmProductCartList);
        }
    }
    private void getTax(String subTotalAmount,List<FarmProductCartList> farmProductCartList){
        TaxRequestParam requestParam = new TaxRequestParam(appController.getAuthenticationKey(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                "",
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")),
                String.valueOf(subTotalAmount));
        cartData.addAll(MyCartTransformer.getMyCartItem(farmProductCartList));
        items.addAll(MyCartTransformer.getMyCartItem(farmProductCartList));
        getServicesAndTax(requestParam);
    }
    private void setType(){
        SharedPreferenceManager.getInstance().setSharedPreference("order_type", order_type);
        cartListData(farmProductCartList);

       switch (order_type){

           case "Delivery":
           {
               textViewDelivery.setBackgroundColor(getContext().getResources().getColor(R.color.red));
               textViewPickUp.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray));
           }


           break;
           case "Pickup":
           {
               textViewPickUp.setBackgroundColor(getContext().getResources().getColor(R.color.gradient_color_dark));
               textViewDelivery.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray));
           }
           break;

       }
    }

    @Override
    public void onCheckOutClicked() {
        Intent checkOutIntent = new Intent(getActivity(), CheckOutFromCartActivity.class);
        checkOutIntent.putExtra("farm_latitude",farm_latitude);
        checkOutIntent.putExtra("farm_longitude",farm_longitude);
        checkOutIntent.putExtra("farm_delivery_radius",farm_delivery_radius);
        checkOutIntent.putExtra("farm_name",farm_name);
        checkOutIntent.putExtra("farm_address",farm_address);
        checkOutIntent.putExtra("farm_logo",farm_logo);
        if(!order_type.equalsIgnoreCase("")){
            checkOutIntent.putExtra(Constant.DATA_INTENT, taxData);
            checkOutIntent.putExtra("order_type",order_type);
            startActivity(checkOutIntent);
        }
        else{
            Toast.makeText(baseActivity, "Please select Order type", Toast.LENGTH_SHORT).show();
        }

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