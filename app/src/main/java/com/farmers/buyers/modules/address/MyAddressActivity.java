package com.farmers.buyers.modules.address;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.SwipeControllerActions;
import com.farmers.buyers.common.utils.SwipeHelper;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.adapter.MyAddressAdapter;
import com.farmers.buyers.modules.address.model.AddAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.address.model.MyAddressViewModel;
import com.farmers.buyers.modules.address.view.MyAddressListViewHolder;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;
import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.GPSTracker;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity implements MyAddressListViewHolder.AddressItemClickListener {

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
    private MutableLiveData<DataFetchState<AddressApiModel>> deleteStateMachine = new MutableLiveData<>();

    private RecyclerView recyclerView;
    private MyAddressAdapter adapter;
    private TextView addNewAddress;
    private String addressId;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private List<CheckOutCartAddressItems> addressItems = new ArrayList<>();
    Integer comeFrom=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        Intent intent=getIntent();
        if (intent!=null){
            comeFrom=intent.getIntExtra("ComeFrom",0);
        }


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

        init();
        listener();
    }

    private void init() {
        recyclerView = findViewById(R.id.my_address_recyclerView);
        addNewAddress = findViewById(R.id.add_new_address);
        adapter = new MyAddressAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40, EqualSpacingItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView, 250) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List buffer) {
                buffer.add(new MyButton(MyAddressActivity.this, R.drawable.ic_delete_round, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);
                                showConfirmMessage(addressItems.get(position).getAddress_id());
                            }
                        }
                ));

                buffer.add(new MyButton(MyAddressActivity.this, R.drawable.ic_edit, Color.parseColor("#FFFFFFFF"),
                        new SwipeControllerActions() {
                            @Override
                            public void onLeftClicked(int position) {
                                super.onLeftClicked(position);
                                startActivity(new Intent(MyAddressActivity.this,
                                        AddNewAddressActivity.class).
                                        putExtra("KEY_FROM", "EDIT_ADDRESS").
                                        putExtra("ADDRESS_ID", addressItems.get(position).getAddress_id()).
                                        putExtra("ADDRESS", addressItems.get(position).getAddress()).
                                        putExtra("ADDRESS_TITLE", addressItems.get(position).getAddressTitle()));
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
                    addressListSuccess(dataFetchState);
                    break;
                }
            }
        });

        deleteStateMachine.observe(this, dataFetchState -> {
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
                    dismissLoader();
                    Toast.makeText(MyAddressActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    viewModel.getAddressList(stateMachine);
                    break;
                }
            }
        });
    }

    private void addressListSuccess(DataFetchState<AddressApiModel> dataFetchState) {
        dismissLoader();
        items.clear();
        addressItems.clear();
        items.addAll(AddressTransformer.getAddress(dataFetchState.data.getData().getAllDataModels()));
        addressItems.addAll(AddressTransformer.getAddress(dataFetchState.data.getData().getAllDataModels()));
        adapter.updateData(items);
    }

    private void listener() {
        addNewAddress.setOnClickListener(view -> startActivity(new Intent(MyAddressActivity.this,
                AddNewAddressActivity.class).putExtra("KEY_FROM", "ADD_ADDRESS")));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAddressList(stateMachine);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    public void showConfirmMessage(String addressId) {
        AddAddressRequestParams addAddressRequestParams = new AddAddressRequestParams(AppController.get().getLoginId(),
                addressId,
                AppController.get().getAuthenticationKey());

        new AlertDialog.Builder(MyAddressActivity.this)
                .setTitle("Farmer Alert")
                .setMessage("Are you sure ! You want to delete ?")
                .setCancelable(false)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    viewModel.deleteAddress(deleteStateMachine, addAddressRequestParams);
                    dialog.dismiss();
                })
                .setIcon(getResources().getDrawable(R.drawable.logo))
                .show();
    }

    @Override
    public void onAddressItemClicked(CheckOutCartAddressItems addressObj) {
        this.addressId = addressObj.getAddress_id();

        if (comeFrom==0){
                Intent intent=new Intent();
                intent.putExtra(Constant.DATA_INTENT,addressObj);
                setResult(1254,intent);
                finish();
        }


    }
}