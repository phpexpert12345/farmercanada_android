package com.farmers.buyers.modules.profile;

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
 * on 31-01-2021 at 04:10
 * mohammadsajjad679@gmail.com
 */

public class MyProfileTransformer {

    public static MyProfileHeaderItems getProfileHeader() {
        return new MyProfileHeaderItems("","","","","","","");
    }

    public static MyProfileOptionItem getProfileMenuItems() {
        List<RecyclerViewListItem> itemList = new ArrayList<>();
        itemList.add(new MyProfileOptionMenuItems("Order",R.drawable.ic_orders, R.color.light_green, ProfileOptionsGridItem.ORDERS));
        itemList.add(new MyProfileOptionMenuItems("Address",R.drawable.ic_menu_item_dark_red_location, R.color.middle_red,ProfileOptionsGridItem.ADDRESS));
        itemList.add(new MyProfileOptionMenuItems("Rating & Review",R.drawable.ic_rating_review, R.color.middle_red, ProfileOptionsGridItem.RATING_REVIEW));
        itemList.add(new MyProfileOptionMenuItems("Payment",R.drawable.ic_payments, R.color.light_green, ProfileOptionsGridItem.PAYMENTS));
        return new MyProfileOptionItem(itemList);
    }

    public static SimpleRowListItem getAccountSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Edit Profile", R.drawable.ic_edit_profile, 0, ProfileItem.EDIT_PROFILE));
        accountSettingItem.add(new SimpleRowItem("Change Password", R.drawable.ic_key, 0, ProfileItem.CHANGE_PASSWORD));
        accountSettingItem.add(new SimpleRowItem("Notifications", R.drawable.ic_notification_red, 0, ProfileItem.NOTIFICATION));
        return new SimpleRowListItem(accountSettingItem);
    }

    public static SimpleRowListItem getRoleSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Open Store", R.drawable.ic_open_store, 0, ProfileItem.OPEN_STORE));
        accountSettingItem.add(new SimpleRowItem("Learn About Store", R.drawable.ic_about_store, 0, ProfileItem.LEARN_ABOUT_STORE));
        return new SimpleRowListItem(accountSettingItem);
    }

  public static SimpleRowListItem getReferralSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Earn Money", R.drawable.ic_earn_money, 0, ProfileItem.EARN_MONEY));
        accountSettingItem.add(new SimpleRowItem("Support", R.drawable.ic_chat, 0, ProfileItem.SUPPORT));
        accountSettingItem.add(new SimpleRowItem("Logout", R.drawable.ic_log_out, 0, ProfileItem.LOGOUT));
        return new SimpleRowListItem(accountSettingItem);
    }


}
