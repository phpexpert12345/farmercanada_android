package com.farmers.seller.modules.workingHour;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.seller.modules.workingHour.deliveryTime.DeliveryTimeFragment;
import com.farmers.seller.modules.workingHour.pickupTime.PickupTimeFragment;
import com.farmers.seller.modules.workingHour.storeTime.StoreTimeFragment;
import com.google.android.material.tabs.TabLayout;

public class WorkingHourActivity extends BaseActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;
    public StoreTimeFragment tab1;
    public PickupTimeFragment tab2;
    public DeliveryTimeFragment tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hour);
        setupToolbar(new ToolbarConfig("Working Hours", true, new View.OnClickListener() {
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
        setUpTabLayout();
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

                getOurOrdersList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                getOurOrdersList(tab.getPosition());
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
        tab1 = new StoreTimeFragment().get();
        tab2 = new PickupTimeFragment().get();
        tab3 = new DeliveryTimeFragment().get();

        AppPagerAdapter adapter = new AppPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(tab1, tab1.getTitle());
        adapter.addFragment(tab2, tab2.getTitle());
        adapter.addFragment(tab3, tab3.getTitle());

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void getOurOrdersList(int position) {
        switch (position) {
            case 0: {
                tab1.getStoreTime();
                break;
            }
            case 1: {
                tab2.getPickupTime();
                break;
            }
            case 3: {
                tab3.getDeliveryTime();
                break;
            }
        }
    }
}