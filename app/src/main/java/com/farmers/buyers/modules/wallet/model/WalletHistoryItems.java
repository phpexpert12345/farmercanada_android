package com.farmers.buyers.modules.wallet.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:02
 * mohammadsajjad679@gmail.com
 */

public class WalletHistoryItems implements RecyclerViewListItem {
    String shopName;
    String status;
    String amount;
    String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String  date;
    int icon;

    public WalletHistoryItems(String shopName, String status, String amount, String time,String  date, int icon) {
        this.shopName = shopName;
        this.status = status;
        this.amount = amount;
        this.time = time;
        this.icon = icon;
        this.date=date;
    }

    public String getShopName() {
        return shopName;
    }

    public String getStatus() {
        return status;
    }

    public String getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public int getViewType() {
        return CardConstant.WALLET_HISTORY_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
