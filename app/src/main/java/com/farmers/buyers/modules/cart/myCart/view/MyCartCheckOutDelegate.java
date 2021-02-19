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

    private MyCartCheckoutViewHolder.MyCoupounClickListeners couponClicked;

    public MyCartCheckOutDelegate(MyCartCheckoutViewHolder.MyCartCheckOutClickListeners listeners, MyCartCheckoutViewHolder.MyCoupounClickListeners couponClicked1) {
        this.cartCheckOutClickListeners = listeners;
        this.couponClicked=couponClicked1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyCartCheckoutViewHolder(parent, cartCheckOutClickListeners,couponClicked);
    }
}
