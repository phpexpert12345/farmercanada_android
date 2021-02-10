package com.farmers.buyers.modules.seller.sellerProfile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.changePassword.sellerChangePassword.SellerChangePasswordActivity;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.inbox.NotificationsActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;
import com.farmers.buyers.modules.profile.EditProfileActivity;
import com.farmers.buyers.modules.profile.MyProfileTransformer;
import com.farmers.buyers.modules.profile.NotificationBottomSheetDialogFragment;
import com.farmers.buyers.modules.profile.adapter.MyProfileAdapter;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.buyers.modules.seller.sellerProfile.adapter.SellerProfileAdapter;
import com.farmers.buyers.modules.seller.sellerProfile.editProfile.SellerShopSetting;
import com.farmers.buyers.modules.seller.sellerProfile.view.SellerProfileHeaderViewHolder;
import com.farmers.buyers.modules.support.list.SupportActivity;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;
import com.farmers.seller.modules.editProfile.SellerEditProfileActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:01
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileActivity extends BaseActivity implements SellerProfileHeaderViewHolder.SellerProfileItemClickListener,
        SimpleRowViewHolder.OnSimpleRowItemClickedListener, MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener {

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
                startActivity(new Intent(this, ReferFriendsActivity.class));
                break;
            }

            case SUPPORT: {
                startActivity(new Intent(this, SupportActivity.class));
                break;
            }

            case NOTIFICATION: {
                NotificationBottomSheetDialogFragment notifyme = new NotificationBottomSheetDialogFragment();
                notifyme.show(getSupportFragmentManager(), notifyme.getTag());
                break;
            }

            case LOGOUT: {
                SharedPreferenceManager.getInstance().clearUserInfo();
                startActivity(new Intent(this, LoginActivity.class));
                this.finish();
                break;
            }

        }
    }

    @Override
    public void onGridMenuClicked(ProfileOptionsGridItem item) {
        switch (item) {
            case MyProduct: {
                this.startActivity(new Intent(this, ProductListActivity.class));
                break;
            }
            case ShopSetting: {
                startActivity(new Intent(this, SellerShopSetting.class));
                break;
            }
//
//            case RATING_REVIEW: {
//                this.startActivity(new Intent(this, RatingAndReviewActivity.class));
//                break;
//            }
//
//            case ADDRESS: {
//                this.startActivity(new Intent(this, MyAddressActivity.class));
//                break;
//            }

        }
    }
}
