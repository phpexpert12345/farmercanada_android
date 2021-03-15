package com.farmers.seller.modules.workingHour.deliveryTime;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.workingHour.WorkingHourTransformer;
import com.farmers.seller.modules.workingHour.adapter.PickupTimeListAdapter;
import com.farmers.seller.modules.workingHour.adapter.PickupWeekDayListAdapter;
import com.farmers.seller.modules.workingHour.adapter.StoreTimeListAdapter;
import com.farmers.seller.modules.workingHour.adapter.WeekDayListAdapter;
import com.farmers.seller.modules.workingHour.model.PickupTimeListItem;
import com.farmers.seller.modules.workingHour.model.StoreTimeListItem;
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;
import com.farmers.seller.modules.workingHour.pickupTime.PickupTimeFragment;
import com.farmers.seller.modules.workingHour.view.PickupTimeListViewHolder;
import com.farmers.seller.modules.workingHour.view.PickupWeekDaysListViewHolder;
import com.farmers.seller.modules.workingHour.view.StoreTimeListViewHolder;
import com.farmers.seller.modules.workingHour.view.WeekDaysListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DeliveryTimeFragment extends BaseFragment implements View.OnClickListener, PickupWeekDaysListViewHolder.PickupWeekDayItemClickListener,
        PickupTimeListViewHolder.PickupTimeItemClickListener {

    private Dialog storeTimeIntervalDialog;
    private RecyclerView rv_review_list;
//    private StoreTimeListAdapter adapter;
    PickupTimeListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    public EditText ed_store_time_interval;

    private RecyclerView rv_week_day_time;
    private PickupWeekDayListAdapter weekAdapter;
    private List<RecyclerViewListItem> weekItems = new ArrayList<>();
    private Button bt_next_store_time;

    public DeliveryTimeFragment get() {
        return new DeliveryTimeFragment();
    }

    public DeliveryTimeFragment() {
    }

    @Override
    public String getTitle() {
        return "Delivery Time";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_delivery_time;
    }

    public void getDeliveryTime() {
    }

    @Override
    public void bindView(View view) {
        ed_store_time_interval = view.findViewById(R.id.ed_store_time_interval);
        ed_store_time_interval.setOnClickListener(this);
        bt_next_store_time = view.findViewById(R.id.bt_next_store_time);
        prepareItems();

        rv_week_day_time = view.findViewById(R.id.rv_week_day_time);
        weekAdapter = new PickupWeekDayListAdapter(this);
        rv_week_day_time.setAdapter(weekAdapter);
        rv_week_day_time.addItemDecoration(new EqualSpacingItemDecoration(2));
        rv_week_day_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        weekAdapter.updateData(weekItems);

        bt_next_store_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ed_store_time_interval:
                storeTimeIntervalDialog(getActivity());
                break;

            case R.id.bt_next_store_time:
                break;
        }
    }

    private void prepareItems() {
        items.addAll(WorkingHourTransformer.getPickupTimeList());
        weekItems.addAll(WorkingHourTransformer.getPickupWeekDaysList());
    }

    public void getStoreTime() {
    }

    public void storeTimeIntervalDialog(Activity activity) {

        storeTimeIntervalDialog = new Dialog(activity, R.style.NewDialog);
        storeTimeIntervalDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        storeTimeIntervalDialog.setCancelable(true);
        storeTimeIntervalDialog.setContentView(R.layout.layout_store_time_interval_dialog);

        TextView tv_dialog_title = storeTimeIntervalDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Time Interval");

        rv_review_list = storeTimeIntervalDialog.findViewById(R.id.rv_review_list);
        adapter = new PickupTimeListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.addItemDecoration(new EqualSpacingItemDecoration(0));
        rv_review_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.updateData(items);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(storeTimeIntervalDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        storeTimeIntervalDialog.getWindow().setAttributes(lp);
        storeTimeIntervalDialog.show();
    }





    @Override
    public void onPickupTimeItemClicked(PickupTimeListItem storeTimeItem) {
        storeTimeIntervalDialog.dismiss();
        ed_store_time_interval.setText(storeTimeItem.getInterval());
    }

    @Override
    public void onWeekDayItemClicked(int position, String startDate, String endDate) {

    }

    @Override
    public void onUpdateSwitchItemClicked(int position) {

    }

    @Override
    public void onStartDateClicked(int position, EditText startDate) {

    }

    @Override
    public void onEndDateClicked(int position, EditText endDate) {

    }
}