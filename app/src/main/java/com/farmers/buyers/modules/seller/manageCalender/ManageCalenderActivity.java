package com.farmers.buyers.modules.seller.manageCalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;

public class ManageCalenderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_calender);

        setupToolbar(new BaseActivity.ToolbarConfig("Manage Calender", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}