package com.farmers.buyers.modules.seller.sellerProfile;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.changePassword.ChangePasswordActivity;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.inbox.NotificationsActivity;
import com.farmers.buyers.modules.login.LoginActivity;
import com.farmers.buyers.modules.orders.subOrderList.SubOrderListActivity;
import com.farmers.buyers.modules.profile.EditProfileActivity;
import com.farmers.buyers.modules.profile.MyProfileTransformer;
import com.farmers.buyers.modules.profile.adapter.MyProfileAdapter;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;
import com.farmers.buyers.modules.profile.view.MyProfileOptionItemViewHolder;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewActivity;
import com.farmers.buyers.modules.referFriends.ReferFriendsActivity;
import com.farmers.buyers.modules.seller.sellerProfile.adapter.SellerProfileAdapter;
import com.farmers.buyers.modules.seller.sellerProfile.view.SellerProfileHeaderViewHolder;
import com.farmers.buyers.modules.support.list.SupportActivity;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:01
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileFragment extends BaseFragment implements SellerProfileHeaderViewHolder.SellerProfileItemClickListener, SimpleRowViewHolder.OnSimpleRowItemClickedListener, MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener {

    private SellerProfileAdapter adapter;
    private RecyclerView recyclerView;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Override
    public String getTitle() {
        return "My Profile";
    }

    @Override
    public int getResourceFile() {
        return R.layout.seller_profile_fragment;
    }

    @Override
    public void onViewCreated() {
        prepareItems();
    }

    @Override
    public void bindView(View view) {
        recyclerView = view.findViewById(R.id.seller_profile_recyclerView);
        adapter = new SellerProfileAdapter(this, this, this);
        recyclerView.addItemDecoration(new LinearSpacesItemDecoration(20));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));

    }

    private void prepareItems() {
        items.add(SellerProfileTransformer.getProfileHeader());
        items.add(SellerProfileTransformer.getProfileMenuItems());
        items.add(new SimpleTitleItem("Account Setting"));
        items.add(SellerProfileTransformer.getAccountSetting());
        items.add(new SimpleTitleItem("Referral & Credits"));
        items.add(SellerProfileTransformer.getReferralSetting());
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

            case LOGOUT: {
                SharedPreferenceManager.getInstance().clearUserInfo();
                startActivity(new Intent(baseActivity, LoginActivity.class) );
                baseActivity.finish();
                break;
            }

        }
    }

    @Override
    public void onGridMenuClicked(ProfileOptionsGridItem item) {
        switch (item){
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