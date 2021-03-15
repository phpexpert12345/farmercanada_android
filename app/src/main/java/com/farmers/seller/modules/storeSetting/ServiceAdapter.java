package com.farmers.seller.modules.storeSetting;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.seller.modules.setupSellerAccount.serviceDetails.model.StoreDeliveryRangeItems;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    List<StoreDeliveryRangeItems> storeDeliveryRangeItems;
    int selectedPosition=-1;
    Context context;
    public ServiceAdapter(List<StoreDeliveryRangeItems> storeDeliveryRangeItems,Context context,String selected){
        this.context=context;
        this.storeDeliveryRangeItems=storeDeliveryRangeItems;
        for(int i=0;i<storeDeliveryRangeItems.size();i++){
            if(storeDeliveryRangeItems.get(i).getTitle().equalsIgnoreCase(selected)){
                selectedPosition=i;
                break;
            }
        }
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.store_setup_delivery_range_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        StoreDeliveryRangeItems item = storeDeliveryRangeItems.get(position);
       holder.rangeTv.setText(item.getTitle());

        if (selectedPosition == position) {
            holder.rangeTv.setTextColor(context.getResources().getColor(R.color.white));
            holder.rangeTv.setBackground(context.getResources().getDrawable(R.drawable.green_rect_bg));
        } else {
            holder.rangeTv.setBackground(null);
            holder.rangeTv.setTextColor(context.getResources().getColor(R.color.primaryTextColor));
        }

        holder.rangeTv.setOnClickListener(view -> {
            selectedPosition = position;
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return storeDeliveryRangeItems.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        private TextView rangeTv;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            rangeTv=itemView.findViewById(R.id.setup_range_delivery_item_title_tv);
        }
    }
}
