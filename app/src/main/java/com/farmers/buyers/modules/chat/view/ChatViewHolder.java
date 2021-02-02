package com.farmers.buyers.modules.chat.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 18:39
 * mohammadsajjad679@gmail.com
 */

public class ChatViewHolder extends BaseViewHolder {
    TextView inComingTv;
    TextView myMsgTv;

    public ChatViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.chat_item_layout));
        myMsgTv = itemView.findViewById(R.id.chat_my_msg_tv);
        inComingTv = itemView.findViewById(R.id.chat_incomming_msg_tv);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {


    }
}
