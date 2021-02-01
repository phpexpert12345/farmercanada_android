package com.farmers.buyers.modules.profile;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.view.SimpleRowViewHolder;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.FollowersActivity;
import com.farmers.buyers.modules.profile.adapter.MyProfileAdapter;
import com.farmers.buyers.modules.profile.model.MyProfileHeaderItems;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:34
 * mohammadsajjad679@gmail.com
 */

public class MyProfileFragment extends BaseFragment implements MyProfileHeaderViewHolder.MyProfileItemClickListener, SimpleRowViewHolder.OnSimpleRowItemClickedListener {

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
        adapter = new MyProfileAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));


    }

    private void prepareItems() {
        items.add(MyProfileTransformer.getProfileHeader());
        items.add(MyProfileTransformer.getProfileMenuItems());
        items.add(new SimpleTitleItem("Account Setting"));
        items.add(MyProfileTransformer.getAccountSetting());
        items.add(new SimpleTitleItem("Become a Vendor"));
        items.add(MyProfileTransformer.getRoleSetting());
        items.add(new SimpleTitleItem("Referral & Credits"));
        items.add(MyProfileTransformer.getReferralSetting());
        adapter.updateData(items);

    }


    @Override
    public void onFollowersItemClicked() {
        startActivity(new Intent(baseActivity, FollowersActivity.class));
    }

    @Override
    public void onWalletClicked() {

    }

    @Override
    public void onInboxClicked() {

    }

    @Override
    public void onSimpleRowItemClicked(ProfileItem item) {
        switch (item) {
            case EDIT_PROFILE: {
                startActivity(new Intent(baseActivity, EditProfileActivity.class));
                break;
            }
        }
    }
}
