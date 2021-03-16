package com.farmers.buyers.modules.cart.checkout.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

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
    RadioButton codRadio, cardRadio, walletRadio;
    RelativeLayout relative_cash,relative_card,relative_wallet;

    public PaymentMethodsViewHolder(@NonNull ViewGroup parent, PaymentMethodListener paymentMethodListener) {
        super(Extensions.inflate(parent, R.layout.payment_methods_holder_layout));
        codRadio = itemView.findViewById(R.id.payment_method_cod_radio);
        cardRadio = itemView.findViewById(R.id.payment_method_card_radio);
        walletRadio = itemView.findViewById(R.id.payment_method_wallet_radio);
        relative_card=itemView.findViewById(R.id.relative_card);
        relative_cash=itemView.findViewById(R.id.relative_cash);
        relative_cash.setVisibility(View.GONE);
        relative_wallet=itemView.findViewById(R.id.relative_wallet);
        relative_card.setOnClickListener(v->{
            cardRadio.setChecked(true);
            walletRadio.setChecked(false);
            codRadio.setChecked(false);
            paymentMethodListener.onPaymentMethodCheckChangeListener(1,"Credit/Debit");
        });
        relative_cash.setOnClickListener(v->{
            cardRadio.setChecked(false);
            walletRadio.setChecked(false);
            codRadio.setChecked(true);
            paymentMethodListener.onPaymentMethodCheckChangeListener(0,"Cash");
        });
        relative_wallet.setOnClickListener(v->{
            cardRadio.setChecked(false);
            walletRadio.setChecked(true);
            codRadio.setChecked(false);
            paymentMethodListener.onPaymentMethodCheckChangeListener(2,"Wallet");
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }


    public interface PaymentMethodListener {
        void onPaymentMethodCheckChangeListener(int type,String pay_type);
    }
}
