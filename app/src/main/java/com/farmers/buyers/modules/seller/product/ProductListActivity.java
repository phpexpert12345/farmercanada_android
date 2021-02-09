package com.farmers.buyers.modules.seller.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.product.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setupToolbar(new ToolbarConfig("Product List", true, R.drawable.ic_arrow_back_black, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.drawable.ic_pluc, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductListActivity.this, AddProductActivity.class));

            }
        })));
        prepareItems();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.product_recyclerView);
        adapter = new ProductListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(ProductListTransformer.getProducts());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}