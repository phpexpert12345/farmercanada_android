package com.farmers.buyers.modules.cart.myCart.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:30
 * mohammadsajjad679@gmail.com
 */

public class MyCartCheckOutDelegate extends BaseDelegate {
    private MyCartCheckoutViewHolder.MyCartCheckOutClickListeners cartCheckOutClickListeners;

    public MyCartCheckOutDelegate(MyCartCheckoutViewHolder.MyCartCheckOutClickListeners listeners) {
        this.cartCheckOutClickListeners = listeners;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyCartCheckoutViewHolder(parent, cartCheckOutClickListeners);
    }
}
