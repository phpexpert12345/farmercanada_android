package com.farmers.buyers.modules.cart.myCart.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.farmers.buyers.storage.Constant;

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
    EditText couponEditText;
    TextView couponAmount,totalAmount;
    TextView shipingFee,packageFeeAmount,lableGst;
    TextView gstTaxAmount,subTotal,packageFeeLabel;
    float totalAmountf=0f;


    public MyCartCheckoutViewHolder(@NonNull ViewGroup parent, final MyCartCheckOutClickListeners listeners1, final MyCoupounClickListeners couponListener) {
        super(Extensions.inflate(parent, R.layout.my_cart_check_out_view_holder_layout));
        myCartApplyCouponLl = itemView.findViewById(R.id.my_cart_apply_coupon_ll);
        myCartApplyCouponTv = itemView.findViewById(R.id.my_cart_apply_coupon_tv);
        myCartAppliedCouponLayout = itemView.findViewById(R.id.my_cart_applied_coupon_layout);
        myCartRemoveCouponTv = itemView.findViewById(R.id.my_cart_remove_coupon_tv);
        couponEditText = itemView.findViewById(R.id.home_header_item_search_et);
        checkOutBtn = itemView.findViewById(R.id.my_cart_checkout_btn);
        couponAmount=itemView.findViewById(R.id.couponAmount);
        totalAmount=itemView.findViewById(R.id.totalAmount);
        shipingFee=itemView.findViewById(R.id.shiping_fee);
        lableGst=itemView.findViewById(R.id.lable_gst);
        packageFeeAmount=itemView.findViewById(R.id.packedge_fee_amount);
        gstTaxAmount=itemView.findViewById(R.id.gst_tax_amount);
        subTotal=itemView.findViewById(R.id.sub_total);
        packageFeeLabel=itemView.findViewById(R.id.packedge_fee_lable);


        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners1.onCheckOutClicked();

            }
        });
        myCartApplyCouponTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couponListener.onCouponClicked(couponEditText.getText().toString());
              /*  myCartAppliedCouponLayout.setVisibility(View.VISIBLE);
                myCartRemoveCouponTv.setVisibility(View.VISIBLE);
                myCartApplyCouponLl.setVisibility(View.GONE);
*/
            }
        });


        LocalBroadcastManager.getInstance(itemView.getContext()).registerReceiver(broadcastReceiver, new IntentFilter("CouponSubmit"));
        LocalBroadcastManager.getInstance(itemView.getContext()).registerReceiver(serviceTax, new IntentFilter(Constant.TAX_INTENT));


    }

    @Override
    public void bindView(final RecyclerViewListItem items) {

        myCartRemoveCouponTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final InputMethodManager imm = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(itemView.getWindowToken(), 0);
                myCartAppliedCouponLayout.setVisibility(View.GONE);
                myCartRemoveCouponTv.setVisibility(View.GONE);
                myCartApplyCouponLl.setVisibility(View.VISIBLE);
                couponEditText.setText("");
                couponEditText.setError(null);
                totalAmount.setText("-$ "+String.valueOf(totalAmountf));

            }
        });
    }

    BroadcastReceiver serviceTax=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            subTotal.setText("300.00");
            TaxData taxData=(TaxData) intent.getSerializableExtra("data");
            shipingFee.setText(taxData.getDeliveryCharge());
            packageFeeAmount.setText(taxData.getPackageFeeAmount());
            lableGst.setText("GST   ("+taxData.getgSTTax()+ "%):");
            gstTaxAmount.setText(taxData.getgSTTaxAmount());
            packageFeeLabel.setText("Package Fee ("+taxData.getPackageFeeTax()+"):");
            totalAmountf=300+Float.parseFloat(taxData.getgSTTaxAmount())+Float.parseFloat(taxData.getPackageFeeAmount())+
                    Float.parseFloat(taxData.getDeliveryCharge().toString());

            totalAmount.setText("-$ "+String.valueOf(totalAmountf));

        }
    };

    BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ApplyCouponData couponData=(ApplyCouponData)intent.getSerializableExtra("data");
          if (couponData.getCoupon_Discount_Price()!=null){
              myCartAppliedCouponLayout.setVisibility(View.VISIBLE);
              myCartRemoveCouponTv.setVisibility(View.VISIBLE);
              myCartApplyCouponLl.setVisibility(View.GONE);
              couponAmount.setText("-$ "+couponData.getCoupon_Discount_Price());
              totalAmount.setText("-$ "+(totalAmountf-Float.parseFloat(couponData.getCoupon_Discount_Price().toString())));
          }else {
              couponEditText.setError("Invalid Coupon");
          }

        }
    };
    public interface MyCartCheckOutClickListeners {
        public void onCheckOutClicked();
    }
    public interface MyCoupounClickListeners {
        public void onCouponClicked(String couponCode);
    }
}
