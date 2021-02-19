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

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyCartItemViewHolder(parent);
    }
}
