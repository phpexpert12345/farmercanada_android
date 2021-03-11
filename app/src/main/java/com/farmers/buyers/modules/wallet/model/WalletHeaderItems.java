package com.farmers.buyers.modules.wallet.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:21
 * mohammadsajjad679@gmail.com
 */

public class WalletHeaderItems implements RecyclerViewListItem {
public String wallet_amount;
    @Override
    public int getViewType() {
        return CardConstant.WALLET_HEADER_ITEM_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
