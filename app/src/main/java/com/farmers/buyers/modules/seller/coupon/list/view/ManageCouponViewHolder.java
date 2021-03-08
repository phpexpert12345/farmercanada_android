package com.farmers.buyers.modules.seller.coupon.list.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.list.model.ManageCouponItem;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 17:05
 * mohammadsajjad679@gmail.com
 */

public class ManageCouponViewHolder extends BaseViewHolder {
    private TextView couponIdTv, addDateTv, endDateTv, couponStatusTv, couponCodeTv, editTv, deleteTv;

    public ManageCouponViewHolder(@NonNull ViewGroup parent, CouponItemClickListener listener) {
        super(Extensions.inflate(parent, R.layout.manage_coupon_item_layout));
        couponIdTv = itemView.findViewById(R.id.manage_coupon_id_tv);
        addDateTv = itemView.findViewById(R.id.manage_coupon_add_date_tv);
        couponStatusTv = itemView.findViewById(R.id.manage_coupon_add_status_tv);
        endDateTv = itemView.findViewById(R.id.manage_coupon_expire_date_tv);
        couponCodeTv = itemView.findViewById(R.id.manage_coupon_item_coupon_code_tv);
        deleteTv = itemView.findViewById(R.id.manage_coupon_item_delete_tv);
        editTv = itemView.findViewById(R.id.manage_coupon_item_edit_tv);

        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditCouponListener(getAdapterPosition());
            }
        });

        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteCouponListener(getAdapterPosition());
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ManageCouponItem item = (ManageCouponItem) items;


        couponIdTv.setText(item.getCouponId());
                addDateTv.setText(item.getAddDate());
        endDateTv.setText(item.getEndDate());
                couponStatusTv.setText(item.getStatus());
        couponCodeTv.setText(item.getCouponCode());

    }

    public interface CouponItemClickListener {
        void onDeleteCouponListener(int position);
        void onEditCouponListener(int position);
    }
}
