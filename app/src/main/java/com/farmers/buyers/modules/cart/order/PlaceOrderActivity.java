package com.farmers.buyers.modules.cart.order;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.common.model.StripePay;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.OrderSuccessDialog;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartReqParam;
import com.farmers.buyers.modules.cart.myCart.model.cartList.FarmProductCartList;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.cart.order.adapter.PlaceOrderAdapter;
import com.farmers.buyers.modules.cart.order.adapter.TimeListAdapter;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.modules.cart.order.view.PlaceOrderSlotItemViewHolder;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.storage.Constant;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends BaseActivity implements OrderSuccessDialog.OnDialogClickListeners,
        PlaceOrderSlotItemViewHolder.SlotCheckedListener, TimeListAdapter.TimeItemClickListener {

    private RecyclerView recyclerView;
    private Button paymentButton;
    private PlaceOrderAdapter adapter;
    CheckOutCartAddressItems address;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private OrderSuccessDialog dialog;
    private Dialog pay_dialog;
    String price,quantity,itemid,item_unit_type,str_sizeid,extraitemid;
    Double subTotalAmount = 0.0;
    String order_type;
    String pay_type;
    TaxData taxData;
    String date;
    String time;
    StripePay stripePay;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SubmitOrderViewModel.class)) {
                return (T) new SubmitOrderViewModel();
            }
            return null;
        }
    };
    private SubmitOrderViewModel viewModel = factory.create(SubmitOrderViewModel.class);
    private MutableLiveData<DataFetchState<SubmitResponse>> submitMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> dateStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddressApiModel>> timeStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    private RecyclerView.Adapter mTimeAdapter;
    private RecyclerView rv_time_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        App.finish_activity=false;
        ToolbarConfig config = new ToolbarConfig("Add Date & Time", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }));

        setupToolbar(config);
        prepareItems();
        init();
        listener();
        getPaymentkey();



        dateStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    adapter.updateData(viewModel.items);
                    date=response.data.getData().getAllDateList().get(0).current_date;
                    callTimeSlot(response.data.getData().getAllDateList().get(0).current_date);
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });

        timeStateMachine.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS:
                    dismissLoader();
                    mTimeAdapter = new TimeListAdapter(PlaceOrderActivity.this, response.data.getData().getAllTimeList(),
                            this);
                    rv_time_list.setAdapter(mTimeAdapter);
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
                    dialog.showDialog();
                    App.finish_activity=true;
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
    }

    private void callTimeSlot(String date) {

        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                date);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }

    private void init() {
        recyclerView = findViewById(R.id.place_order_recyclerView);
        paymentButton = findViewById(R.id.place_order_btn);
        adapter = new PlaceOrderAdapter(this);
        dialog = new OrderSuccessDialog(this, this, false);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rv_time_list = findViewById(R.id.rv_time_list);
        rv_time_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        SubmitRequestParam requestParam = new SubmitRequestParam(AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId());

        viewModel.getOrderDate(dateStateMachine, requestParam);
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
                    dismissLoader();
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
                    showLoader();
                    break;
                case ERROR:
                    break;
            }
        });
        paymentButton.setOnClickListener(view -> {
            Intent intent = getIntent();
            address= (CheckOutCartAddressItems) intent.getSerializableExtra("address");
            pay_type=intent.getStringExtra("pay_type");
           taxData  = (TaxData) intent.getSerializableExtra(Constant.DATA_INTENT);
            order_type=intent.getStringExtra("order_type");
            int type;
            switch (order_type){
                case "Delivery":
                    type=0;
                    break;
                case "Pickup":
                    type=1;
                    break;
                default:
                    type=0;
            }
            if(pay_type.equalsIgnoreCase("Cash")){
                Placeorder(type);
            }
            else if(pay_type.equalsIgnoreCase("Credit/Debit")){
                dialogOpen(type);
            }
//            SubmitRequestParam param = new SubmitRequestParam(appController.getAuthenticationKey(),
//                    "0",
//                    "0",
//                    "",
//                    "",
//                    address.getAddress(),
//                    "",
//                    String.valueOf(type),
//                    "",
//                    time,
//                    date,
//                    "0",
//                    String.valueOf(taxData.getDiscountAmount()),
//                    taxData.getSubTotal(),
//                    taxData.getDeliveryCharge(),
//                    taxData.getDeliveryCharge(),
//                    taxData.getPackageFeeAmount(),
//                    taxData.getgSTTaxAmount(),
//                    subTotalAmount.toString(),
//                    String.valueOf(pay_type),
//                    address.getAddress_id(),
//                    appController.getLoginId(),
//                    "",
//                    item_unit_type,
//                    str_sizeid,
//                    price,
//                    quantity,
//                    itemid,
//                    "",
//                    SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString());
//
//            viewModel.submitOrder(submitMachine, param);
        });
    }

    private void prepareItems() {
        items.add(new SimpleTitleItem("Choose delivery slot for this address"));
        items.add(MyCartTransformer.getPlaceOrderSlot());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCancelClicked() {
        dialog.dismissDialog();
    }

    @Override
    public void onSubmitClicked() {
        dialog.dismissDialog();
        startActivity(new Intent(this, OrderSuccessDetailActivity.class));
    }
    private void dialogOpen(int type) {
        pay_dialog = new Dialog(PlaceOrderActivity.this);
        pay_dialog.setContentView(R.layout.dialog_stripe);
        Window window = pay_dialog.getWindow();
        pay_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        CardMultilineWidget card_details = pay_dialog.findViewById(R.id.card_details);
        Button btnPay = pay_dialog.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPaymentcall(card_details,type);
            }
        });


        pay_dialog.show();
    }
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
    private void Placeorder(int type){
        SubmitRequestParam param = new SubmitRequestParam(appController.getAuthenticationKey(),
            "0",
            "0",
            "",
            "",
            address.getAddress(),
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
            address.getAddress_id(),
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
    public void onSlotSelected(String slot, int position) {

        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
date=slot;
        SubmitRequestParam requestParam = new SubmitRequestParam(
                AppController.get().getAuthenticationKey(),
                AppController.get().getLoginId(),
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "")),
                slot);
        viewModel.getOrderTimeByDate(timeStateMachine, requestParam);
    }

    @Override
    public void onTimeItemClicked(String timeSlot) {
        time=timeSlot;
        Toast.makeText(PlaceOrderActivity.this, timeSlot, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(App.finish_activity){
            finish();
        }
    }
    private void createPaymentcall(CardMultilineWidget card_details,int type) {

        //pk_test_51I04dtKD2jSEa72lEFrtZpRLIdB41dgp5cv421yfPcQ2bPjW6dXswhLYlZoSUTuhbGyMtHjI7n4dMHCcp4N8gqKf00kKQTk8UX
        // live==  pk_live_51H335kI4oh76Z6dpfFRYuAHNcBAQtEZGtf6D7Hs2IG92vaM0x9Do2YFgNBmFNUx5d7fdAv9zsHyUxPjkydKfUCEX00j0eCL1ae
        Stripe stripe = new Stripe(PlaceOrderActivity.this, stripePay.stripe_publishKey);
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
                    Toast.makeText(PlaceOrderActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(PlaceOrderActivity.this, jsonObject.optString("status_message"), Toast.LENGTH_SHORT).show();
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

}