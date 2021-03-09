package com.farmers.seller.modules.broadcastMessage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.profile.model.ProfileRequestParams;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageRequest;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageResponse;
import com.farmers.seller.modules.viewOrderDetails.ViewOrderDetailsActivity;

public class CreateMessageActivity extends BaseActivity implements View.OnClickListener {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(BroadcastMessageViewModel.class)) {
                return (T) new BroadcastMessageViewModel();
            }
            return null;
        }
    };
    public BroadcastMessageViewModel viewModel = factory.create(BroadcastMessageViewModel.class);
    private MutableLiveData<DataFetchState<BroadcastMessageResponse>> broadcastMessageStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<BroadcastMessageResponse>> editBroadcastMessageStateMachine = new MutableLiveData<>();

    private AppController appController = AppController.get();
    private Button bt_broadcast_draft, bt_broadcast_publish;
    private EditText ed_title, ed_description;
    private RadioGroup radio_group;
    private String targetAudience = "";
    private RadioButton radio_allBuyers, radio_allFollowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        setupToolbar(new BaseActivity.ToolbarConfig("Broadcast Message", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new BaseActivity.ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();

    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
        bt_broadcast_publish = findViewById(R.id.bt_broadcast_publish);
        bt_broadcast_draft = findViewById(R.id.bt_broadcast_draft);
        ed_title = findViewById(R.id.ed_title);
        ed_description = findViewById(R.id.ed_description);
        radio_group = findViewById(R.id.radio_group);
        radio_allBuyers = findViewById(R.id.radio_allBuyers);
        radio_allFollowers = findViewById(R.id.radio_allFollowers);

        if (getIntent().getStringExtra("FROM").equalsIgnoreCase("Edit")) {
            ed_title.setText(getIntent().getStringExtra("Title"));
            ed_description.setText(getIntent().getStringExtra("Message"));
            if (getIntent().getStringExtra("PublishOn").equalsIgnoreCase("0")) {
                radio_allBuyers.setChecked(true);
                radio_allFollowers.setChecked(false);
            } else {
                radio_allBuyers.setChecked(false);
                radio_allFollowers.setChecked(true);
            }
        }

        radio_group.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (radioGroup1.getCheckedRadioButtonId()) {
                case R.id.radio_allBuyers: //All Buyers = 1 & All Followers = 2
                    targetAudience = "0";
                    break;

                case R.id.radio_allFollowers:
                    targetAudience = "1";
                    break;
            }
        });

        broadcastMessageStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    AlertHelper.showAlert(CreateMessageActivity.this, "Broadcast Message",
                            farmListResponseDataFetchState.status_message, true, "Ok",
                            false, "", true, new OnAlertClickListener() {
                                @Override
                                public void onNegativeBtnClicked() {
                                    finish();
                                }

                                @Override
                                public void onPositiveBtnClicked() {
                                    finish();
                                }
                            });
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        editBroadcastMessageStateMachine.observe(this, farmListResponseDataFetchState -> {
            switch (farmListResponseDataFetchState.status) {
                case ERROR:
                    dismissLoader();
                    Toast.makeText(this, farmListResponseDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    AlertHelper.showAlert(CreateMessageActivity.this, "Broadcast Message",
                            farmListResponseDataFetchState.status_message, true, "Ok",
                            false, "", true, new OnAlertClickListener() {
                                @Override
                                public void onNegativeBtnClicked() {
                                    finish();
                                }

                                @Override
                                public void onPositiveBtnClicked() {
                                    finish();
                                }
                            });
                    break;
                case LOADING:
                    showLoader();
                    break;
            }
        });

        bt_broadcast_publish.setOnClickListener(this);
        bt_broadcast_draft.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_broadcast_draft:
                if (getIntent().getStringExtra("FROM").equalsIgnoreCase("Edit")) {
                    BroadcastMessageRequest broadcastMessageRequest1 = new BroadcastMessageRequest(
                            appController.getLoginId(),
                            appController.getAuthenticationKey(),
                            appController.getFarmId(),
                            ed_title.getText().toString().trim(),
                            ed_description.getText().toString().trim(),
                            targetAudience,
                            "0",
                            getIntent().getStringExtra("messageId"));
                    viewModel.editBroadcastMessage(editBroadcastMessageStateMachine, broadcastMessageRequest1);
                } else {
                    BroadcastMessageRequest broadcastMessageRequest1 = new BroadcastMessageRequest(
                            appController.getLoginId(),
                            appController.getAuthenticationKey(),
                            appController.getFarmId(),
                            ed_title.getText().toString().trim(),
                            ed_description.getText().toString().trim(),
                            targetAudience,
                            "0");
                    viewModel.publishBroadcastMessage(broadcastMessageStateMachine, broadcastMessageRequest1);
                }
                break;

            case R.id.bt_broadcast_publish:
                if (getIntent().getStringExtra("FROM").equalsIgnoreCase("Edit")) {
                    BroadcastMessageRequest broadcastMessageRequest2 = new BroadcastMessageRequest(
                            appController.getLoginId(),
                            appController.getAuthenticationKey(),
                            appController.getFarmId(),
                            ed_title.getText().toString().trim(),
                            ed_description.getText().toString().trim(),
                            targetAudience,
                            "1",
                            getIntent().getStringExtra("messageId"));
                    viewModel.editBroadcastMessage(editBroadcastMessageStateMachine, broadcastMessageRequest2);
                } else {
                    BroadcastMessageRequest broadcastMessageRequest2 = new BroadcastMessageRequest(
                            appController.getLoginId(),
                            appController.getAuthenticationKey(),
                            appController.getFarmId(),
                            ed_title.getText().toString().trim(),
                            ed_description.getText().toString().trim(),
                            targetAudience,
                            "1");
                    viewModel.publishBroadcastMessage(broadcastMessageStateMachine, broadcastMessageRequest2);
                }
                break;
        }
    }
}