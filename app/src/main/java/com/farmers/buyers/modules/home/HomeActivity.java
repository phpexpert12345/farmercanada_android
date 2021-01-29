package com.farmers.buyers.modules.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartFragment;
import com.farmers.buyers.modules.home.adapter.HomeAdapter;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;
import com.farmers.buyers.modules.profile.MyProfileFragment;
import com.farmers.buyers.modules.saveFarms.SavedFarmsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {
        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.navigation_home : {
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;
            }

            case R.id.navigation_profile : {
                fragment = new MyProfileFragment();
                loadFragment(fragment);
                break;
            }

            case R.id.navigation_save : {
                fragment = new SavedFarmsFragment();
                loadFragment(fragment);
                break;

            }

            case R.id.navigation_cart : {
                fragment = new MyCartFragment();
                loadFragment(fragment);
                break;
            }
        }
        return loadFragment(fragment);
    }
}