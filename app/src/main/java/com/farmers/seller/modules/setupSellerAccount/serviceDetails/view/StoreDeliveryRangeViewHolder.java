package com.farmers.seller.modules.setupSellerAccount.serviceDetails.view;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.model.StoreDeliveryRangeItems;

/**
 * Created by Mohammad sajjad on 04-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class StoreDeliveryRangeViewHolder extends BaseViewHolder {
    private TextView rangeTv;
    private RangeSelectedListener listener;
    private int selectedPosition = -1;

    public StoreDeliveryRangeViewHolder(@NonNull ViewGroup parent, RangeSelectedListener listener) {
        super(Extensions.inflate(parent, R.layout.store_setup_delivery_range_item_layout));
        rangeTv = itemView.findViewById(R.id.setup_range_delivery_item_title_tv);
        this.listener = listener;


    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        StoreDeliveryRangeItems item = (StoreDeliveryRangeItems) items;
        rangeTv.setText(item.getTitle());

        if (selectedPosition == getAdapterPosition()) {
            selectedPosition = getOldPosition();
            rangeTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.green_rect_bg));
        } else {
            rangeTv.setBackground(null);
            rangeTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
        }

        rangeTv.setOnClickListener(view -> {
            selectedPosition = getAdapterPosition();
            rangeTv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.green_rect_bg));
            listener.onRangeSelectListener(getAdapterPosition(), item.getTitle());
        });
    }

    public interface RangeSelectedListener {
        void onRangeSelectListener(int position, String range);
    }
}