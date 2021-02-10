package com.farmers.buyers.modules.seller.sellerProfile.editProfile;

import android.os.Bundle;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class SellerShopSetting extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shop_setting);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }
}