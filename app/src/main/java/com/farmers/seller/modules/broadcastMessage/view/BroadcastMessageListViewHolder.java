package com.farmers.seller.modules.broadcastMessage.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.broadcastMessage.model.BroadcastMessageListItem;

import de.hdodenhof.circleimageview.CircleImageView;

public class BroadcastMessageListViewHolder extends BaseViewHolder {

    CardView messageCard;
    LinearLayout ll_order_accept, ll_order_reject;
    TextView tv_message, tv_date, tv_published_on, tv_publish;
    CircleImageView civ_delete;
    ImageView img_copy;
    BroadcastMessageListItem item;

    public BroadcastMessageListViewHolder(@NonNull ViewGroup parent, final BroadcastItemClickListener broadcastItemClickListener) {
        super(Extensions.inflate(parent, R.layout.broadcast_message_list_item_layout));

        messageCard = itemView.findViewById(R.id.message_item_card);
        ll_order_accept = itemView.findViewById(R.id.ll_order_accept);
        ll_order_reject = itemView.findViewById(R.id.ll_order_reject);
        tv_message = itemView.findViewById(R.id.tv_message);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_published_on = itemView.findViewById(R.id.tv_published_on);
        tv_publish = itemView.findViewById(R.id.tv_publish);
        civ_delete = itemView.findViewById(R.id.civ_delete);
        img_copy = itemView.findViewById(R.id.img_copy);

        civ_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                broadcastItemClickListener.onBroadcastItemClicked(item);
            }
        });

        img_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                broadcastItemClickListener.onBroadcastItemEditClicked(item);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        BroadcastMessageListItem item = (BroadcastMessageListItem) items;
        this.item = item;
        tv_message.setText(item.getMessage_title() + "\n" + item.getMessage_content());
        tv_date.setText(item.getPublish_on());
        tv_published_on.setText(item.getMessage_status_name());
        tv_publish.setText(item.getMessage_status_name());
    }

    public interface BroadcastItemClickListener {
        void onBroadcastItemClicked(BroadcastMessageListItem item);

        void onBroadcastItemEditClicked(BroadcastMessageListItem item);
    }
}