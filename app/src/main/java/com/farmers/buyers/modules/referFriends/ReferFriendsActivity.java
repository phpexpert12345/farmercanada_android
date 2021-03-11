package com.farmers.buyers.modules.referFriends;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.address.MyAddressActivity;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.address.model.MyAddressViewModel;

public class ReferFriendsActivity extends BaseActivity implements View.OnClickListener {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ReferAndEarnViewModel.class)) {
                return (T) new ReferAndEarnViewModel();
            }
            return null;
        }
    };
    public ReferAndEarnViewModel viewModel = factory.create(ReferAndEarnViewModel.class);
    private MutableLiveData<DataFetchState<AddressApiModel>> stateMachine = new MutableLiveData<>();

    Button bt_share;
    LinearLayout ll_copy;
    TextView tv_referral_code, tv_referral_title, tv_referral_description;
    public ImageView img_referral;
    public String shareMsg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_friends);
        setupToolbar(new ToolbarConfig("Refer Friends", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, new ToolbarMenuConfig(R.drawable.ic_notification, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })));

        init();
    }

    private void init() {
        ll_copy = findViewById(R.id.ll_copy);
        tv_referral_code = findViewById(R.id.tv_referral_code);
        tv_referral_title = findViewById(R.id.tv_referral_title);
        tv_referral_description = findViewById(R.id.tv_referral_description);
        img_referral = findViewById(R.id.img_referal);
        bt_share = findViewById(R.id.bt_share);
        bt_share.setOnClickListener(this);
        ll_copy.setOnClickListener(this);

        viewModel.getReferAndEarn(stateMachine);

        stateMachine.observe(this, dataFetchState -> {
            switch (dataFetchState.status) {
                case ERROR: {
                    dismissLoader();
//                    Toast.makeText(ReferFriendsActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    showLoader();
                    break;
                }
                case SUCCESS: {
                    referAndEarnSuccess(dataFetchState);
                    break;
                }
            }
        });
    }

    private void referAndEarnSuccess(DataFetchState<AddressApiModel> dataFetchState) {
        Glide.with(this)
                .load(dataFetchState.data.getData().referral_photo)
                .centerCrop()
                .placeholder(R.mipmap.refer)
                .into(img_referral);
        tv_referral_title.setText(dataFetchState.data.getData().refer_a_friend_heading);
        tv_referral_description.setText(dataFetchState.data.getData().refer_a_friend_heading_description);
        shareMsg = dataFetchState.data.getData().refer_a_friend_sharing;
        tv_referral_code.setText(dataFetchState.data.getData().referral_code);
        dismissLoader();
//        Toast.makeText(ReferFriendsActivity.this, dataFetchState.status_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_share:
                sendToWhatsapp(shareMsg);
                break;
            case R.id.ll_copy:
                copyText(tv_referral_code.getText().toString().trim());
                break;
        }
    }

    private void sendToWhatsapp(String shareMsg) {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        waIntent.putExtra(Intent.EXTRA_SUBJECT, "Farmer Android App");
        waIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
        startActivity(Intent.createChooser(waIntent, "Share via"));
    }

    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text label", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
    }
}