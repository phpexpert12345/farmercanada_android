package com.farmers.buyers.modules.seller.sellerProfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.changePassword.sellerChangePassword.SellerChangePasswordActivity;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.inbox.NotificationsActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.profile.NotificationBottomSheetDialogFragment;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.profile.view.ProfileFragmentViewModel;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.seller.sellerProfile.adapter.SellerProfileAdapter;
import com.farmers.buyers.modules.seller.sellerProfile.editProfile.SellerShopSetting;
import com.farmers.buyers.modules.seller.sellerProfile.view.SellerProfileHeaderViewHolder;
import com.farmers.buyers.modules.support.list.SupportActivity;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.editProfile.SellerEditProfileActivity;
import com.farmers.seller.modules.inbox.SellerNotificationsActivity;
import com.farmers.seller.modules.ourOrders.OurOrdersActivity;
import com.farmers.seller.modules.referFriends.SellerReferFriendsActivity;
import com.farmers.seller.modules.storeSetting.StoreSettingActivity;

import java.util.ArrayList;
import java.util.List;

import static com.farmers.buyers.app.App.getAppContext;
import static java.security.AccessController.getContext;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:01
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileActivity extends BaseActivity implements SellerProfileHeaderViewHolder.SellerProfileItemClickListener,
        SimpleRowViewHolder.OnSimpleRowItemClickedListener, MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ProfileFragmentViewModel.class)) {
                return (T) new ProfileFragmentViewModel();
            }
            return null;
        }
    };
    public ProfileFragmentViewModel viewModel = factory.create(ProfileFragmentViewModel.class);
    private MutableLiveData<DataFetchState<AllDataModel>> userTypeStateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();
    private SellerProfileAdapter adapter;
    private RecyclerView recyclerView;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_profile_fragment);
        prepareItems();
        init();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    public void init() {
        recyclerView = findViewById(R.id.seller_profile_recyclerView);
        adapter = new SellerProfileAdapter(this, this, this);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.updateData(items);

        userTypeStateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(SellerProfileActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(SellerProfileActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    SharedPreferenceManager.getInstance().clearUserInfo();
                    startActivity(new Intent(SellerProfileActivity.this, LoginActivity.class));
                    finish();
                    break;
                }
            }
        });
    }

    private void prepareItems() {
        items.add(SellerProfileTransformer.getProfileHeader());
        items.add(SellerProfileTransformer.getProfileMenuItems());
        items.add(new SimpleTitleItem("Account Setting", R.color.light_gray));
        items.add(SellerProfileTransformer.getAccountSetting());
        items.add(new SimpleTitleItem("Referral & Credits", R.color.light_gray));
        items.add(SellerProfileTransformer.getReferralSetting());

    }

    @Override
    public void onFollowersItemClicked() {
        startActivity(new Intent(this, FollowersActivity.class));
    }

    @Override
    public void onWalletClicked() {
        startActivity(new Intent(this, WalletActivity.class));
    }

    @Override
    public void onInboxClicked() {
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    @Override
    public void onUserChangeClicked() {
        buyer_seller_switch_dialog(SellerProfileActivity.this);
    }

    @Override
    public void onSimpleRowItemClicked(ProfileItem item) {
        switch (item) {
            case EDIT_PROFILE: {
                startActivity(new Intent(this, SellerEditProfileActivity.class));
                break;
            }

            case CHANGE_PASSWORD: {
                startActivity(new Intent(this, SellerChangePasswordActivity.class));
                break;
            }

            case EARN_MONEY: {
                startActivity(new Intent(this, SellerReferFriendsActivity.class));
                break;
            }

            case SUPPORT: {
                startActivity(new Intent(this, SupportActivity.class));
                break;
            }

            case NOTIFICATION: {
                startActivity(new Intent(SellerProfileActivity.this, SellerNotificationsActivity.class));
               /* NotificationBottomSheetDialogFragment notifyme = new NotificationBottomSheetDialogFragment();
                notifyme.show(getSupportFragmentManager(), notifyme.getTag());*/
                break;
            }

            case LOGOUT: {
                showLogoutAlert();
                break;
            }
        }
    }

    private void showLogoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Farmer Alert");
        builder.setMessage("Are you sure! You want to logout ?");
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            SharedPreferenceManager.getInstance().clearUserInfo();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.show();
    }

    @Override
    public void onGridMenuClicked(ProfileOptionsGridItem item) {
        switch (item) {
            case MyProduct: {
                this.startActivity(new Intent(this, ProductListActivity.class));
                break;
            }
            case MyOrder: {
                startActivity(new Intent(this, OurOrdersActivity.class));
                break;
            }
            case ShopSetting: {
                //startActivity(new Intent(this, SellerShopSetting.class));
                startActivity(new Intent(this, StoreSettingActivity.class));
                break;
            }
            case MyEarning: {
                startActivity(new Intent(this, SellerReferFriendsActivity.class));
                break;
            }
        }
    }

    public void buyer_seller_switch_dialog(Context activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.buyer_seller_switch_dialog);
        RadioGroup radioGroup = dialog.findViewById(R.id.user_type_radio_group);
        TextView tv_user_type = dialog.findViewById(R.id.tv_user_type);
        RadioButton radio_seller, radio_buyer;
        radio_seller = dialog.findViewById(R.id.radio_seller);
        radio_buyer = dialog.findViewById(R.id.radio_buyer);
        tv_user_type.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_TYPE", "")));

        if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_TYPE", "")).
                equalsIgnoreCase("Seller")) {
            radio_seller.setChecked(true);
        } else {
            radio_buyer.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (radioGroup1.getCheckedRadioButtonId()) {
                case R.id.radio_seller: {//Buyer = 1 & Seller = 2
                    //  Toast.makeText(getContext(), "Seller", Toast.LENGTH_SHORT).show();
                    ProfileRequestParams profileRequestParams = new ProfileRequestParams("2",
                            appController.getLoginId(), appController.getAuthenticationKey());
                    viewModel.changeUserType(userTypeStateMachine, profileRequestParams);

                    dialog.dismiss();
                    break;
                }
                case R.id.radio_buyer: {
                    //   Toast.makeText(getContext(), "Buyer", Toast.LENGTH_SHORT).show();
                    ProfileRequestParams profileRequestParams = new ProfileRequestParams("1",
                            appController.getLoginId(), appController.getAuthenticationKey());
                    viewModel.changeUserType(userTypeStateMachine, profileRequestParams);
                    dialog.dismiss();
                    break;
                }
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}