package com.farmers.buyers.modules.inbox.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.model.MessageListItems;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 17:01
 * mohammadsajjad679@gmail.com
 */

public class MessageListViewHolder extends BaseViewHolder {
    CardView messageCard;

    public MessageListViewHolder(@NonNull ViewGroup parent, final MessageItemClickListener messageItemClickListener) {
        super(Extensions.inflate(parent, R.layout.message_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageItemClickListener.onMessageItemClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {


    }

    public interface MessageItemClickListener {
        void onMessageItemClicked(int position);
    }
}
