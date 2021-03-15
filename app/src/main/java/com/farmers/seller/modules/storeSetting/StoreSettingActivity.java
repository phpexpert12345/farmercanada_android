package com.farmers.seller.modules.storeSetting;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.seller.modules.storeSetting.model.StoreFarmDetails;
import com.farmers.seller.modules.workingHour.WorkingHourActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class StoreSettingActivity extends BaseActivity implements View.OnClickListener {

    private CardView card_change_store, card_service, card_document, card_working_hours;
    private AppController appController = AppController.get();
    StoreFarmDetails storeFarmDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting);

        setupToolbar(new ToolbarConfig("Store Setting", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();

    }

    private void init() {
        card_change_store = findViewById(R.id.card_change_store);
        card_service = findViewById(R.id.card_service);
        card_document = findViewById(R.id.card_document);
        card_working_hours = findViewById(R.id.card_working_hours);
        card_change_store.setOnClickListener(this);
        card_service.setOnClickListener(this);
        card_document.setOnClickListener(this);
        card_working_hours.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStoreDetails();
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_change_store:
                startActivity(new Intent(StoreSettingActivity.this,ChangeStoreActivity.class).putExtra("store",storeFarmDetails));
                break;
            case R.id.card_service:
                startActivity(new Intent(StoreSettingActivity.this,UpdateServiceActivity.class).putExtra("store",storeFarmDetails));
                break;
            case R.id.card_document:
                startActivity(new Intent(StoreSettingActivity.this,UpdateDocActivity.class).putExtra("store",storeFarmDetails));
                break;
            case R.id.card_working_hours:
                startActivity(new Intent(StoreSettingActivity.this, WorkingHourActivity.class));
                break;
        }
    }
    public void getStoreDetails(){
       String url=ApiConstants.STORE_DETAILS+"?auth_key="+appController.getAuthenticationKey()+"&LoginId="+appController.getLoginId()+"&farm_id="+appController.getFarmId();
       Log.i("url",url);
       showLoader();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissLoader();
Log.i("response",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean status=jsonObject.getBoolean("status");
                    if(status){
                        JSONObject data=jsonObject.getJSONObject("data");
                        if(data.has("StoreFarmDetail")){
                            Gson gson=new Gson();
                            if(data.get("StoreFarmDetail") instanceof JSONArray){
                                JSONArray jsonArray=data.getJSONArray("StoreFarmDetail");

                                Type type=new TypeToken<List<StoreFarmDetails>>(){}.getType();
                                List<StoreFarmDetails> storeFarmlist=gson.fromJson(jsonArray.toString(),type);
                                if(storeFarmlist.size()>0){
                                    storeFarmDetails=storeFarmlist.get(0);
                                }
                            }
                            else{
                                JSONObject store=data.getJSONObject("StoreFarmDetail");
                                Type type=new TypeToken<StoreFarmDetails>(){}.getType();
                                storeFarmDetails=gson.fromJson(store.toString(),type);
                            }
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}