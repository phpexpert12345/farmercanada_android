package com.farmers.buyers.modules.cart.checkout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.OrderData;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.model.StripePay;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.adapter.CheckOutCartItemAdapter;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.checkout.model.PaymentMethodsItems;
import com.farmers.buyers.modules.cart.checkout.view.CheckOutFromCartAddressViewHolder;
import com.farmers.buyers.modules.cart.checkout.view.PaymentMethodsViewHolder;
import com.farmers.buyers.modules.cart.myCart.MyCartViewModel;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.order.OrderSuccessDetailActivity;
import com.farmers.buyers.modules.cart.order.PlaceOrderActivity;
import com.farmers.buyers.modules.cart.order.SubmitOrderViewModel;
import com.farmers.buyers.modules.cart.order.adapter.TimeListAdapter;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.modules.farmDetail.model.FarmDeliveryStatus;
import com.farmers.buyers.modules.orders.OrderSingleton;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckOutFromCartActivity extends BaseActivity implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners,
        MyCartCheckoutViewHolder.MyCoupounClickListeners, CheckOutFromCartAddressViewHolder.ChangeAddressCallback,
        PaymentMethodsViewHolder.PaymentMethodListener {

    private RecyclerView recyclerView;
    TaxData taxData;
    String order_type;
    private CheckOutCartItemAdapter adapter;
    CheckOutCartAddressItems address;
    private FarmDeliveryStatus farmDeliveryStatus;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    List<AddressApiModel.AddressListData>date_list=new ArrayList<>();
    List<AddressApiModel.AddressListData>time_list=new ArrayList<>();
    String price,quantity,itemid,item_unit_type,str_sizeid,extraitemid;
    Double subTotalAmount = 0.0;
    String pay_type;
    String date;
    String time;
    double dis=0.0;
    StripePay stripePay;
    Dialog date_dialog;
    private MutableLiveData<DataFetchState<AddressApiModel>> dateStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> timeStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    private MutableLiveData<DataFetchState<SubmitResponse>> submitMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();
    private void getPaymentkey(){
        String url= ApiConstants.BASE_URL+ApiConstants.GET_PAYMENT_KEY+"?farm_id="+ SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString()+"&auth_key="+appController.getAuthenticationKey();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.has("status")){
                        boolean status=jsonObject.getBoolean("status");
                        if(status){
                            Gson gson=new Gson();
                            Type type= new TypeToken<StripePay>(){}.getType();
                            stripePay=gson.fromJson(jsonObject.getJSONObject("data").toString(),type);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

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
    private GPSTracker gpsTracker;
    private int paymentType = -1;
    private Dialog pay_dialog;
    private void callTimeSlot(String date) {

        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                date);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }
    private void dialogTimeSelection(List<AddressApiModel.AddressListData> sessionTypeMainData,int type) {

        date_dialog = new Dialog(CheckOutFromCartActivity.this);
        date_dialog.setContentView(R.layout.dialog_session_type);
        Window window = date_dialog.getWindow();
        date_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextView tvCancel = date_dialog.findViewById(R.id.tvCancel);
        TextView tvDone = date_dialog.findViewById(R.id.tvDone);
        TextView txt_title = date_dialog.findViewById(R.id.txt_title);
        if(type==0){
           txt_title.setText("Select Date");
        }
        else{
            txt_title.setText("Select TimeSlot");
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_dialog.cancel();

            }
        });
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (sessionType.equalsIgnoreCase("")){
                    sessionType= sessionTypeMainData.get(0).getId();
                    tvSessionType.setText(sessionTypeMainData.get(0).getSessionType());
                }*/
                // txt_selected_time.setText();
                date_dialog.cancel();
            }
        });

        WheelView wvSessionType = date_dialog.findViewById(R.id.wvSessionType);
        wvSessionType.setCyclic(false);

        /*final List<String> mOptionsItems = new ArrayList<>();
        for (int i=0;i<sessionTypeMainData.size();i++){
            mOptionsItems.add("item0");
        }*/

        wvSessionType.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return sessionTypeMainData.size();
            }

            @Override
            public Object getItem(int index) {
                if(type==0){
                    return sessionTypeMainData.get(index).current_date;
                }
                else{
                    return sessionTypeMainData.get(index).current_time;
                }

            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }
        });
        wvSessionType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                switch (type){
                    case 0:
                        date=sessionTypeMainData.get(index).current_date;
                        callTimeSlot(date);
                        date_dialog.dismiss();
                        break;
                    case 1:
                        time=sessionTypeMainData.get(index).current_time;
                        date_dialog.dismiss();
                        break;
                }

                //tvBookingTime.setText(sessionTypeMainData.get(index).getGetTime());
                //txt_selected_time.setText(sessionTypeMainData.get(index).getGetTime());
                //Toast.makeText(getApplicationContext(), "" + sessionTypeMainData.get(index).getSessionType(), Toast.LENGTH_SHORT).show();
              /*  sessionType = sessionTypeMainData.get(index).getId();
                tvSessionType.setText(sessionTypeMainData.get(index).getSessionType());*/
            }
        });
        date_dialog.show();
    }
    private void dialogOpen(int type) {
        pay_dialog = new Dialog(CheckOutFromCartActivity.this);
        pay_dialog.setContentView(R.layout.dialog_stripe);
        Window window = pay_dialog.getWindow();
        pay_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        CardMultilineWidget card_details = pay_dialog.findViewById(R.id.card_details);
        Button btnPay = pay_dialog.findViewById(R.id.btnPay);
        ImageView wallet_back_image=pay_dialog.findViewById(R.id.wallet_back_image);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPaymentcall(card_details,type);
            }
        });
        wallet_back_image.setOnClickListener(v->{
            pay_dialog.dismiss();
        });


        pay_dialog.show();
    }
    private void createPaymentcall(CardMultilineWidget card_details,int type) {

        //pk_test_51I04dtKD2jSEa72lEFrtZpRLIdB41dgp5cv421yfPcQ2bPjW6dXswhLYlZoSUTuhbGyMtHjI7n4dMHCcp4N8gqKf00kKQTk8UX
        // live==  pk_live_51H335kI4oh76Z6dpfFRYuAHNcBAQtEZGtf6D7Hs2IG92vaM0x9Do2YFgNBmFNUx5d7fdAv9zsHyUxPjkydKfUCEX00j0eCL1ae
        Stripe stripe = new Stripe(CheckOutFromCartActivity.this, stripePay.stripe_publishKey);
//        Stripe stripe = new Stripe(PlaceOrderActivity.this, "pk_test_51H335kI4oh76Z6dpZGTM13kKY5tMuzpQpGAzDOxhjLIHvzgD3IUWsznINS83NYvmTtXWOugAVvlnMfIDC5c8X2cm00V8TXD3tL");
        final Card cardToSave = card_details.getCard();

        if (cardToSave != null) {
            showLoader();
            //progressBar.setVisibility(View.VISIBLE);
           /* stripe.createAccountToken(, "", "", new ApiResultCallback<Token>() {
                @Override
                public void onSuccess(@NotNull Token token) {

                }

                @Override
                public void onError(@NotNull Exception e) {

                }
            });*/
            stripe.createToken(cardToSave, new ApiResultCallback<Token>() {
                @Override
                public void onError(Exception error) {
                    dismissLoader();
                    Toast.makeText(CheckOutFromCartActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onSuccess(Token token) {
                    dismissLoader();
                    String description = "Payment success";
                    Log.e("getTokenId=", token.getId());
                    //Toast.makeText(CartCheckout.this, token.getId(), Toast.LENGTH_SHORT).show();
                    // progressBar.setVisibility(View.VISIBLE);
                    //stripePayment(token.getId());

                    //hideProgress();
                    //dialog.cancel();

                    stripePayment(token.getId(),type);
                    //here we send the token generated by strip to server for payment
                    //
                    //
                    //
                    //RetrofitHelper.getInstance().doStripePayment(stripePaymentCallback, user_id, token.getId(), amount, "1",package_id,"THis is for test");
                }



            });
        }
        else{
            Toast.makeText(this, "Please enter card details", Toast.LENGTH_SHORT).show();
        }
    }
    private void stripePayment(String token,int type){
        showLoader();
        String url= ApiConstants.BASE_URL+ApiConstants.STRIPE_PAY+"?farm_id="+ SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString()+"&auth_key="+appController.getAuthenticationKey()+"&amount="+taxData.getSubTotal()+"&stripeToken="+token+"&currency=usd"+"&description=test";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dismissLoader();
                    pay_dialog.dismiss();
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.has("status")){
                        boolean status=jsonObject.getBoolean("status");
                        if(status){

                            Placeorder(type);
                        }
                        else{
                            Toast.makeText(CheckOutFromCartActivity.this, jsonObject.optString("status_message"), Toast.LENGTH_SHORT).show();
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissLoader();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void listener() {
        CartReqParam cartReqParam = new CartReqParam(
                appController.getAuthenticationKey(),
                appController.getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")));
        viewModel.getCartListItems(cartListMachine,cartReqParam);
        cartListMachine.observe(this, data -> {
            switch (data.status) {
                case SUCCESS:
                    if (data.data.getStatus()) {
                        if(data.data.getData().getFarmProductCartList().size()>0){
                            List<FarmProductCartList>farmProductCartLists=data.data.getData().getFarmProductCartList();
                            StringBuilder price_=new StringBuilder();
                            StringBuilder quat=new StringBuilder();
                            StringBuilder item_id=new StringBuilder();
                            StringBuilder size_id=new StringBuilder();
                            StringBuilder extra=new StringBuilder();
                            StringBuilder unit=new StringBuilder();
                            subTotalAmount=0.0;
                            for(FarmProductCartList farmProductCartList:farmProductCartLists){
                                if(price_.length()>0){
                                    price_.append(",");
                                }
                                if(quat.length()>0){
                                    quat.append(",");
                                }
                                if(item_id.length()>0){
                                    item_id.append(",");
                                }
                                if(size_id.length()>0){
                                    size_id.append(",");
                                }
                                if(extra.length()>0){
                                    extra.append(",");
                                }
                                price_.append(farmProductCartList.getItemPrice());
                                quat.append(farmProductCartList.getItemQuantity());
                                item_id.append(farmProductCartList.getItemId());
                                unit.append(farmProductCartList.getItemUnit());
                                size_id.append(farmProductCartList.getItemSize());
                                extra.append(farmProductCartList.getItemSize());
                                subTotalAmount = subTotalAmount + Double.parseDouble(farmProductCartList.getItemPrice())*Integer.parseInt(farmProductCartList.getItemQuantity());
                            }
                            price=price_.toString();
                            itemid=item_id.toString();
                            quantity=quat.toString();
                            item_unit_type=unit.toString();
                            str_sizeid=size_id.toString();
                            extraitemid=extra.toString();
                            Log.i("order",price+"_"+itemid+"_"+quantity+"_"+item_unit_type+"_"+str_sizeid+"_"+extraitemid);
                        }
                        else{

                        }

//                        cartListData(data.data.getData().getFarmProductCartList());

                    } else {

                    }
                    break;
                case LOADING:

                    break;
                case ERROR:
                    break;
            }
        });


    }
    private void Placeorder(int type){
        String addres="";
        String address_id="0";
        if(address!=null){
            addres=address.getAddress();
            address_id=address.getAddress_id();
        }
        SubmitRequestParam param = new SubmitRequestParam(appController.getAuthenticationKey(),
                "0",
                "0",
                "",
                "",
                addres,
                "",
                String.valueOf(type),
                "",
                time,
                date,
                "0",
                String.valueOf(taxData.getDiscountAmount()),
                taxData.getSubTotal(),
                taxData.getDeliveryCharge(),
                taxData.getDeliveryCharge(),
                taxData.getPackageFeeAmount(),
                taxData.getgSTTaxAmount(),
                subTotalAmount.toString(),
                String.valueOf(pay_type),
                address_id,
                appController.getLoginId(),
                "",
                item_unit_type,
                str_sizeid,
                price,
                quantity,
                itemid,
                "",
                SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString());

        viewModel.submitOrder(submitMachine, param);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_from_cart);
        App.finish_activity=false;
        gpsTracker = new GPSTracker(this);
        farmDeliveryStatus= DroidPrefs.get(this,"delivery_radius",FarmDeliveryStatus.class);

        setupToolbar(new ToolbarConfig("CheckOut", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        })));

        Intent intent = getIntent();
        taxData = (TaxData) intent.getSerializableExtra(Constant.DATA_INTENT);
        order_type=intent.getStringExtra("order_type");
        if(order_type.equalsIgnoreCase("Delivery")){
            Intent delivery = new Intent(CheckOutFromCartActivity.this, MyAddressActivity.class);
            startActivityForResult(delivery, 1254);
        }
        else{
            startActivityForResult(new Intent(CheckOutFromCartActivity.this,PlaceOrderActivity.class),280);
//            SubmitRequestParam requestParam = new SubmitRequestParam(AppController.get().getAuthenticationKey(),
//                    AppController.get().getLoginId());
//
//            viewModel.getOrderDate(dateStateMachine, requestParam);
        }
        taxData.setApplyCouponButton(false);
        taxData.setRemoveDiscountButton(false);
        if (taxData.getDiscountAmount() > 1) {
            taxData.setDiscountTextView(true);
        } else {
            taxData.setDiscountTextView(false);
        }
        taxData.setDiscountAmount(OrderSingleton.getInstance().getCoupon_discount_amount());

        CheckOutCartAddressItems addressItems = new CheckOutCartAddressItems(
                "",
                "Change Address",
                "Please change address",
                "",
                true,
                true);

        prepareItem(taxData, addressItems);

        init();
        listener();
        getPaymentkey();

        timeStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    time_list=response.data.getData().getAllTimeList();
                    dialogTimeSelection(time_list,1);
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
        dateStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
date_list=response.data.getData().getAllDateList();
                    dialogTimeSelection(date_list,0);
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
        submitMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    if(response.data.getStatusCode().equalsIgnoreCase("0")){
                        OrderData orderData=response.data.getData();
                                startActivity(new Intent(this, OrderSuccessDetailActivity.class).putExtra("order_data",orderData).putExtra("order_type",order_type));
                        App.finish_activity=true;
                        finish();
                    }
                    else{
                        Toast.makeText(this, response.data.getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }

                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.check_out_from_cart_recyclerView);
        adapter = new CheckOutCartItemAdapter(this, this, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(50, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItem(TaxData taxData, CheckOutCartAddressItems addressItems) {
        items.clear();
        if (order_type.equalsIgnoreCase("Delivery")) {
            items.add(new SimpleTitleItem("Delivery Address"));
            items.add(addressItems);
        } else {

        }
        items.add(new SimpleTitleItem("Payment Methods"));
        items.add(new PaymentMethodsItems());
        items.add(MyCartTransformer.getTaxDataItem(taxData));
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCheckOutClicked() {

        if (paymentType == -1) {
            Toast.makeText(CheckOutFromCartActivity.this, "Please choose payment method", Toast.LENGTH_SHORT).show();
        }
        else if(order_type.equalsIgnoreCase("Delivery")){
            if(address==null) {
                Toast.makeText(CheckOutFromCartActivity.this, "Please select address", Toast.LENGTH_SHORT).show();
            }

            else{
                goToPayment();
            }
        }
        else {
           goToPayment();
        }
    }
    private void goToPayment(){
        Intent intent = getIntent();
        taxData  = (TaxData) intent.getSerializableExtra(Constant.DATA_INTENT);
        if(order_type.equalsIgnoreCase("Delivery")){
            if(dis>=farmDeliveryStatus.farm_delivery_status){
                Toast.makeText(this, "We Don't Deliver here kindly change address", Toast.LENGTH_SHORT).show();
            }
            else{



                int type=0;


                if(pay_type.equalsIgnoreCase("Cash")){
                    Placeorder(type);
                }
                else if(pay_type.equalsIgnoreCase("Credit/Debit")){
                    dialogOpen(type);
                }

            }
        }
        else{
            int type=1;
            if(pay_type.equalsIgnoreCase("Cash")){
                Placeorder(type);
            }
            else if(pay_type.equalsIgnoreCase("Credit/Debit")){
                dialogOpen(type);
            }
        }



    }

    @Override
    public void onCouponClicked(String couponCode) {

    }

    @Override
    public void onEditAddressClicked(CheckOutCartAddressItems addressDetail) {
        Intent intent = new Intent(CheckOutFromCartActivity.this, MyAddressActivity.class);
        startActivityForResult(intent, 1254);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1254) {
            if (data != null) {
                 address = (CheckOutCartAddressItems) data.getSerializableExtra(Constant.DATA_INTENT);
                 if(address!=null){
                     dis=distance(farmDeliveryStatus.farm_lat,farmDeliveryStatus.farm_long,address.getAddress_lat(),address.getAddress_long());
                     dis=dis*1.609;

                 }

                prepareItem(taxData, address);
                adapter.updateData(items);
                if(time==null){
                    startActivityForResult(new Intent(CheckOutFromCartActivity.this,PlaceOrderActivity.class),280);
//                    SubmitRequestParam requestParam = new SubmitRequestParam(AppController.get().getAuthenticationKey(),
//                            AppController.get().getLoginId());
//
//                    viewModel.getOrderDate(dateStateMachine, requestParam);
                }
            }
        }
        else if(requestCode==280){
            if(data!=null){
                if(data.hasExtra("date")){
                    date=data.getStringExtra("date");
                    time=data.getStringExtra("time");
                }
            }
        }

    }

    @Override
    public void onPaymentMethodCheckChangeListener(int type,String pay_type) { //Cash 0, Card 1, Wallet 2
        this.paymentType = type;
        this.pay_type=pay_type;
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = deg2rad(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0); }

    @Override
    protected void onResume() {
        super.onResume();
        if(App.finish_activity){
            finish();
        }
    }


}