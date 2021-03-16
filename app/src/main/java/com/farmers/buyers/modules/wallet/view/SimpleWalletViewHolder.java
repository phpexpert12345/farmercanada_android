package com.farmers.buyers.modules.wallet.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class SimpleWalletViewHolder extends BaseViewHolder {
    public SimpleWalletViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.wallet_title_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
