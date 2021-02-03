package com.farmers.buyers.modules.orders.subOrderList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.orders.SubOrderExtra;
import com.google.android.material.tabs.TabLayout;

public class SubOrderListActivity extends BaseActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    SubOrderListFragment tab1;
    SubOrderListFragment tab2;
    SubOrderListFragment tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_order_list);
        setupToolbar(new ToolbarConfig("Order", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
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
        tabLayout = findViewById(R.id.sub_order_tab_layout);
        viewPager = findViewById(R.id.sub_order_viewPager);

        setUpTabLayout();
    }

    private void setUpTabLayout() {

        setUpViewPager();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                int count = fm.getBackStackEntryCount();
                if (count > 1) fm.popBackStack();
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.getTabAt(0).select();
            }
        });

    }

    private void setUpViewPager() {
        tab1 = new SubOrderListFragment("Pending", SubOrderExtra.PENDING).get();
        tab2 = new SubOrderListFragment("Accepted", SubOrderExtra.ACCEPTED).get();
        tab3 = new SubOrderListFragment("Rejected", SubOrderExtra.REJECTED).get();

        AppPagerAdapter appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
        appPagerAdapter.addFragment(tab1, tab1.title);
        appPagerAdapter.addFragment(tab2, tab2.title);
        appPagerAdapter.addFragment(tab3, tab3.title);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(appPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);

    }
}