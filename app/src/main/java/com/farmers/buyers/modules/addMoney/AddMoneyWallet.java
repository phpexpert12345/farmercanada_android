package com.farmers.buyers.modules.addMoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.StripePay;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.cart.order.PlaceOrderActivity;
import com.farmers.buyers.modules.signUp.SignUpActivity;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.SubmitOtpActivity;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.remote.ApiConstants;
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
import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class AddMoneyWallet extends BaseActivity implements View.OnClickListener {
    StripePay stripePay;
 Dialog pay_dialog;


    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddMoneyViewModel.class)) {
                return (T) new AddMoneyViewModel();
            }
            return null;
        }
    };

    private AddMoneyViewModel viewModel = factory.create(AddMoneyViewModel.class);
    private MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine = new MutableLiveData<>();

    public ImageView wallet_back_image;
    public RadioButton radio_credit;
    public LinearLayout linear_credit;
    public Button bt_add;
    public EditText ed_amount, ed_transaction_id, ed_status;
    private AppController appController = AppController.get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_wallet);

        init();
    }

    private void init() {
        wallet_back_image = findViewById(R.id.wallet_back_image);
        bt_add = findViewById(R.id.bt_add);
        ed_amount = findViewById(R.id.ed_amount);
        ed_transaction_id = findViewById(R.id.ed_transaction_id);
        ed_status = findViewById(R.id.ed_status);
        linear_credit=findViewById(R.id.linear_credit);
        radio_credit=findViewById(R.id.radio_credit);
getPaymentkey();
        stateMachine.observe(this, signUpApiModelDataFetchState -> {
            switch (signUpApiModelDataFetchState.status) {
                case LOADING: {
//                    loading();
                    break;
                }

                case SUCCESS: {
                    success(signUpApiModelDataFetchState.status_message);
                    break;
                }

                case ERROR: {
                    error(signUpApiModelDataFetchState.status_message);
                    break;
                }
            }
        });

        bt_add.setOnClickListener(this);
        wallet_back_image.setOnClickListener(this);
        linear_credit.setOnClickListener(this);
    }

    private void loading() {
        showLoader();
    }

    private void success(String msg) {
        dismissLoader();
        String current_price=DroidPrefs.get(this,"wallet_amount",String.class);
        double price= Double.parseDouble(current_price);
        price+=Double.parseDouble(ed_amount.getText().toString());
        DroidPrefs.apply(this,"wallet_amount",String.format("%.2f",price));
        Log.i("price",price+"");
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:

                if(radio_credit.isChecked()) {
                    if(ed_amount.getText().toString().isEmpty()){
                        Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        dialogOpen(0);
                    }
                }
                else{
                    Toast.makeText(this, "Please select option", Toast.LENGTH_SHORT).show();
                }
//                addMoney();
                break;

            case R.id.wallet_back_image:
                finish();
                break;
            case R.id.linear_credit:
                radio_credit.setChecked(true);
                break;

        }
    }

    private void addMoney(String tra_id,String status) {
        AddMoneyRequestParams addMoneyRequestParams = new AddMoneyRequestParams(AppController.get().getLoginId(),
                ed_amount.getText().toString().trim(),
                tra_id,
               status, AppController.get().getAuthenticationKey());
        viewModel.addMoney(stateMachine, addMoneyRequestParams);
    }
    private void getPaymentkey(){
        String url= ApiConstants.BASE_URL+ApiConstants.GET_PAYMENT_KEY_WALLET+"?farm_id="+ SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString()+"&auth_key="+appController.getAuthenticationKey();
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
    private void dialogOpen(int type) {
        pay_dialog = new Dialog(AddMoneyWallet.this);
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
        Stripe stripe = new Stripe(AddMoneyWallet.this, stripePay.stripe_publishKey);
        final Card cardToSave = card_details.getCard();
        if (cardToSave != null) {
             showLoader();
            //Toast.makeText(getApplicationContext(),getResources().getString(R.string.wait),Toast.LENGTH_LONG).show();
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
                    Toast.makeText(AddMoneyWallet.this, "Please try again", Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onSuccess(Token token) {

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
            Toast.makeText(this, "Please enter valid card details", Toast.LENGTH_SHORT).show();
        }
    }
    private void stripePayment(String token,int type){
        String url= ApiConstants.BASE_URL+ApiConstants.STRIPE_PAY_WALLET+"?farm_id="+ SharedPreferenceManager.getInstance().getSharedPreferences("FARM_ID", "").toString()+"&auth_key="+appController.getAuthenticationKey()+"&amount="+ ed_amount.getText().toString().trim()+"&stripeToken="+token+"&currency=usd"+"&description=test";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    pay_dialog.dismiss();
                    Log.i("res",response);
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.has("status")){
                        boolean status=jsonObject.getBoolean("status");

                        if(status){
                           addMoney("test","Credited");
                        }
                        else{
                            Toast.makeText(AddMoneyWallet.this, jsonObject.optString("status_message"), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}