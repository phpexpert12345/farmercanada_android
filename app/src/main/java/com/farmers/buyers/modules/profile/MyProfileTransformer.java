package com.farmers.buyers.modules.profile;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleRowItem;
import com.farmers.buyers.common.model.SimpleRowListItem;
import com.farmers.buyers.core.RecyclerViewListItem;
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
        itemList.add(new MyProfileOptionMenuItems("Order",""));
        itemList.add(new MyProfileOptionMenuItems("Address",""));
        itemList.add(new MyProfileOptionMenuItems("Rating & Review",""));
        itemList.add(new MyProfileOptionMenuItems("Payment",""));
        return new MyProfileOptionItem(itemList);
    }

    public static SimpleRowListItem getAccountSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Edit Profile", R.drawable.ic_user_light_red_bg, 0, ProfileItem.EDIT_PROFILE));
        accountSettingItem.add(new SimpleRowItem("Change Password", R.drawable.ic_user_light_red_bg, 0, ProfileItem.CHANGE_PASSWORD));
        accountSettingItem.add(new SimpleRowItem("Notifications", R.drawable.ic_user_light_red_bg, 0, ProfileItem.NOTIFICATION));
        return new SimpleRowListItem(accountSettingItem);
    }

    public static SimpleRowListItem getRoleSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Open Store", R.drawable.ic_user_light_red_bg, 0, ProfileItem.OPEN_STORE));
        accountSettingItem.add(new SimpleRowItem("Learn About Store", R.drawable.ic_user_light_red_bg, 0, ProfileItem.LEARN_ABOUT_STORE));
        return new SimpleRowListItem(accountSettingItem);
    }

  public static SimpleRowListItem getReferralSetting() {
        List<SimpleRowItem> accountSettingItem = new ArrayList<>();
        accountSettingItem.add(new SimpleRowItem("Earn Money", R.drawable.ic_user_light_red_bg, 0, ProfileItem.EARN_MONEY));
        accountSettingItem.add(new SimpleRowItem("Support", R.drawable.ic_user_light_red_bg, 0, ProfileItem.SUPPORT));
        accountSettingItem.add(new SimpleRowItem("Logout", R.drawable.ic_user_light_red_bg, 0, ProfileItem.LOGOUT));
        return new SimpleRowListItem(accountSettingItem);
    }


}
