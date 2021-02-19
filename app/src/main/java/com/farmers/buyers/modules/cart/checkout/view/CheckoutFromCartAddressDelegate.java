package com.farmers.buyers.modules.cart.checkout.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:12
 * mohammadsajjad679@gmail.com
 */

public class CheckoutFromCartAddressDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CheckOutFromCartAddressViewHolder(parent);
    }
}
