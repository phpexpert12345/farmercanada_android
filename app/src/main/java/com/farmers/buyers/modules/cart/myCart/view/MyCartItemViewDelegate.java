package com.farmers.buyers.modules.cart.myCart.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 17:45
 * mohammadsajjad679@gmail.com
 */

public class MyCartItemViewDelegate extends BaseDelegate {

    MyCartItemViewHolder.DecreaseCallback decreaseCallback;
    MyCartItemViewHolder.IncreaseCallback increaseCallback;

    public MyCartItemViewDelegate(MyCartItemViewHolder.DecreaseCallback decreaseCallback1,MyCartItemViewHolder.IncreaseCallback increaseCallback1) {

        decreaseCallback=decreaseCallback1;
        increaseCallback=increaseCallback1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyCartItemViewHolder(parent,increaseCallback,decreaseCallback);
    }
}
