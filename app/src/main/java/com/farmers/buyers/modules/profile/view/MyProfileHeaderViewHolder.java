package com.farmers.buyers.modules.profile.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 03:52
 * mohammadsajjad679@gmail.com
 */

public class MyProfileHeaderViewHolder extends BaseViewHolder {

    private LinearLayout followersLL, walletLL, inboxLL;

    public MyProfileHeaderViewHolder(@NonNull ViewGroup parent, final MyProfileItemClickListener profileItemClickListener) {
        super(Extensions.inflate(parent, R.layout.my_profile_header_layout));
        followersLL = itemView.findViewById(R.id.followers_ll);
        walletLL = itemView.findViewById(R.id.wallet_ll);
        inboxLL = itemView.findViewById(R.id.inbox_ll);

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

    }

    @Override
    public void bindView(RecyclerViewListItem items) {


    }

    public interface MyProfileItemClickListener {
        void onFollowersItemClicked();
        void onWalletClicked();
        void onInboxClicked();
    }
}
