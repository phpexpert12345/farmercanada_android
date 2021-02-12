package com.farmers.seller.modules.workingHour.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.workingHour.adapter.OrderLimitListAdapter;
import com.farmers.seller.modules.workingHour.adapter.PickupOrderLimitListAdapter;
import com.farmers.seller.modules.workingHour.model.PickupWeekDayListItem;
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;

public class PickupWeekDaysListViewHolder extends BaseViewHolder implements PickupOrderLimitListAdapter.PickupOrderLimitItemClickListener {

    RecyclerView rv_review_list;
    Dialog orderLimitDialog;
    PickupWeekDayListItem item;
    TextView tv_time_interval;
    SwitchCompat switch_week;
    LinearLayout ll_order_limit_time;
    EditText ed_order_limit;
    int selectedPosition = -1;
    PickupOrderLimitListAdapter.PickupOrderLimitItemClickListener pickupOrderLimitItemClickListener;

    public PickupWeekDaysListViewHolder(@NonNull ViewGroup parent, final PickupWeekDayItemClickListener pickupWeekDayItemClickListener) {
        super(Extensions.inflate(parent, R.layout.layout_week_day_item));
        pickupOrderLimitItemClickListener = this;
        tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
        switch_week = itemView.findViewById(R.id.switch_week);
        ll_order_limit_time = itemView.findViewById(R.id.ll_order_limit_time);
        ed_order_limit = itemView.findViewById(R.id.ed_order_limit);

        switch_week.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectedPosition = getAdapterPosition();
                if (b) {
                    ll_order_limit_time.setVisibility(View.VISIBLE);
                } else {
                    ll_order_limit_time.setVisibility(View.GONE);
                }
            }
        });

        ed_order_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderLimitDialog(itemView.getContext(), item);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        PickupWeekDayListItem item = (PickupWeekDayListItem) items;
        this.item = item;
        tv_time_interval.setText(item.getInterval());


       /* if (selectedPosition == getAdapterPosition()) {
            ll_order_limit_time.setVisibility(View.VISIBLE);
        } else {
            ll_order_limit_time.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onPickupOrderLimitItemClicked(String item) {
        ed_order_limit.setText(item);
        orderLimitDialog.dismiss();
    }

    public interface PickupWeekDayItemClickListener {
        void onPickupWeekDayItemClicked(WeekDayListItem weekDayListItem);
    }

    public void OrderLimitDialog(Context activity, PickupWeekDayListItem item) {

        orderLimitDialog = new Dialog(activity, R.style.NewDialog);
        orderLimitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        orderLimitDialog.setCancelable(true);
        orderLimitDialog.setContentView(R.layout.layout_store_time_interval_dialog);

        TextView tv_dialog_title = orderLimitDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Order Limit");

        rv_review_list = orderLimitDialog.findViewById(R.id.rv_review_list);
        PickupOrderLimitListAdapter orderLimitListAdapter = new PickupOrderLimitListAdapter(itemView.getContext(), item.getDropDownDataList(),
                pickupOrderLimitItemClickListener);
        rv_review_list.setHasFixedSize(true);
        rv_review_list.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        rv_review_list.setAdapter(orderLimitListAdapter);
        rv_review_list.setNestedScrollingEnabled(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(orderLimitDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        orderLimitDialog.getWindow().setAttributes(lp);
        orderLimitDialog.show();
    }
}