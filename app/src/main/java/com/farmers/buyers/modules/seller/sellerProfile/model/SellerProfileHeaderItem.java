package com.farmers.buyers.modules.seller.sellerProfile.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:12
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileHeaderItem implements RecyclerViewListItem {
    String userName;
    String role;
    String email;
    String walletMoney;
    String followers;
    String inboxCount;
    String image;

    public SellerProfileHeaderItem(String userName, String role, String email, String walletMoney, String followers, String inboxCount, String image) {
        this.userName = userName;
        this.role = role;
        this.email = email;
        this.walletMoney = walletMoney;
        this.followers = followers;
        this.inboxCount = inboxCount;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getWalletMoney() {
        return walletMoney;
    }

    public String getFollowers() {
        return followers;
    }

    public String getInboxCount() {
        return inboxCount;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int getViewType() {
        return CardConstant.SELLER_PROFILE_HEADER_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
