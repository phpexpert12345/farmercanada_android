package com.farmers.buyers.modules.seller.sellerProfile;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleRowItem;
import com.farmers.buyers.common.model.SimpleRowListItem;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.model.MyProfileHeaderItems;
import com.farmers.buyers.modules.profile.model.MyProfileOptionItem;
import com.farmers.buyers.modules.profile.model.MyProfileOptionMenuItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:15
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileTransformer {

    public static MyProfileHeaderItems getProfileHeader() {
        return new MyProfileHeaderItems("", "", "", "", "", "", "");
    }

    public static MyProfileOptionItem getProfileMenuItems() {
        List<RecyclerViewListItem> itemList = new ArrayList<>();
        itemList.add(new MyProfileOptionMenuItems("My Products", R.drawable.ic_orders, R.drawable.round_corner_ligh_green_bg, ProfileOptionsGridItem.ORDERS));
        itemList.add(new MyProfileOptionMenuItems("My Orders", R.drawable.ic_menu_item_dark_red_location, R.drawable.round_corner_ligh_red_bg, ProfileOptionsGridItem.ADDRESS));
        itemList.add(new MyProfileOptionMenuItems("My Earnings", R.drawable.ic_rating_review, R.drawable.round_corner_ligh_red_bg, ProfileOptionsGridItem.RATING_REVIEW));
        itemList.add(new MyProfileOptionMenuItems("Shop Setting", R.drawable.ic_payments, R.drawable.round_corner_ligh_green_bg, ProfileOptionsGridItem.PAYMENTS));
        return new MyProfileOptionItem(itemList);
    }

    public static SimpleRowListItem getAccountSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Edit Profile", R.drawable.edit_profile_icon, 0, ProfileItem.EDIT_PROFILE));
        accountSettingItem.add(new SimpleRowItem("Change Password", R.drawable.ic_key, 0, ProfileItem.CHANGE_PASSWORD));
        accountSettingItem.add(new SimpleRowItem("Notifications", R.drawable.ic_notification_red, R.drawable.ic_forward, ProfileItem.NOTIFICATION));
        return new SimpleRowListItem(accountSettingItem);
    }

    public static SimpleRowListItem getReferralSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Support", R.drawable.support_icon, 0, ProfileItem.SUPPORT));
        accountSettingItem.add(new SimpleRowItem("Logout", R.drawable.ic_log_out, 0, ProfileItem.LOGOUT));
        return new SimpleRowListItem(accountSettingItem);
    }


}