package com.farmers.seller.modules.workingHour.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.ourOrders.model.SideMenuListItem;
import com.farmers.seller.modules.workingHour.model.StoreTimeListItem;

public class StoreTimeListViewHolder extends BaseViewHolder {

    StoreTimeListItem item;
    TextView tv_time_interval;
    LinearLayout ll_interval_select;
    RadioButton rd_time_interval_select;

    public StoreTimeListViewHolder(@NonNull ViewGroup parent, final StoreTimeItemClickListener storeTimeItemClickListener) {
        super(Extensions.inflate(parent, R.layout.layout_store_time_interval_item));

        tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
        ll_interval_select = itemView.findViewById(R.id.ll_interval_select);
        rd_time_interval_select = itemView.findViewById(R.id.rd_time_interval_select);

        rd_time_interval_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeTimeItemClickListener.onStoreTimeItemClicked(item);
            }
        });

        ll_interval_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeTimeItemClickListener.onStoreTimeItemClicked(item);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        StoreTimeListItem item = (StoreTimeListItem) items;
        this.item = item;
        tv_time_interval.setText(item.getInterval());
    }

    public interface StoreTimeItemClickListener {
        void onStoreTimeItemClicked(StoreTimeListItem storeTimeItem);
    }
}
