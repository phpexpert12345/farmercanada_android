package com.farmers.buyers.modules.inbox.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:01
 * mohammadsajjad679@gmail.com
 */

public class MessageListViewHolder extends BaseViewHolder {

    public MessageListViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.message_list_item_layout));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }
}
