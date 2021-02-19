package com.farmers.buyers.modules.cart.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;

public class OrderSuccessDetailActivity extends AppCompatActivity {

    Button continueShoppingBtn, goToOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success_detail);

        goToOrderBtn = findViewById(R.id.order_success_continue_go_to_order_btn);
        continueShoppingBtn = findViewById(R.id.order_success_continue_shopping_btn);

        goToOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessDetailActivity.this, SubOrderListActivity.class));
                finish();
            }
        });

        continueShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSuccessDetailActivity.this, HomeActivity.class));
                finish();
            }
        });
    }


}