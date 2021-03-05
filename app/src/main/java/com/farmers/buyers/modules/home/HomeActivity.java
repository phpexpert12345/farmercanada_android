package com.farmers.buyers.modules.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.modules.cart.myCart.MyCartFragment;
import com.farmers.buyers.modules.home.homeFragment.HomeFragment;
import com.farmers.buyers.modules.profile.MyProfileFragment;
import com.farmers.buyers.modules.saveFarms.SavedFarmsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
HomeFragment homeFragment;
    Fragment fragment = null;
    BottomNavigationView navigation;
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
        homeFragment=new HomeFragment();
        loadFragment(homeFragment);
         navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    public boolean loadFragment(Fragment fragment) {
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

        switch (item.getItemId()) {

            case R.id.navigation_home: {
                fragment = new HomeFragment();

                loadFragment(fragment);
                break;
            }

            case R.id.navigation_profile: {
                fragment = new MyProfileFragment();
                loadFragment(fragment);
                break;
            }

            case R.id.navigation_save: {
                fragment = new SavedFarmsFragment();
                loadFragment(fragment);
                break;

            }

            case R.id.navigation_cart: {
                fragment = new MyCartFragment();
                loadFragment(fragment);
                break;
            }
        }
        return loadFragment(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            homeFragment.updateAddress();

    }

    @Override
    public void onBackPressed() {

        if(fragment!=null){
            if(!(fragment instanceof HomeFragment)){
                fragment=new HomeFragment();
                loadFragment(homeFragment);
                navigation.setSelectedItemId(R.id.navigation_home);
            }
            else{
               finish();
            }
        }
        else{
            finish();
        }
    }
}