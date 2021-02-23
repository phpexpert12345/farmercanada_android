package com.farmers.buyers.modules.cart.order.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.order.adapter.PlaceOrderSlotItemAdapter;
import com.farmers.buyers.modules.cart.order.model.PlaceOrderSlotItem;
import com.farmers.buyers.modules.cart.order.model.PlaceOrderSlotListItems;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 00:28
 * mohammadsajjad679@gmail.com
 */

public class PlaceOrderSlotViewHolder extends BaseViewHolder {
    private RecyclerView slotRecyclerView;
    private PlaceOrderSlotItemAdapter adapter;


    public PlaceOrderSlotViewHolder(@NonNull ViewGroup parent, PlaceOrderSlotItemViewHolder.SlotCheckedListener listener) {
        super(Extensions.inflate(parent, R.layout.place_order_slot_layout_holder));
        slotRecyclerView = itemView.findViewById(R.id.place_order_slot_recyclerView);
        adapter = new PlaceOrderSlotItemAdapter(listener);
        slotRecyclerView.addItemDecoration(new LinearSpacesItemDecoration(20));
        slotRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        slotRecyclerView.setAdapter(adapter);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        adapter.updateData(((PlaceOrderSlotListItems)items).getItem());
    }
}
