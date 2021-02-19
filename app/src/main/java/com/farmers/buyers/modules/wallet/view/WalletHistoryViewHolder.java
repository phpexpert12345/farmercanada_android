package com.farmers.buyers.modules.wallet.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class WalletHistoryViewHolder extends BaseViewHolder {

    TextView nameTv;
    TextView paymentStatusTv;
    TextView amountTv;
    TextView timeTv;
    ImageView paymentStatusImg;


    public WalletHistoryViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.wallet_history_item_layout));
;
        nameTv = itemView.findViewById(R.id.wallet_history_item_name_tv);
                paymentStatusTv = itemView.findViewById(R.id.wallet_history_item_status_tv);
        amountTv = itemView.findViewById(R.id.wallet_history_item_amount_tv);
                timeTv = itemView.findViewById(R.id.wallet_history_item_time_tv);
        paymentStatusImg = itemView.findViewById(R.id.wallet_history_item_status_image);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
