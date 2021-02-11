package com.farmers.buyers.modules.profile;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.app.AppControllerContract;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.model.SimpleDividerItem;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.inbox.NotificationsActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;
import com.farmers.buyers.modules.profile.adapter.MyProfileAdapter;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.farmers.buyers.modules.seller.addProduct.AddProductActivity;
import com.farmers.buyers.modules.seller.coupon.addCoupon.AddNewCouponActivity;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponActivity;
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

    }

    private void prepareItems() {
        items.add(MyProfileTransformer.getProfileHeader());
        items.add(MyProfileTransformer.getProfileMenuItems());
        items.add(new SimpleTitleItem("Account Setting", R.color.light_gray));
        items.add(MyProfileTransformer.getAccountSetting());
        items.add(new SimpleTitleItem("Become a Vendor", R.color.light_gray));
        items.add(MyProfileTransformer.getRoleSetting());
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
                startActivity(new Intent(baseActivity, ManageCouponActivity.class));
                break;
            }

            case LEARN_ABOUT_STORE: {
                startActivity(new Intent(baseActivity, AddNewCouponActivity.class));
                break;
            }

            case NOTIFICATION: {
                NotificationBottomSheetDialogFragment notifyme = new NotificationBottomSheetDialogFragment();
                notifyme.show(baseActivity.getSupportFragmentManager(), notifyme.getTag());
                break;
            }

            case LOGOUT: {
                SharedPreferenceManager.getInstance().clearUserInfo();
                startActivity(new Intent(baseActivity, LoginActivity.class));
                baseActivity.finish();
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
                baseActivity.startActivity(new Intent(baseActivity, RatingAndReviewActivity.class));
                break;
            }

            case ADDRESS: {
                baseActivity.startActivity(new Intent(baseActivity, MyAddressActivity.class));
                break;
            }

        }
    }
}
