package com.farmers.buyers.modules.wallet.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:12
 * mohammadsajjad679@gmail.com
 */

public class WalletHeaderDelegate extends BaseDelegate {
    WalletHeaderViewHolder.WalletHeaderClickListener walletHeaderClickListener;

    public WalletHeaderDelegate(WalletHeaderViewHolder.WalletHeaderClickListener walletHeaderClickListener) {
        this.walletHeaderClickListener = walletHeaderClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new WalletHeaderViewHolder(parent, walletHeaderClickListener);
    }
}
