package com.farmers.buyers.modules.cart.checkout.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:59
 * mohammadsajjad679@gmail.com
 */

public class PaymentMethodsItems implements RecyclerViewListItem {
    @Override
    public int getViewType() {
        return CardConstant.MY_CART_PAYMENT_METHODS;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
