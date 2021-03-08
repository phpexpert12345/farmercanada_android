package com.farmers.buyers.modules.seller.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.product.adapter.ProductListAdapter;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.ProductListApiModel;
import com.farmers.buyers.modules.seller.product.view.ProductListViewHolder;
import com.farmers.seller.modules.ourOrders.OurOrdersActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends BaseActivity implements View.OnClickListener, ProductListViewHolder.ProductListItemClickListener {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    public LinearLayout ll_add_product;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ProductListViewModel.class));
            return (T) new ProductListViewModel();
        }
    };

    private ProductListViewModel viewModel = factory.create(ProductListViewModel.class);
    private MutableLiveData<DataFetchState<ProductListApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<DeleteProductApiModel>> deleteStateMachine = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setupToolbar(new ToolbarConfig("Product List", true, R.drawable.ic_arrow_back_black, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, true, new ToolbarMenuConfig(R.mipmap.filter_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));
        init();
    }

    private void init() {
        ll_add_product = findViewById(R.id.ll_add_product);
        recyclerView = findViewById(R.id.product_recyclerView);
        adapter = new ProductListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ll_add_product.setOnClickListener(this);

        stateMachine.observe(this, productListApiModelDataFetchState -> {
                    switch (productListApiModelDataFetchState.status) {
                        case LOADING:
                            showLoader();
                            break;
                        case ERROR:
                            dismissLoader();
                            Toast.makeText(ProductListActivity.this, productListApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS: {
                            dismissLoader();
                            bindAdapter();
                            break;
                        }
                    }
                });

        deleteStateMachine.observe(this, deleteProductResponse -> {
            switch (deleteProductResponse.status) {
                case LOADING: showLoader(); break;
                case ERROR: dismissLoader();
                    break;
                case SUCCESS: {
                    dismissLoader();
                    AlertHelper.showAlert(this, "Delete Product", deleteProductResponse.status_message, true, "Ok", true, "", false, new OnAlertClickListener() {
                        @Override
                        public void onNegativeBtnClicked() {

                        }

                        @Override
                        public void onPositiveBtnClicked() {
                            viewModel.getProductList(stateMachine);
                        }
                    });
                    break;
                }
            }

        });
    }

    @Override
    protected void onStart() {
        viewModel.getProductList(stateMachine);
        super.onStart();
    }

    private void bindAdapter() {
        Log.e("items", String.valueOf(viewModel.items.size()));
        adapter.updateData(viewModel.items);
    }
    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(ProductListActivity.this, AddProductActivity.class));
    }

    @Override
    public void onDeleteItemClickListener(int position) {
        AlertHelper.showAlert(this, "Delete Product", "Are you sure you want to delete this product", true, "Delete", true, "Cancel", true, new OnAlertClickListener() {
            @Override
            public void onNegativeBtnClicked() {

            }

            @Override
            public void onPositiveBtnClicked() {
                viewModel.onProductDelete(deleteStateMachine, position);
            }
        });
    }

    @Override
    public void onEditItemClickListener(int position) {

    }
}