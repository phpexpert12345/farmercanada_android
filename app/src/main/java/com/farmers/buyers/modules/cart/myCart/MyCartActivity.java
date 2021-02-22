package com.farmers.buyers.modules.cart.myCart;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.SwipeControllerActions;
import com.farmers.buyers.common.utils.SwipeHelper;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.CheckOutFromCartActivity;
import com.farmers.buyers.modules.cart.myCart.adapter.MyCartAdapter;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;
import com.farmers.buyers.modules.cart.myCart.view.MyCartItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends BaseActivity implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners, MyCartCheckoutViewHolder.MyCoupounClickListeners, MyCartItemViewHolder.IncreaseCallback, MyCartItemViewHolder.DecreaseCallback {

    private RecyclerView recyclerView;
    private MyCartAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToolbarConfig config = new ToolbarConfig(
                "Cart", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig((R.drawable.ic_notification), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })
        );

        setContentView(R.layout.activity_my_cart);
        setupToolbar(config);

        prepareData();
        init();


        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView, 250) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List buffer) {

                buffer.add(new MyButton(MyCartActivity.this, R.drawable.ic_delete_round, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);


                            }
                        }
                ));

            }
        };



        ItemTouchHelper helper = new ItemTouchHelper(swipeHelper);
        helper.attachToRecyclerView(recyclerView);
    }

    private void init() {
        recyclerView = findViewById(R.id._my_cart_recyclerView);
        adapter = new MyCartAdapter(this,this,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        adapter.updateData(items);

        }

    private void prepareData() {
       // items.addAll(MyCartTransformer.getMyCartItem());
       // items.add(new MyCartCheckOutItem());
    }


    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onCheckOutClicked() {
        startActivity(new Intent(this, CheckOutFromCartActivity.class));
    }

    @Override
    public void onCouponClicked(String couponCode) {
      //  Toast.makeText(MyCartActivity.this,couponCode,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void increaseCallback(String cartId) {

    }

    @Override
    public void decreseCallback(String cartId) {

    }
}