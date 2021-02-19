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
        itemList.add(new MyProfileOptionMenuItems("My Products", R.drawable.ic_my_products, R.drawable.round_corner_ligh_red_bg, ProfileOptionsGridItem.MyProduct));
        itemList.add(new MyProfileOptionMenuItems("My Orders", R.drawable.ic_my_orders, R.drawable.round_corner_ligh_green_bg, ProfileOptionsGridItem.MyOrder));
        itemList.add(new MyProfileOptionMenuItems("My Earnings", R.drawable.ic_my_earnings, R.drawable.round_corner_ligh_green_bg, ProfileOptionsGridItem.MyEarning));
        itemList.add(new MyProfileOptionMenuItems("Shop Setting", R.drawable.ic_shop_setting, R.drawable.round_corner_ligh_red_bg, ProfileOptionsGridItem.ShopSetting));
        return new MyProfileOptionItem(itemList);
    }

    public static SimpleRowListItem getAccountSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Edit Profile", R.drawable.ic_edit_profile_green, 0, ProfileItem.EDIT_PROFILE));
        accountSettingItem.add(new SimpleRowItem("Change Password", R.drawable.ic_key_green, 0, ProfileItem.CHANGE_PASSWORD));
        accountSettingItem.add(new SimpleRowItem("Notifications", R.drawable.ic_notification_green, R.drawable.ic_forward, ProfileItem.NOTIFICATION));
        return new SimpleRowListItem(accountSettingItem);
    }

    public static SimpleRowListItem getReferralSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Support", R.drawable.ic_support_green, 0, ProfileItem.SUPPORT));
        accountSettingItem.add(new SimpleRowItem("Logout", R.drawable.ic_logout_green, 0, ProfileItem.LOGOUT));
        return new SimpleRowListItem(accountSettingItem);
    }


}