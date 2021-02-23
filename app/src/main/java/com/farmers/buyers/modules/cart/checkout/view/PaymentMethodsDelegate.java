package com.farmers.buyers.modules.cart.checkout.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:13
 * mohammadsajjad679@gmail.com
 */

public class PaymentMethodsDelegate extends BaseDelegate {
    private PaymentMethodsViewHolder.PaymentMethodListener paymentMethodListener;

    public PaymentMethodsDelegate(PaymentMethodsViewHolder.PaymentMethodListener paymentMethodListener) {
        this.paymentMethodListener = paymentMethodListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PaymentMethodsViewHolder(parent, paymentMethodListener);
    }
}
