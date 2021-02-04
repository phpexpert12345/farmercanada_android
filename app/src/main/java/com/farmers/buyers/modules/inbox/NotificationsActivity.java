package com.farmers.buyers.modules.inbox;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.inbox.message.MessageFragment;
import com.farmers.buyers.modules.inbox.notification.NotificationFragment;
import com.google.android.material.tabs.TabLayout;

public class NotificationsActivity extends BaseActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    NotificationFragment tab2;
    MessageFragment tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setupToolbar(new ToolbarConfig("Inbox", true, new View.OnClickListener() {
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