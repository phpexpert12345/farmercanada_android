package com.farmers.seller.modules.ourOrders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.ratingAndReview.review.ReviewFragment;
import com.farmers.buyers.modules.ratingAndReview.reviewed.ReviewedFragment;
import com.farmers.seller.modules.ourOrders.ongoingOrder.OngoingOrderFragment;
import com.farmers.seller.modules.ourOrders.ourOrder.OurOrdersFragment;
import com.farmers.seller.modules.ourOrders.pastOrder.PastOrderFragment;
import com.google.android.material.tabs.TabLayout;

public class OurOrdersActivity extends BaseActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;
    public OurOrdersFragment tab1;
    public OngoingOrderFragment tab2;
    public PastOrderFragment tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_orders);

        setupToolbar(new ToolbarConfig("Our Order's", true, new View.OnClickListener() {
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
        tab1 = new OurOrdersFragment().get();
        tab2 = new OngoingOrderFragment().get();
        tab3 = new PastOrderFragment().get();

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
                tab1.getOurOrder();
                break;
            }

            case 1: {
                tab2.getOngoingOrder();
                break;
            }
            case 3: {
                tab3.getPastOrder();
                break;
            }
        }
    }
}