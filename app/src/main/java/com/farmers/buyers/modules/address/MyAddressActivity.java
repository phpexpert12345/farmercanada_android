package com.farmers.buyers.modules.address;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.SwipeControllerActions;
import com.farmers.buyers.common.utils.SwipeHelper;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.adapter.MyAddressAdapter;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.address.model.MyAddressViewModel;
import com.farmers.buyers.modules.login.model.LoginApiModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(MyAddressViewModel.class)) {
                return (T) new MyAddressViewModel();
            }
            return null;
        }
    };

    public MyAddressViewModel viewModel = factory.create(MyAddressViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();

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

        //   prepareItems();
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

        // adapter.updateData(items);


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

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(MyAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    Toast.makeText(MyAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    dismissLoader();
                    items.addAll(AddressTransformer.getAddress(dataFetchState.data.getData().getAllDataModels()));
                    adapter.updateData(items);
                    break;
                }
            }
        });

        viewModel.getAddressList(stateMachine);
    }

    private void listener() {
        addNewAddress.setOnClickListener(view -> startActivity(new Intent(MyAddressActivity.this,
                AddNewAddressActivity.class)));
    }

    private void prepareItems() {
        items.addAll(AddressTransformer.getAddress());
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}