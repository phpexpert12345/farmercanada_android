package com.farmers.buyers.modules.cart.checkout.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.widget.DrawableRadioButton;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:13
 * mohammadsajjad679@gmail.com
 */

public class PaymentMethodsViewHolder extends BaseViewHolder {

    public PaymentMethodsViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.payment_methods_holder_layout));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
