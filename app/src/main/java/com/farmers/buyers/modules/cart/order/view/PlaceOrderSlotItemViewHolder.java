package com.farmers.buyers.modules.cart.order.view;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.order.model.PlaceOrderSlotItem;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:28
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotItemViewHolder extends BaseViewHolder {

    LinearLayout slotItemLl;
    TextView slotItemDateTv;
    TextView slotItemDayTv;
    private int selected = -1;
    private SlotCheckedListener listener;

    public PlaceOrderSlotItemViewHolder(@NonNull ViewGroup parent, SlotCheckedListener listener) {
        super(Extensions.inflate(parent, R.layout.place_order_slot_item_holder));
        slotItemLl = itemView.findViewById(R.id.place_order_slot_item_ll);
        slotItemDateTv = itemView.findViewById(R.id.place_order_slot_item_day_tv);
        slotItemDayTv = itemView.findViewById(R.id.place_order_slot_item_date_tv);
        this.listener = listener;
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        PlaceOrderSlotItem item = (PlaceOrderSlotItem) items;
        slotItemDayTv.setText(item.getDay());
        slotItemDayTv.setText(item.getDate());

        slotItemLl.setOnClickListener(v -> {
            selected = getAdapterPosition();
            listener.onSlotSelected(item.getDate(), getAdapterPosition());
        });

        if (selected == getAdapterPosition()) {
            selected = getOldPosition();
            slotItemLl.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.red_gradient_bg));
            slotItemDateTv.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
            slotItemDayTv.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
        } else {
            slotItemLl.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_transparent_gray_bg));
            slotItemDateTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
            slotItemDayTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
        }
    }

    public interface SlotCheckedListener {
        void onSlotSelected(String slot, int position);
    }
}