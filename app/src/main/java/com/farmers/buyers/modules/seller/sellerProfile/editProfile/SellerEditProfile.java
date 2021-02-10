package com.farmers.buyers.modules.seller.sellerProfile.editProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class SellerEditProfile extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_profile);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }
}