package com.farmers.buyers.modules.wallet.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class WalletHistoryDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new WalletHistoryViewHolder(parent);
    }
}
