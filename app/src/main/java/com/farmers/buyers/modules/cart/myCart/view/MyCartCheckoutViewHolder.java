package com.farmers.buyers.modules.cart.myCart.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.MyCartFragment;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponData;
import com.farmers.buyers.modules.cart.myCart.model.chargeTax.TaxData;
import com.farmers.buyers.modules.orders.OrderSingleton;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.text.DecimalFormat;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 18:31
 * mohammadsajjad679@gmail.com
 */

public class MyCartCheckoutViewHolder extends BaseViewHolder {

    LinearLayout applyCouponButtonLayout;
    TextView myCartApplyCouponTv;
    RelativeLayout appliedCouponAmountLayout;
    TextView removeCouponTextView;
    Button checkOutBtn;
    EditText couponEditText;
    TextView couponAmount, totalAmount;
    TextView shipingFee, packageFeeAmount, lableGst;
    TextView gstTaxAmount, subTotal, packageFeeLabel;
    double totalAmountf = 0f;
    RelativeLayout rl_shipping_fee;
    DecimalFormat decimalFormat;

    public MyCartCheckoutViewHolder(@NonNull ViewGroup parent, final MyCartCheckOutClickListeners listeners1, final MyCoupounClickListeners couponListener) {
        super(Extensions.inflate(parent, R.layout.my_cart_check_out_view_holder_layout));
        applyCouponButtonLayout = itemView.findViewById(R.id.my_cart_apply_coupon_ll);
        myCartApplyCouponTv = itemView.findViewById(R.id.my_cart_apply_coupon_tv);
        appliedCouponAmountLayout = itemView.findViewById(R.id.my_cart_applied_coupon_layout);
        removeCouponTextView = itemView.findViewById(R.id.my_cart_remove_coupon_tv);
        couponEditText = itemView.findViewById(R.id.home_header_item_search_et);
        checkOutBtn = itemView.findViewById(R.id.my_cart_checkout_btn);
        couponAmount = itemView.findViewById(R.id.couponAmount);
        totalAmount = itemView.findViewById(R.id.totalAmount);
        shipingFee = itemView.findViewById(R.id.shiping_fee);
        lableGst = itemView.findViewById(R.id.lable_gst);
        packageFeeAmount = itemView.findViewById(R.id.packedge_fee_amount);
        gstTaxAmount = itemView.findViewById(R.id.gst_tax_amount);
        subTotal = itemView.findViewById(R.id.sub_total);
        packageFeeLabel = itemView.findViewById(R.id.packedge_fee_lable);
        rl_shipping_fee = itemView.findViewById(R.id.rl_shipping_fee);
        decimalFormat=new DecimalFormat("##.##");

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners1.onCheckOutClicked();
            }
        });

        myCartApplyCouponTv.setOnClickListener(view -> {
            if (!couponEditText.getText().toString().trim().isEmpty())
                couponListener.onCouponClicked(couponEditText.getText().toString());
            else
                couponEditText.setError("Enter the Coupon code");
        });
    }

    @Override
    public void bindView(final RecyclerViewListItem items) {
        TaxData taxData = (TaxData) items;
        double su_total=Double.parseDouble(taxData.getSubTotal());

        subTotal.setText(String.format("%.2f",su_total));
        if (taxData.isApplyCouponButton()) {
            appliedCouponAmountLayout.setVisibility(View.VISIBLE);
            couponEditText.setError(null);
        } else {
            applyCouponButtonLayout.setVisibility(View.GONE);
        }
        if (taxData.isRemoveDiscountButton()) {
            removeCouponTextView.setVisibility(View.VISIBLE);
        } else {
            removeCouponTextView.setVisibility(View.GONE);
        }
        if (taxData.isDiscountTextView()) {
            appliedCouponAmountLayout.setVisibility(View.VISIBLE);
        } else {
            appliedCouponAmountLayout.setVisibility(View.GONE);
        }
        if (taxData.isCouponApplied()) {
            couponEditText.setText("");
            // couponEditText.setError("Invalid Coupon");
        } else {
            // couponEditText.setError(null);
            couponEditText.setText("");

        }
if(taxData.getDeliveryCharge() != null && !taxData.getDeliveryCharge().equalsIgnoreCase("")){
    if(!taxData.getDeliveryCharge().equalsIgnoreCase("0.00")){
        rl_shipping_fee.setVisibility(View.VISIBLE);
        shipingFee.setText(taxData.getDeliveryCharge());
    }
    else{
        rl_shipping_fee.setVisibility(View.GONE);
    }
}
else{
    rl_shipping_fee.setVisibility(View.GONE);
}
//        shipingFee.setText(taxData.getDeliveryCharge());
//        if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")).equals("1")) {
//            rl_shipping_fee.setVisibility(View.GONE);
//        } else {
//            rl_shipping_fee.setVisibility(View.VISIBLE);
//        }
        packageFeeAmount.setText(taxData.getPackageFeeAmount()+".00");
        lableGst.setText("GST   (" + taxData.getgSTTax() + "%):");
        double gst=Double.parseDouble(taxData.getgSTTaxAmount());
        gstTaxAmount.setText(String.format("%.2f",gst));
        packageFeeLabel.setText("Package Fee :");
        if(taxData.getDeliveryCharge()!=null) {
            totalAmountf = Double.parseDouble(taxData.getSubTotal()) + Double.parseDouble(taxData.getgSTTaxAmount()) + Double.parseDouble(taxData.getPackageFeeAmount());
            if(!taxData.getDeliveryCharge().equalsIgnoreCase("")){
                totalAmountf+=Double.parseDouble(taxData.getDeliveryCharge());
            }

        }
String price=decimalFormat.format(totalAmountf);
        totalAmount.setText("$"+String.format("%.2f",totalAmountf) );
        OrderSingleton.getInstance().setTaxData(taxData);
        OrderSingleton.getInstance().setTotal_amount(totalAmountf);

        if (taxData.getDiscountAmount() > 0) {
            OrderSingleton.getInstance().setCoupon_discount_amount(taxData.getDiscountAmount());
            couponAmount.setText("-$" + String.format("%.2f",taxData.getDiscountAmount()));
            totalAmount.setText("$" + String.format("%.2f",totalAmountf-Double.parseDouble(String.valueOf(taxData.getDiscountAmount()))));
            OrderSingleton.getInstance().setTotal_amount(totalAmountf);
        } else {

        }
        removeCouponTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final InputMethodManager imm = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(itemView.getWindowToken(), 0);
                couponEditText.setText("");
                couponEditText.setError(null);
                totalAmount.setText("-$ " + String.valueOf(totalAmountf));
                removeCouponTextView.setVisibility(View.GONE);
                applyCouponButtonLayout.setVisibility(View.VISIBLE);
                appliedCouponAmountLayout.setVisibility(View.GONE);
            }
        });
        // Log.d("SUBTOTAL", "bindView: "+taxData.getSubTotal());
    }

    public interface MyCartCheckOutClickListeners {
        public void onCheckOutClicked();
    }

    public interface MyCoupounClickListeners {
        public void onCouponClicked(String couponCode);
    }
}
