package com.farmers.buyers.modules.seller.coupon.addCoupon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.seller.coupon.EditCouponExtra;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddNewCouponActivity extends BaseActivity {

    public static final String EDIT_COUPON_EXTRA = "edit_coupon_extra";

    private TextView couponCodeTv, startTimeTv, endTimeTv;
    private RadioGroup discountTypeRadioGroup, addCouponDiscountTimeType;
    private TextInputEditText minimumOrderAmountEt, termsConditionEt;
    private EditText discountPercentEt;
    private Button saveBtn;
    private String percentTop;  //todo discount_type (Fixed=0,%=1)
    private String discountTypeRd ; //todo discount_type_check(Current=0, Future = 1)
    private LinearLayout add_new_coupon_time_ll;
    private EditCouponExtra extra;

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddCouponViewModel.class));{
                return (T) new AddCouponViewModel();
            }
        }
    };

    private AddCouponViewModel viewModel = factory.create(AddCouponViewModel.class);
    private MutableLiveData<DataFetchState<AddCouponApiModel>> stateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddCouponApiModel>> updateStateMachine = new MutableLiveData<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_coupon);
        extra = (EditCouponExtra) getIntent().getSerializableExtra(EDIT_COUPON_EXTRA);

        setupToolbar(new ToolbarConfig(extra == null ? "Add New Coupon" : "Edit Coupon", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));


        init();

    }

    private void init() {
       couponCodeTv = findViewById(R.id.add_new_coupon_code);
       startTimeTv = findViewById(R.id.add_new_coupon_start_time_tv);
       endTimeTv = findViewById(R.id.add_new_coupon_end_time_tv);
       discountTypeRadioGroup = findViewById(R.id.add_new_coupon_radio_group);
       addCouponDiscountTimeType = findViewById(R.id.add_new_coupon_discount_type);
       minimumOrderAmountEt = findViewById(R.id.add_new_coupon_minimum_order_amount_et);
       termsConditionEt = findViewById(R.id.add_coupon_terms_condition_et);
       discountPercentEt = findViewById(R.id.add_new_coupon_percent_et);
       saveBtn = findViewById(R.id.add_new_coupon_save_btn);
        add_new_coupon_time_ll = findViewById(R.id.add_new_coupon_time_ll);

        couponCodeTv.setText(Extensions.getRandomCode());
        if (extra != null) {
            bindData();
        }

       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (extra == null) {
                   viewModel.addCoupon(stateMachine, couponCodeTv.getText().toString(), percentTop, discountPercentEt.getText().toString(),minimumOrderAmountEt.getText().toString(),termsConditionEt.getText().toString(),startTimeTv.getText().toString(), endTimeTv.getText().toString());
               }
               else {
                   viewModel.editCoupon(updateStateMachine, couponCodeTv.getText().toString(), percentTop, discountPercentEt.getText().toString(),minimumOrderAmountEt.getText().toString(),termsConditionEt.getText().toString(),startTimeTv.getText().toString(), endTimeTv.getText().toString(), extra.getCouponId());

               }
           }
       });

       stateMachine.observe(this, addCouponApiModelDataFetchState -> {
           switch (addCouponApiModelDataFetchState.status) {
               case LOADING: loading(); break;
               case SUCCESS: { success();
               AlertHelper.showAlert(AddNewCouponActivity.this, "Coupon Added", addCouponApiModelDataFetchState.status_message, true, "Ok", false, "", true, new OnAlertClickListener() {
                   @Override
                   public void onNegativeBtnClicked() {

                   }

                   @Override
                   public void onPositiveBtnClicked() {
                       finish();
                   }
               });
               break;
               }

               case ERROR: error(addCouponApiModelDataFetchState.status_message); break;
           }

       });
       updateStateMachine.observe(this, addCouponApiModelDataFetchState -> {
           switch (addCouponApiModelDataFetchState.status) {
               case LOADING: loading(); break;
               case SUCCESS: { success();
               AlertHelper.showAlert(AddNewCouponActivity.this, "Coupon Update", addCouponApiModelDataFetchState.status_message, true, "Ok", false, "", true, new OnAlertClickListener() {
                   @Override
                   public void onNegativeBtnClicked() {

                   }

                   @Override
                   public void onPositiveBtnClicked() {
                       finish();
                   }
               });
               break;
               }

               case ERROR: error(addCouponApiModelDataFetchState.status_message); break;
           }

       });

       startTimeTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String date;
               int mYear, mMonth, mDay;

               // Get Current Date
               final Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);


               DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewCouponActivity.this,
                       new DatePickerDialog.OnDateSetListener() {

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {

                               startTimeTv.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                           }
                       }, mYear, mMonth, mDay);
               datePickerDialog.show();           }
       });

       endTimeTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String date;
               int mYear, mMonth, mDay;

               // Get Current Date
               final Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);


               DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewCouponActivity.this,
                       new DatePickerDialog.OnDateSetListener() {

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {

                               endTimeTv.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                           }
                       }, mYear, mMonth, mDay);
               datePickerDialog.show();
           }
       });

       discountTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId) {
                   case R.id.add_new_coupon_percent_radio: percentTop ="1"; break;
                   case R.id.add_new_coupon_fixed_radio: percentTop = "0"; break;

               }
           }
       });

       addCouponDiscountTimeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId) {
                   case R.id.add_new_coupon_current_active_radio: discountTypeRd = "0"; add_new_coupon_time_ll.setVisibility(View.GONE); break;
                   case R.id.add_new_coupon_future_radio: discountTypeRd = "1"; add_new_coupon_time_ll.setVisibility(View.VISIBLE) ; break;

               }
           }
       });

    }

    private void bindData() {
        if (extra != null) {
            couponCodeTv.setText(extra.getCouponCode());
                    minimumOrderAmountEt.setText(extra.getMinimumOrder());
            termsConditionEt.setText(extra.getTermsCondition());
                    discountPercentEt.setText(extra.getAmount());

                    if (!extra.getStartDate().isEmpty()){
                        startTimeTv.setText(extra.getStartDate());
                    }
                    if (!extra.getEndDate().isEmpty()) {
                        endTimeTv.setText(extra.getEndDate());
                    }
        }
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();

    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}