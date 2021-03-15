package com.farmers.seller.modules.workingHour.storeTime;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.CustomAlertDialog;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.OnCustomAlertClickListener;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.seller.modules.ourOrders.OurOrdersViewModel;
import com.farmers.seller.modules.workingHour.WorkingHourTransformer;
import com.farmers.seller.modules.workingHour.WorkingHoursViewModel;
import com.farmers.seller.modules.workingHour.adapter.OrderLimitListAdapter;
import com.farmers.seller.modules.workingHour.adapter.StoreTimeListAdapter;
import com.farmers.seller.modules.workingHour.adapter.WeekDayListAdapter;
import com.farmers.seller.modules.workingHour.model.StoreTimeListItem;
import com.farmers.seller.modules.workingHour.model.WeekDayListItem;
import com.farmers.seller.modules.workingHour.model.WorkingHoursResponse;
import com.farmers.seller.modules.workingHour.view.StoreTimeListViewHolder;
import com.farmers.seller.modules.workingHour.view.WeekDaysListViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StoreTimeFragment extends BaseFragment implements StoreTimeListViewHolder.StoreTimeItemClickListener, View.OnClickListener,
        WeekDaysListViewHolder.WeekDayItemClickListener {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(WorkingHoursViewModel.class)) {
                return (T) new WorkingHoursViewModel();
            }
            return null;
        }
    };
    public WorkingHoursViewModel viewModel = factory.create(WorkingHoursViewModel.class);
    private MutableLiveData<DataFetchState<WorkingHoursResponse>> storeTimeUpdateStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<WorkingHoursResponse>> storeTimeStateMachine = new MutableLiveData<>();

    private Dialog storeTimeIntervalDialog;
    private RecyclerView rv_review_list;
    private StoreTimeListAdapter adapter;
    public EditText ed_store_time_interval;
    public boolean bojStatus = false;
    public StoreTimeSetup extract = new StoreTimeSetup();
    private RecyclerView rv_week_day_time;
    private WeekDayListAdapter weekAdapter;
    private Button bt_next_store_time;
    public String startTime, endTime;
    int sHours, sMinit;

    public StoreTimeFragment get() {
        return new StoreTimeFragment();
    }

    public StoreTimeFragment() {
    }

    @Override
    public String getTitle() {
        return "Store Time";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_store_time;
    }

    @Override
    public void bindView(View view) {
        ed_store_time_interval = view.findViewById(R.id.ed_store_time_interval);
        ed_store_time_interval.setOnClickListener(this);
        bt_next_store_time = view.findViewById(R.id.bt_next_store_time);

        rv_week_day_time = view.findViewById(R.id.rv_week_day_time);
        weekAdapter = new WeekDayListAdapter(this);
        rv_week_day_time.setAdapter(weekAdapter);
        rv_week_day_time.addItemDecoration(new EqualSpacingItemDecoration(2));
        rv_week_day_time.setLayoutManager(new LinearLayoutManager(getActivity()));

        prepareItems();

        viewModel.getStoreTimeList(storeTimeStateMachine);

        storeTimeStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    dismissLoader();
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        storeTimeUpdateStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(getActivity(), farmListResponseDataFetchState.status_message,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    dismissLoader();
                    CustomAlertDialog.showDialogCustom(baseActivity,
                            R.drawable.alert_icon,
                            "Store Time",
                            farmListResponseDataFetchState.status_message,
                            new OnCustomAlertClickListener() {
                                @Override
                                public void onNegativeBtnClicked(Dialog dialog) {
                                    dialog.dismiss();
                                    prepareItems();
                                    bojStatus = false;
                                }

                                @Override
                                public void onPositiveBtnClicked(Dialog dialog) {
                                    prepareItems();
                                    dialog.dismiss();
                                    bojStatus = false;
                                }
                            });
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        bt_next_store_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ed_store_time_interval:
                storeTimeIntervalDialog(getActivity());
                break;

            case R.id.bt_next_store_time:
                if (bojStatus) {
                    viewModel.AddUpdateStoreTimeList(storeTimeUpdateStateMachine, extract);
                } else {
                    Toast.makeText(baseActivity, "Please choose store time!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void prepareItems() {
        viewModel.items.clear();
        viewModel.weekItems.clear();
        viewModel.items.addAll(WorkingHourTransformer.getStoreTimeList());
        viewModel.weekItems.addAll(WorkingHourTransformer.getWeekDaysList());
        weekAdapter.updateData(viewModel.weekItems);
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
        adapter = new StoreTimeListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.addItemDecoration(new EqualSpacingItemDecoration(0));
        rv_review_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.updateData(viewModel.items);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(storeTimeIntervalDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        storeTimeIntervalDialog.getWindow().setAttributes(lp);
        storeTimeIntervalDialog.show();
    }

    @Override
    public void onStoreTimeItemClicked(StoreTimeListItem storeTimeItem) {
        storeTimeIntervalDialog.dismiss();
        ed_store_time_interval.setText(storeTimeItem.getInterval());
    }

    @Override
    public void onWeekDayItemClicked(int position, String startDate, String endDate) {

    }

    @Override
    public void onUpdateSwitchItemClicked(int position) {
        weekAdapter.notifyDataSetChanged();
        weekAdapter.notifyItemChanged(position);
    }

    @Override
    public void onStartDateClicked(int position, EditText startDate) {
        startTimePicker(position, startDate);
    }

    @Override
    public void onEndDateClicked(int position, EditText endDate) {
        endTimePicker(position, endDate);
    }

    public void startTimePicker(int position, EditText ed_start_date) {

        Dialog timeDialog = new Dialog(baseActivity, R.style.NewDialog);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setCancelable(true);
        timeDialog.setContentView(R.layout.layout_time_picker);

        Button bt_cancel = timeDialog.findViewById(R.id.bt_cancel);
        Button bt_select = timeDialog.findViewById(R.id.bt_select);
        TextView tv_dialog_title = timeDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Start Time");
        TimePicker time_picker = timeDialog.findViewById(R.id.time_picker);
        time_picker.setIs24HourView(false);

        bt_select.setOnClickListener(view -> {

            int hour, minute;
            String am_pm;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = time_picker.getHour();
                minute = time_picker.getMinute();
            } else {
                hour = time_picker.getCurrentHour();
                minute = time_picker.getCurrentMinute();
            }
            if (hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            } else {
                am_pm = "AM";
            }
            ed_start_date.setText(hour + ":" + minute + " " + am_pm);
            startTime = hour + ":" + minute + " " + am_pm;
            sHours = hour;
            sMinit = minute;

            bojStatus = true;
            switch (position) {
                case 0:
                    extract.mon_available = "Yes";
                    extract.mon_start_time = startTime;
                    break;
                case 1:
                    extract.tue_available = "Yes";
                    extract.tue_start_time = startTime;
                    break;
                case 2:
                    extract.wed_available = "Yes";
                    extract.wed_start_time = startTime;
                    break;
                case 3:
                    extract.thu_available = "Yes";
                    extract.thu_start_time = startTime;
                    break;
                case 4:
                    extract.fri_available = "Yes";
                    extract.fri_start_time = startTime;
                    break;
                case 5:
                    extract.sat_available = "Yes";
                    extract.sat_start_time = startTime;
                    break;
                case 6:
                    extract.sun_available = "Yes";
                    extract.sun_start_time = startTime;
                    break;
            }
            timeDialog.dismiss();
        });

        bt_cancel.setOnClickListener(view -> timeDialog.dismiss());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(timeDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        timeDialog.getWindow().setAttributes(lp);
        timeDialog.show();
    }

    public void endTimePicker(int position, EditText ed_end_date) {

        Dialog timeDialog = new Dialog(baseActivity, R.style.NewDialog);
        timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timeDialog.setCancelable(true);
        timeDialog.setContentView(R.layout.layout_time_picker);

        Button bt_cancel = timeDialog.findViewById(R.id.bt_cancel);
        Button bt_select = timeDialog.findViewById(R.id.bt_select);
        TextView tv_dialog_title = timeDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select End Time");
        TimePicker time_picker = timeDialog.findViewById(R.id.time_picker);
        time_picker.setIs24HourView(false);

        bt_select.setOnClickListener(view -> {

            int hour, minute;
            String am_pm;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = time_picker.getHour();
                minute = time_picker.getMinute();
            } else {
                hour = time_picker.getCurrentHour();
                minute = time_picker.getCurrentMinute();
            }
            if (hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            } else {
                am_pm = "AM";
            }
            ed_end_date.setText(hour + ":" + minute + " " + am_pm);
            endTime = hour + ":" + minute + " " + am_pm;
            if (startTime.equals(endTime)) {
                Toast.makeText(baseActivity, "Start and End time can not be same !!", Toast.LENGTH_LONG).show();
            } else {
                bojStatus = true;
                switch (position) {
                    case 0:
                        extract.mon_available = "Yes";
                        extract.mon_close_time = ed_end_date.getText().toString().trim();
                        break;
                    case 1:
                        extract.tue_available = "Yes";
                        extract.tue_close_time = ed_end_date.getText().toString().trim();
                        ;
                        break;
                    case 2:
                        extract.wed_available = "Yes";
                        extract.wed_close_time = ed_end_date.getText().toString().trim();
                        break;
                    case 3:
                        extract.thu_available = "Yes";
                        extract.thu_close_time = ed_end_date.getText().toString().trim();
                        break;
                    case 4:
                        extract.fri_available = "Yes";
                        extract.fri_close_time = ed_end_date.getText().toString().trim();
                        break;
                    case 5:
                        extract.sat_available = "Yes";
                        extract.sat_close_time = ed_end_date.getText().toString().trim();
                        break;
                    case 6:
                        extract.sun_available = "Yes";
                        extract.sun_close_time = ed_end_date.getText().toString().trim();
                        break;
                }
                timeDialog.dismiss();
            }
        });

        bt_cancel.setOnClickListener(view -> timeDialog.dismiss());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(timeDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        timeDialog.getWindow().setAttributes(lp);
        timeDialog.show();
    }
}