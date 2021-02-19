package com.farmers.buyers.modules.chat.view;

import android.view.ViewGroup;

import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 18:40
 * mohammadsajjad679@gmail.com
 */

public class ChatItemDelegate extends BaseDelegate {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ChatViewHolder(parent);
    }
}
