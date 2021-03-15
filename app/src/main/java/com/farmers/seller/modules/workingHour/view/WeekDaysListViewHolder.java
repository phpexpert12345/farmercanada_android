package com.farmers.seller.modules.workingHour.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.workingHour.adapter.OrderLimitListAdapter;
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;

import java.util.Calendar;

public class WeekDaysListViewHolder extends BaseViewHolder implements OrderLimitListAdapter.OrderLimitItemClickListener {

    RecyclerView rv_review_list;
    Dialog orderLimitDialog;
    WeekDayListItem item;
    TextView tv_time_interval;
    SwitchCompat switch_week;
    LinearLayout ll_order_limit_time;
    EditText ed_order_limit;
    int selectedPosition = -1;
    OrderLimitListAdapter.OrderLimitItemClickListener onStoreTimeItemClicked;
    public EditText ed_start_date, ed_end_date;
    public Button bt_save;

    public WeekDaysListViewHolder(@NonNull ViewGroup parent, final WeekDayItemClickListener weekDayItemClickListener) {
        super(Extensions.inflate(parent, R.layout.layout_week_day_item));
        onStoreTimeItemClicked = this;
        tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
        switch_week = itemView.findViewById(R.id.switch_week);
        ll_order_limit_time = itemView.findViewById(R.id.ll_order_limit_time);
        ed_order_limit = itemView.findViewById(R.id.ed_order_limit);
        ed_start_date = itemView.findViewById(R.id.ed_start_date);
        ed_end_date = itemView.findViewById(R.id.ed_end_date);
        bt_save = itemView.findViewById(R.id.bt_save);

        bt_save.setOnClickListener(view -> {
            selectedPosition = getAdapterPosition();
            if (TextUtils.isEmpty(ed_start_date.getText().toString().trim())) {
                Toast.makeText(itemView.getContext(), "Please Enter start time", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(ed_end_date.getText().toString().trim())) {
                Toast.makeText(itemView.getContext(), "Please Enter end time", Toast.LENGTH_SHORT).show();
            } else {
                weekDayItemClickListener.onWeekDayItemClicked(getAdapterPosition(),
                        ed_start_date.getText().toString().trim(),
                        ed_end_date.getText().toString().trim());
                switch_week.setChecked(false);
            }
        });

        switch_week.setOnCheckedChangeListener((compoundButton, b) -> {
            selectedPosition = getAdapterPosition();
            if (b) {
                ll_order_limit_time.setVisibility(View.VISIBLE);
            } else {
                ll_order_limit_time.setVisibility(View.GONE);
            }
            //weekDayItemClickListener.onUpdateSwitchItemClicked(getAdapterPosition());
        });

        ed_order_limit.setOnClickListener(view -> OrderLimitDialog(itemView.getContext(), item));

        ed_start_date.setOnClickListener(view -> weekDayItemClickListener.onStartDateClicked(getAdapterPosition(), ed_start_date));

        ed_end_date.setOnClickListener(view -> weekDayItemClickListener.onEndDateClicked(getAdapterPosition(), ed_end_date));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        WeekDayListItem item = (WeekDayListItem) items;
        this.item = item;
        tv_time_interval.setText(item.getInterval());
    }

    @Override
    public void onOrderLimitItemClicked(String item) {
        ed_order_limit.setText(item);
        orderLimitDialog.dismiss();
    }

    public void OrderLimitDialog(Context activity, WeekDayListItem item) {

        orderLimitDialog = new Dialog(activity, R.style.NewDialog);
        orderLimitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        orderLimitDialog.setCancelable(true);
        orderLimitDialog.setContentView(R.layout.layout_store_time_interval_dialog);

        TextView tv_dialog_title = orderLimitDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Order Limit");

        rv_review_list = orderLimitDialog.findViewById(R.id.rv_review_list);
        OrderLimitListAdapter orderLimitListAdapter = new OrderLimitListAdapter(itemView.getContext(), item.getDropDownDataList(),
                onStoreTimeItemClicked);
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

    public interface WeekDayItemClickListener {
        void onWeekDayItemClicked(int position, String startDate, String endDate);

        void onUpdateSwitchItemClicked(int position);

        void onStartDateClicked(int position, EditText startDate);

        void onEndDateClicked(int position, EditText endDate);
    }
}