package com.farmers.buyers.modules.profile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.SharedPreferenceManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 03:52
 * mohammadsajjad679@gmail.com
 */

public class MyProfileHeaderViewHolder extends BaseViewHolder {

    private LinearLayout followersLL, walletLL, inboxLL, ll_switch_user;
    private TextView tv_wallet_balance, tv_user_name, tv_user_email, tv_user_type;
    private AppController appController = AppController.get();
    private CircleImageView profile_header_user_image;

    public MyProfileHeaderViewHolder(@NonNull ViewGroup parent, final MyProfileItemClickListener profileItemClickListener) {
        super(Extensions.inflate(parent, R.layout.my_profile_header_layout));
        followersLL = itemView.findViewById(R.id.followers_ll);
        walletLL = itemView.findViewById(R.id.wallet_ll);
        inboxLL = itemView.findViewById(R.id.inbox_ll);
        ll_switch_user = itemView.findViewById(R.id.ll_switch_user);
        tv_wallet_balance = itemView.findViewById(R.id.tv_wallet_balance);
        profile_header_user_image = itemView.findViewById(R.id.profile_header_user_image);
        tv_user_name = itemView.findViewById(R.id.tv_user_name);
        tv_user_email = itemView.findViewById(R.id.tv_user_email);
        tv_user_type = itemView.findViewById(R.id.tv_user_type);

        tv_wallet_balance.setText("$ " + appController.getWalletAmount());
        tv_user_name.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_NAME", "")));
        tv_user_email.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_EMAIL", "")));
        tv_user_type.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_TYPE", "")));

        Glide.with(itemView.getContext())
                .load(appController.getProfilePic())
                .placeholder(R.drawable.user_image)
                .into(profile_header_user_image);

        followersLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileItemClickListener.onFollowersItemClicked();
            }
        });

        walletLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileItemClickListener.onWalletClicked();
            }
        });

        inboxLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileItemClickListener.onInboxClicked();
            }
        });

        ll_switch_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_seller_switch_dialog(itemView.getContext());
            }
        });

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }

    public void buyer_seller_switch_dialog(Context activity) {

        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.buyer_seller_switch_dialog);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    public interface MyProfileItemClickListener {
        void onFollowersItemClicked();

        void onWalletClicked();

        void onInboxClicked();
    }
}
