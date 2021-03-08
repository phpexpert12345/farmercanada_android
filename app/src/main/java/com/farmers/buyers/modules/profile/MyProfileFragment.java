package com.farmers.buyers.modules.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.aboutStore.AboutStoreActivity;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.home.homeFragment.HomeFragmentViewModel;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.inbox.NotificationsActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;
import com.farmers.buyers.modules.profile.adapter.MyProfileAdapter;
import com.farmers.buyers.modules.profile.editProfile.EditProfileActivity;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.profile.view.ProfileFragmentViewModel;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.modules.ratingAndReview.customerReview.CustomerReviewListActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.farmers.buyers.modules.signUp.OtpActivity;
import com.farmers.buyers.modules.support.list.SupportActivity;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:34
 * mohammadsajjad679@gmail.com
 */

public class MyProfileFragment extends BaseFragment implements MyProfileHeaderViewHolder.MyProfileItemClickListener,
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
    private MyProfileAdapter adapter;
    private RecyclerView recyclerView;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    public String getTitle() {
        return "My Profile";
    }

    @Override
    public int getResourceFile() {
        return R.layout.my_profile_fragment;
    }

    @Override
    public void onViewCreated() {
        prepareItems();
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.my_profile_recyclerView);
        adapter = new MyProfileAdapter(this, this, this);

        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
        init();
    }

    private void init() {
        userTypeStateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
                    Toast.makeText(getContext(), dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(getContext(), dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    SharedPreferenceManager.getInstance().clearUserInfo();
                    startActivity(new Intent(baseActivity, LoginActivity.class));
                    baseActivity.finish();
                    break;
                }
            }
        });
    }

    private void prepareItems() {
        items.add(MyProfileTransformer.getProfileHeader());
        items.add(MyProfileTransformer.getProfileMenuItems());
        items.add(new SimpleTitleItem("Account Setting", R.color.light_gray));
        items.add(MyProfileTransformer.getAccountSetting());
//        items.add(new SimpleTitleItem("Become a Vendor", R.color.light_gray));
//        items.add(MyProfileTransformer.getRoleSetting());
        items.add(new SimpleTitleItem("Referral & Credits", R.color.light_gray));
        items.add(MyProfileTransformer.getReferralSetting());
        adapter.updateData(items);

    }

    @Override
    public void onFollowersItemClicked() {
        startActivity(new Intent(baseActivity, FollowersActivity.class));
    }

    @Override
    public void onWalletClicked() {
        startActivity(new Intent(baseActivity, WalletActivity.class));
    }

    @Override
    public void onUserChangeClicked() {
        buyer_seller_switch_dialog(getContext());
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

    @Override
    public void onInboxClicked() {
        startActivity(new Intent(baseActivity, NotificationsActivity.class));
    }

    @Override
    public void onSimpleRowItemClicked(ProfileItem item) {
        switch (item) {
            case EDIT_PROFILE: {
                startActivity(new Intent(baseActivity, EditProfileActivity.class));
                break;
            }
            case CHANGE_PASSWORD: {
                startActivity(new Intent(baseActivity, ChangePasswordActivity.class));
                break;
            }

            case EARN_MONEY: {
                startActivity(new Intent(baseActivity, ReferFriendsActivity.class));
                break;
            }

            case SUPPORT: {
                startActivity(new Intent(baseActivity, SupportActivity.class));
                break;
            }
            case OPEN_STORE: {
//                startActivity(new Intent(baseActivity, ManageCouponActivity.class));
                break;
            }

            case LEARN_ABOUT_STORE: {
                startActivity(new Intent(baseActivity, AboutStoreActivity.class));
                break;
            }

            case NOTIFICATION: {
                NotificationBottomSheetDialogFragment notifyme = new NotificationBottomSheetDialogFragment();
                notifyme.show(baseActivity.getSupportFragmentManager(), notifyme.getTag());
                break;
            }

            case LOGOUT: {
                showLogoutAlert();
                break;
            }

        }
    }

    @Override
    public void onGridMenuClicked(ProfileOptionsGridItem item) {
        switch (item) {
            case ORDERS: {
                baseActivity.startActivity(new Intent(baseActivity, SubOrderListActivity.class));
                break;
            }

            case RATING_REVIEW: {
                baseActivity.startActivity(new Intent(baseActivity, CustomerReviewListActivity.class));
                break;
            }

            case ADDRESS: {
                Intent intentAddress = new Intent(baseActivity, MyAddressActivity.class);
                intentAddress.putExtra("ComeFrom", 1);
                startActivity(intentAddress);
                break;
            }
            case PAYMENTS: {
                Toast.makeText(baseActivity, "In-progress", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void showLogoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Farmer Alert");
        builder.setMessage("Are you sure! You want to logout ?");
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            SharedPreferenceManager.getInstance().clearUserInfo();
            startActivity(new Intent(baseActivity, LoginActivity.class));
            baseActivity.finish();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.show();
    }

}