package com.farmers.buyers.modules.cart.checkout.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:13
 * mohammadsajjad679@gmail.com
 */

public class CheckOutFromCartAddressViewHolder extends BaseViewHolder {

    public CheckOutFromCartAddressViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.check_out_from_cart_address_holder_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
