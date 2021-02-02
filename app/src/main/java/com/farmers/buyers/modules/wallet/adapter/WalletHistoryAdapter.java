package com.farmers.buyers.modules.wallet.adapter;


import com.farmers.buyers.common.view.SimpleTitleDelegate;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.wallet.view.WalletHistoryDelegate;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:25
 * mohammadsajjad679@gmail.com
 */

public class WalletHistoryAdapter extends BaseAdapter {

    public WalletHistoryAdapter() {
        super();
        initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SIMPLE_TITLE_ITEM_ADAPTER, new SimpleTitleDelegate());
        delegates.put(CardConstant.WALLET_HISTORY_ADAPTER, new WalletHistoryDelegate());
    }
}
