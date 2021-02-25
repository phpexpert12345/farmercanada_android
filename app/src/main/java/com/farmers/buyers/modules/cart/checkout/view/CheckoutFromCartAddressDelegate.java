package com.farmers.buyers.modules.cart.checkout.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:12
 * mohammadsajjad679@gmail.com
 */

public class CheckoutFromCartAddressDelegate extends BaseDelegate {

    CheckOutFromCartAddressViewHolder.ChangeAddressCallback cartAddress;

    public CheckoutFromCartAddressDelegate(CheckOutFromCartAddressViewHolder.ChangeAddressCallback listeners) {
        this.cartAddress = listeners;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CheckOutFromCartAddressViewHolder(parent,cartAddress);
    }
}
