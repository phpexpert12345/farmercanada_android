package com.farmers.seller.modules.workingHour.view;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;

public class WeekDaysListViewHolder extends BaseViewHolder implements OrderLimitListAdapter.OrderLimitItemClickListener {

    RecyclerView rv_review_list;
    Dialog orderLimitDialog;
    WeekDayListItem item;
    TextView tv_time_interval;
    SwitchCompat switch_week;
    LinearLayout ll_order_limit_time;
    EditText ed_order_limit,edit_end_time,edit_start_time;
    ImageView img_drop;
    int selectedPosition = -1;
    RelativeLayout relative_end_time,relative_start_time;
    OrderLimitListAdapter.OrderLimitItemClickListener onStoreTimeItemClicked;

    public WeekDaysListViewHolder(@NonNull ViewGroup parent, final WeekDayItemClickListener weekDayItemClickListener) {
        super(Extensions.inflate(parent, R.layout.layout_week_day_item));
        onStoreTimeItemClicked = this;
        tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
        switch_week = itemView.findViewById(R.id.switch_week);
        ll_order_limit_time = itemView.findViewById(R.id.ll_order_limit_time);
        ed_order_limit = itemView.findViewById(R.id.ed_order_limit);
        edit_start_time = itemView.findViewById(R.id.edit_start_time);
        edit_end_time = itemView.findViewById(R.id.edit_end_time);
        relative_end_time = itemView.findViewById(R.id.relative_end_time);
        relative_start_time = itemView.findViewById(R.id.relative_start_time);
        ed_order_limit.setFocusable(true);
        ed_order_limit.setCursorVisible(true);
        ed_order_limit.setInputType(InputType.TYPE_CLASS_NUMBER);
        img_drop = itemView.findViewById(R.id.img_drop);
       img_drop.setVisibility(View.GONE);
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
        edit_start_time.setOnClickListener(v->{

            OrderLimitDialog(itemView.getContext(), item,"Select Start Time",0);
        });
        edit_end_time.setOnClickListener(v->{

            OrderLimitDialog(itemView.getContext(), item,"Select End Time",1);
        });

//        ed_order_limit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OrderLimitDialog(itemView.getContext(), item);
//            }
//        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        WeekDayListItem item = (WeekDayListItem) items;
        this.item = item;
        tv_time_interval.setText(item.getInterval());


       /* if (selectedPosition == getAdapterPosition()) {
            ll_order_limit_time.setVisibility(View.VISIBLE);
        } else {
            ll_order_limit_time.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onOrderLimitItemClicked(String item,int type) {
        if(type==0){
            edit_start_time.setText(item);
        }
        else{
            edit_end_time.setText(item);
        }
//        ed_order_limit.setText(item);
        orderLimitDialog.dismiss();
    }

    public interface WeekDayItemClickListener {
        void onWeekDayItemClicked(WeekDayListItem weekDayListItem);
    }

    public void OrderLimitDialog(Context activity, WeekDayListItem item,String message,int type) {

        orderLimitDialog = new Dialog(activity, R.style.NewDialog);
        orderLimitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        orderLimitDialog.setCancelable(true);
        orderLimitDialog.setContentView(R.layout.layout_store_time_interval_dialog);

        TextView tv_dialog_title = orderLimitDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText(message);

        rv_review_list = orderLimitDialog.findViewById(R.id.rv_review_list);
        OrderLimitListAdapter orderLimitListAdapter = new OrderLimitListAdapter(itemView.getContext(), item.getDropDownDataList(),
                onStoreTimeItemClicked,type);
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