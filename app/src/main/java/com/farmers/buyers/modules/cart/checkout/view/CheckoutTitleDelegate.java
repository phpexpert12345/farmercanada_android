package com.farmers.buyers.modules.cart.checkout.view;

import android.view.ViewGroup;

import com.farmers.buyers.common.view.SimpleTitleViewHolder;
import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class CheckoutTitleDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CheckoutCartTitleViewHolder(parent);

    }
}
