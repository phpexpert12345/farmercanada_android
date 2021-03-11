package com.farmers.buyers.modules.cart.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.app.App;
import com.farmers.buyers.common.model.OrderData;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderSuccessDetailActivity extends AppCompatActivity {

    Button continueShoppingBtn, goToOrderBtn;
    CircleImageView order_success_user_image;
    OrderData orderData;
Intent intent;
String order_type;
TextView order_id,order_date,order_type_txt,txt_farm_name,txt_farm_address,txt_farm_phone,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success_detail);
        intent=getIntent();
        goToOrderBtn = findViewById(R.id.order_success_continue_go_to_order_btn);
        continueShoppingBtn = findViewById(R.id.order_success_continue_shopping_btn);
        order_success_user_image=findViewById(R.id.order_success_user_image);
        txt_farm_address=findViewById(R.id.txt_farm_address);
        txt_farm_name=findViewById(R.id.txt_farm_name);
        txt_farm_phone=findViewById(R.id.txt_farm_phone);
        order_id=findViewById(R.id.order_id);
        order_date=findViewById(R.id.order_date);
        order_type_txt=findViewById(R.id.order_type_txt);
        description=findViewById(R.id.description);
        txt_farm_phone.setVisibility(View.GONE);
        if(intent.hasExtra("order_data")){
            order_type=intent.getStringExtra("order_type");
            orderData= (OrderData) intent.getSerializableExtra("order_data");
            order_id.setText(orderData.order_number);
            order_date.setText(orderData.order_date+"/"+orderData.order_time);
            order_type_txt.setText(order_type);
            Glide.with(this).load(orderData.farm_logo).placeholder(R.drawable.logo).into(order_success_user_image);
            txt_farm_name.setText(orderData.farm_name);
            txt_farm_address.setText(orderData.farm_address);
            description.setText(orderData.thank_you_confirmation_description);
        }

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

    @Override
    public void onBackPressed() {
        App.finish_activity=true;
        finish();
    }
}