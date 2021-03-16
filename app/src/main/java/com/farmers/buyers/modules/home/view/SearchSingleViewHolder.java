package com.farmers.buyers.modules.home.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

public class SearchSingleViewHolder extends BaseViewHolder {
    private TextView titleTv;
    public SearchSingleViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.checkout_title));
        titleTv = itemView.findViewById(R.id.simple_checkout_name_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        SingleTextItem item = ((SingleTextItem)items);

        titleTv.setText(item.getTitle());

    }
}
