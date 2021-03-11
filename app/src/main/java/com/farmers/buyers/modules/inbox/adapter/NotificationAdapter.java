package com.farmers.buyers.modules.inbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.farmers.buyers.R;
import com.farmers.buyers.common.model.NotificationLists;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    Context context;
    List<NotificationLists>notificationLists;
    public NotificationAdapter(List<NotificationLists>notificationLists,Context context){
        this.context=context;
        this.notificationLists=notificationLists;
    }
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_item_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationLists notificationList=notificationLists.get(position);
        holder.notificationNameTv.setText(notificationList.Notification_description);
        holder.orderDateTv.setText(notificationList.created_date);
        holder.orderStatusTv.setText(notificationList.Notification_heading);
        holder.orderNumberTv.setVisibility(View.GONE);
        Glide.with(context).load(notificationList.icon).placeholder(R.drawable.logo).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return notificationLists.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView notificationNameTv;
        TextView orderNumberTv;
        TextView orderStatusTv;
        TextView orderDateTv;
        RelativeLayout notificationItemLayout;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.notification_list_item_image);
            notificationNameTv = itemView.findViewById(R.id.notification_item_order_num_tv);
            orderNumberTv = itemView.findViewById(R.id.notification_list_item_name_tv);
            orderStatusTv = itemView.findViewById(R.id.notification_list_item_status_tv);
            orderDateTv = itemView.findViewById(R.id.notification_list_item_date_tv);
            notificationItemLayout = itemView.findViewById(R.id.notification_item_layout);
        }
    }
}
