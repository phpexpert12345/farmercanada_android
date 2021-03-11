package com.farmers.buyers.modules.inbox;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.NotificationLists;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.inbox.adapter.NotificationAdapter;
import com.farmers.buyers.modules.inbox.message.MessageFragment;
import com.farmers.buyers.modules.inbox.notification.NotificationFragment;
import com.farmers.buyers.remote.ApiConstants;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class NotificationsActivity extends BaseActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    NotificationFragment tab2;
    MessageFragment tab1;
    RecyclerView recyler_inbox;
    LinearLayout inbox_error_ll;
    TextView inbox_error_tv;
    private AppController appController = AppController.get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setupToolbar(new ToolbarConfig("Inbox", true, new View.OnClickListener() {
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

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
        tabLayout = findViewById(R.id.notification_tab_layout);
        viewPager = findViewById(R.id.notification_viewPager);
        recyler_inbox=findViewById(R.id.recyler_inbox);
        inbox_error_ll=findViewById(R.id.inbox_error_ll);
        inbox_error_tv=findViewById(R.id.inbox_error_tv);
        getNotifications();
//        setUpTabLayout();

    }
    private void getNotifications(){
        showLoader();
        String url= ApiConstants.NOTIFICATION_LIST+"?auth_key="+appController.getAuthenticationKey()+"&LoginId="+appController.getLoginId();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
dismissLoader();
                if(response!=null){

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        boolean status=jsonObject.getBoolean("status");
                        if(status){
                            JSONObject data=jsonObject.getJSONObject("data");
                            if(data.has("NotificationList")){
                                Gson gson=new Gson();
                                Type type=new TypeToken<List<NotificationLists>>(){}.getType();
                                List<NotificationLists> notificationLists=gson.fromJson(data.getJSONArray("NotificationList").toString(),type);
                                if(notificationLists.size()>0){
                                    inbox_error_ll.setVisibility(View.GONE);
                                    Log.i("size",notificationLists.size()+"");
                                    setAdapter(notificationLists);

                                }
                            }
                        }
                        else{
                            recyler_inbox.setVisibility(View.GONE);
                            inbox_error_ll.setVisibility(View.VISIBLE);
                            inbox_error_tv.setText(jsonObject.optString("status_message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
    private void setAdapter(List<NotificationLists> notificationLists){
        recyler_inbox.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        NotificationAdapter notificationAdapter=new NotificationAdapter(notificationLists,this);
        recyler_inbox.setAdapter(notificationAdapter);
        recyler_inbox.setLayoutManager(linearLayoutManager);
    }

    private void setUpTabLayout() {

        setUpStateAdapter();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                int count = fm.getBackStackEntryCount();
                if (count > 1)
                    getSupportFragmentManager().popBackStack();
                ft.commit();

                getNotificationList(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                getNotificationList(tab.getPosition());
            }
        });

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.getTabAt(0).select();
            }
        });
    }

    private void setUpStateAdapter() {
        tab1 = new MessageFragment().get();
        tab2 = new NotificationFragment().get();

        AppPagerAdapter adapter = new AppPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(tab1, tab1.getTitle());
        adapter.addFragment(tab2, tab2.getTitle());

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);

    }

    private void getNotificationList(int position) {
        switch (position) {
            case 0: {
                tab1.getMessages();
                break;
            }

            case 1: {
                tab2.getNotification();
                break;
            }
        }
    }
}