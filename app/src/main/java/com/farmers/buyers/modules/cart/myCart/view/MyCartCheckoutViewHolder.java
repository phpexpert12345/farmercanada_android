package com.farmers.buyers.modules.cart.myCart.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:31
 * mohammadsajjad679@gmail.com
 */

public class MyCartCheckoutViewHolder extends BaseViewHolder {


    LinearLayout myCartApplyCouponLl;
    TextView myCartApplyCouponTv;
    RelativeLayout myCartAppliedCouponLayout;
    TextView myCartRemoveCouponTv;
    Button  checkOutBtn;

    public MyCartCheckoutViewHolder(@NonNull ViewGroup parent, final MyCartCheckOutClickListeners listeners) {
        super(Extensions.inflate(parent, R.layout.my_cart_check_out_view_holder_layout));
        myCartApplyCouponLl = itemView.findViewById(R.id.my_cart_apply_coupon_ll);
        myCartApplyCouponTv = itemView.findViewById(R.id.my_cart_apply_coupon_tv);
        myCartAppliedCouponLayout = itemView.findViewById(R.id.my_cart_applied_coupon_layout);
        myCartRemoveCouponTv = itemView.findViewById(R.id.my_cart_remove_coupon_tv);
        checkOutBtn = itemView.findViewById(R.id.my_cart_checkout_btn);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.onCheckOutClicked();
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        myCartApplyCouponTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartAppliedCouponLayout.setVisibility(View.VISIBLE);
                        myCartRemoveCouponTv.setVisibility(View.VISIBLE);
                myCartApplyCouponLl.setVisibility(View.GONE);

            }
        });

        myCartRemoveCouponTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartAppliedCouponLayout.setVisibility(View.GONE);
                myCartRemoveCouponTv.setVisibility(View.GONE);
                myCartApplyCouponLl.setVisibility(View.VISIBLE);
            }
        });


    }

    public interface MyCartCheckOutClickListeners {
        public void onCheckOutClicked();
    }
}
