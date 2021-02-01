package com.farmers.buyers.modules.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.cart.myCart.MyCartFragment;
import com.farmers.buyers.modules.profile.MyProfileFragment;
import com.farmers.buyers.modules.saveFarms.SavedFarmsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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