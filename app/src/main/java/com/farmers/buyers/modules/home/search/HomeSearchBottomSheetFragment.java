package com.farmers.buyers.modules.home.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.common.utils.DroidPrefs;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.widget.ProgressDialog;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.cart.myCart.model.cartList.CartListResponse;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseApiModel;
import com.farmers.buyers.modules.cart.myCart.model.increaseDecrease.IncreaseDecreaseParams;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.farmDetail.model.FarmDeliveryStatus;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetableItems;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailsVegetableItemsViewHolder;
import com.farmers.buyers.modules.followers.model.FollowUnFollowApiModel;
import com.farmers.buyers.modules.home.HomeActivity;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.home.homeFragment.HomeFragment;
import com.farmers.buyers.modules.home.search.model.HomeSearchApiModel;
import com.farmers.buyers.modules.home.search.model.HomeSearchCategoryList;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Mohammad sajjad on 01-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class HomeSearchBottomSheetFragment extends BaseFragment implements FarmDetailsVegetableItemsViewHolder.FarmDetailVegetableListener {
   private RecyclerView recyclerView;
   private EditText searchEt;
   private TextView searchTv;
   private ImageView backImage;
   private OnBackPressedClickListeners onBackPressedClickListeners;
    private HomeSearchAdapter adapter;
    FarmDetailsVegetableItems veggie;
    int quat;
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(HomeSearchViewModel.class)) {
                return (T) new HomeSearchViewModel();
            }
            return null;
        }};
    private HomeSearchViewModel viewModel = factory.create(HomeSearchViewModel.class);
    private MutableLiveData<DataFetchState<HomeSearchApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FarmListProductResponse>> addToCartStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> increaseDecreaseMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<IncreaseDecreaseApiModel>> clearAllCartItemsMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<CartListResponse>> cartListMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<FollowUnFollowApiModel>> followUnFollowStateMachine = new MutableLiveData<>();

    private AppController appController = AppController.get();
    public String farm_id;
    int farm_delivery_radius;
    double farm_lat,farm_long;
    private void callClearCartDialog(String msg) {
        new AlertDialog.Builder(getContext())
                .setTitle("Farmer Alert")
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(),
                            appController.getLoginId());
                    viewModel.clearAllCartItems(clearAllCartItemsMachine, params);
                    dialog.dismiss();
                })
                .setIcon(getResources().getDrawable(R.drawable.logo))
                .show();
    }



    public HomeSearchBottomSheetFragment(OnBackPressedClickListeners onBackPressedClickListeners) {
       this.onBackPressedClickListeners = onBackPressedClickListeners;
   }


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getResourceFile() {
        return R.layout.home_search_bottom_sheet_dialog_layout;
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.home_search_recyclerView);
        searchEt = view.findViewById(R.id.home_search_bottom_sheet_search_et);
//        searchTv = view.findViewById(R.id.home_search_error_tv);
        backImage = view.findViewById(R.id.home_search_back_img);
        adapter = new HomeSearchAdapter(this);

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemAt(position) instanceof FarmDetailsVegetableItems){
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(20));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        stateMachine.observe(this, new Observer<DataFetchState<HomeSearchApiModel>>() {
            @Override
            public void onChanged(DataFetchState<HomeSearchApiModel> response) {
                switch (response.status) {
                    case LOADING: loading(); break;
                    case SUCCESS: success(); break;
                    case ERROR: error(response.status_message);
                }
            }
        });

        addToCartStateMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    dismissLoader();
                    viewModel.doSearch(stateMachine, searchEt.getText().toString());
                    Toast.makeText(getContext(), response.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    dismissLoader();
                    callClearCartDialog(response.status_message);
//                    viewModel.doSearch(stateMachine, searchEt.getText().toString());
                    break;
            }
        });


        increaseDecreaseMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    viewModel.doSearch(stateMachine, searchEt.getText().toString());
                    Toast.makeText(baseActivity, response.status_message, Toast.LENGTH_SHORT).show();
                    dismissLoader();
                    break;
                case LOADING:
                    showLoader();
                    break;
                case ERROR:
                    dismissLoader();
                    break;
            }
        });
        clearAllCartItemsMachine.observe(this, response -> {
            switch (response.status) {
                case SUCCESS:
                    Toast.makeText(getContext(), response.status_message, Toast.LENGTH_SHORT).show();

                    AddtoCartItems(veggie,quat);
//                    getFarmProductDetail();
                case LOADING:

                    showLoader();
                case ERROR:
                    dismissLoader();
                    break;
            }
        });



        backImage.setOnClickListener(v -> ((HomeActivity)getActivity()).loadFragment(new HomeFragment()));

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    viewModel.doSearch(stateMachine, s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        bindAdapter();
    }

    private void error(String error) {
        dismissLoader();
        bindAdapter();
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }

    @Override
    public void onClickFarmDetailVegetableListener(FarmDetailsVegetableItems item, int cnt) {
        veggie=item;
        quat=cnt;
        AddtoCartItems(item,cnt);
    }
    private void AddtoCartItems(FarmDetailsVegetableItems item,int cnt){
        FarmProductListReq farmProductListReq = new FarmProductListReq(appController.getAuthenticationKey(),
                item.getFarmId(),
                appController.getLoginId(),
                item.productId,
                String.valueOf(cnt),
                item.getPrice(),
                "1",
                item.price_unit_type,
                String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")));
        viewModel.addToCartItems(addToCartStateMachine, farmProductListReq);
    }

    @Override
    public void onClickIncreaseCartListener(FarmDetailsVegetableItems item, int cnt) {
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(
                appController.getAuthenticationKey(),
                item.cart_id,
                "0");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }

    @Override
    public void onClickDecreaseCartListener(FarmDetailsVegetableItems item, int cnt) {
        IncreaseDecreaseParams params = new IncreaseDecreaseParams(appController.getAuthenticationKey(),
                item.cart_id, "1");
        viewModel.increaseDecrease(increaseDecreaseMachine, params);
    }


    public interface OnBackPressedClickListeners{
        void onPressedBackButton();
    }
}