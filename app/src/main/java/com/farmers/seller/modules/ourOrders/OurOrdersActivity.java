package com.farmers.seller.modules.ourOrders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.AppPagerAdapter;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponActivity;
import com.farmers.buyers.modules.seller.manageCalender.ManageCalenderActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.seller.sellerProfile.SellerProfileActivity;
import com.farmers.seller.modules.broadcastMessage.BroadCastMessageTransformer;
import com.farmers.seller.modules.broadcastMessage.activity.BroadcastMessageActivity;
import com.farmers.seller.modules.broadcastMessage.adapter.BroadCastMessageListAdapter;
import com.farmers.seller.modules.ourOrders.adapter.SideMenuListAdapter;
import com.farmers.seller.modules.ourOrders.model.SideMenuListItem;
import com.farmers.seller.modules.ourOrders.ongoingOrder.OngoingOrderFragment;
import com.farmers.seller.modules.ourOrders.ourOrder.OurOrdersFragment;
import com.farmers.seller.modules.ourOrders.pastOrder.PastOrderFragment;
import com.farmers.seller.modules.ourOrders.view.SideMenuListViewHolder;
import com.farmers.seller.modules.referFriends.SellerReferFriendsActivity;
import com.farmers.seller.modules.setupSellerAccount.documentUpload.DocumentUploadActivity;
import com.farmers.seller.modules.workingHour.WorkingHourActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OurOrdersActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, SideMenuListViewHolder.SideMenuItemClickListener {

    public DrawerLayout drawer;
    public ImageView nav_icon;
    public TextView tv_title_name;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public OurOrdersFragment tab1;
    public OngoingOrderFragment tab2;
    public PastOrderFragment tab3;
    private RecyclerView rv_side_menu_list;
    private SideMenuListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_orders);

        init();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav_icon = findViewById(R.id.nav_icon);
        tv_title_name = findViewById(R.id.tv_title_name);
        nav_icon.setOnClickListener(nav_iconListener);
        drawer = findViewById(R.id.drawer_layout);
        tv_title_name.setText("Our Order's");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = findViewById(R.id.notification_tab_layout);
        viewPager = findViewById(R.id.notification_viewPager);
        setUpTabLayout();

        rv_side_menu_list = findViewById(R.id.rv_side_menu_list);
        adapter = new SideMenuListAdapter(this);
        rv_side_menu_list.setAdapter(adapter);
        rv_side_menu_list.setLayoutManager(new LinearLayoutManager(this));
        prepareItems();
        adapter.updateData(items);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private View.OnClickListener nav_iconListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawer.openDrawer(GravityCompat.START);
        }
    };

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

    private void prepareItems() {
        items.addAll(OurOrdersTransformer.getReviewedList());
    }

    @Override
    public void onSideMenuItemClicked(SideMenuListItem item) {

        drawer.closeDrawers();

        if (item.getId() == 1) {
            startActivity(new Intent(OurOrdersActivity.this, ProductListActivity.class));
        } else if (item.getId() == 2) {
        } else if (item.getId() == 3) {
            startActivity(new Intent(OurOrdersActivity.this, SellerReferFriendsActivity.class));
        } else if (item.getId() == 4) {
            startActivity(new Intent(OurOrdersActivity.this, BroadcastMessageActivity.class));
        } else if (item.getId() == 5) {
            startActivity(new Intent(OurOrdersActivity.this, ManageCouponActivity.class));
        } else if (item.getId() == 6) {
            startActivity(new Intent(OurOrdersActivity.this, ManageCalenderActivity.class));
        } else if (item.getId() == 7) {
            startActivity(new Intent(OurOrdersActivity.this, SellerProfileActivity.class));
        } else if (item.getId() == 8) {
            startActivity(new Intent(OurOrdersActivity.this, WorkingHourActivity.class));
        }
    }
}