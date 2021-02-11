package com.farmers.buyers.modules.address;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.SwipeControllerActions;
import com.farmers.buyers.common.utils.SwipeHelper;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.adapter.MyAddressAdapter;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.cart.myCart.MyCartActivity;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private MyAddressAdapter adapter;
    private TextView addNewAddress;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        setupToolbar(new ToolbarConfig("Address", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        prepareItems();
        init();
        listener();
    }

    private void init() {
        recyclerView = findViewById(R.id.my_address_recyclerView);
        addNewAddress = findViewById(R.id.add_new_address);
        adapter = new MyAddressAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.updateData(items);



        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView, 250) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List buffer) {

                buffer.add(new MyButton(MyAddressActivity.this, R.drawable.ic_delete_round, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);
                            }
                        }
                ));

                buffer.add(new MyButton(MyAddressActivity.this, R.drawable.ic_edit, Color.parseColor("#FFFFFFFF"), new SwipeControllerActions() {
                    @Override
                    public void onRightClicked(int position) {
                        super.onRightClicked(position);
                    }
                }));

            }
        };



        ItemTouchHelper helper = new ItemTouchHelper(swipeHelper);
        helper.attachToRecyclerView(recyclerView);

    }

    private void listener() {
        addNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyAddressActivity.this, AddNewAddressActivity.class));
            }
        });
    }

    private void prepareItems() {
        items.addAll(AddressTransformer.getAddress());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}