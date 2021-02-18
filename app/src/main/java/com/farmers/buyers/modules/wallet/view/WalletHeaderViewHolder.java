package com.farmers.buyers.modules.wallet.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 02-02-2021 at 17:12
 * mohammadsajjad679@gmail.com
 */

public class WalletHeaderViewHolder extends BaseViewHolder {

    TextView tv_wallet_balance;
    ImageView backImage;
    Button withDrawBtn;
    AppController appController = AppController.get();

    public WalletHeaderViewHolder(@NonNull ViewGroup parent, final WalletHeaderClickListener walletHeaderClickListener) {
        super(Extensions.inflate(parent, R.layout.wallet_header_layout));
        backImage = itemView.findViewById(R.id.wallet_back_image);
        withDrawBtn = itemView.findViewById(R.id.wallet_withdraw_btn);
        tv_wallet_balance = itemView.findViewById(R.id.tv_wallet_balance);

        tv_wallet_balance.setText("$ " + appController.getWalletAmount());

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletHeaderClickListener.onBackClicked();
            }
        });

        withDrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletHeaderClickListener.onWithdrawClicked();
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }

    public interface WalletHeaderClickListener {
        void onBackClicked();

        void onWithdrawClicked();
    }
}
