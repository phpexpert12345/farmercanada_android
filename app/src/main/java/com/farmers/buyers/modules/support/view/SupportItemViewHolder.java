package com.farmers.buyers.modules.support.view;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.model.OrderListItem;

public class SupportItemViewHolder extends BaseViewHolder {

    public SupportItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.support_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }

    public interface SupportItemClickListener {
        void onSupportItemClicked(int position);
    }
}
