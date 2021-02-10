package com.farmers.buyers.modules.seller.sellerProfile.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.view.MyProfileHeaderViewHolder;

/**
 * created by Mohammad Sajjad
 * on 09-02-2021 at 16:06
 * mohammadsajjad679@gmail.com
 */

public class SellerProfileHeaderViewHolder  extends BaseViewHolder {

    private LinearLayout followersLL, walletLL, inboxLL, ll_switch_user;

    public SellerProfileHeaderViewHolder(@NonNull ViewGroup parent, final SellerProfileItemClickListener profileItemClickListener) {
        super(Extensions.inflate(parent, R.layout.seller_profile_header_layout));
        followersLL = itemView.findViewById(R.id.seller_profile_followers_ll);
        walletLL = itemView.findViewById(R.id.seller_profile_wallet_ll);
        inboxLL = itemView.findViewById(R.id.seller_profile_inbox_ll);
        ll_switch_user = itemView.findViewById(R.id.seller_profile_ll_switch_user);

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

    public interface SellerProfileItemClickListener {
        void onFollowersItemClicked();

        void onWalletClicked();

        void onInboxClicked();
    }
}
