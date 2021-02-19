package com.farmers.seller.modules.workingHour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.seller.modules.workingHour.model.DropDownData;
import com.farmers.seller.modules.workingHour.model.PickupDropDownData;

import java.util.List;

public class PickupOrderLimitListAdapter extends RecyclerView.Adapter<PickupOrderLimitListAdapter.ViewHolder> {

    private Context context;
    private List<PickupDropDownData> modelArrayList;
    private PickupOrderLimitItemClickListener pickupOrderLimitItemClickListener;

    public PickupOrderLimitListAdapter(Context context, List<PickupDropDownData> modelArrayList, PickupOrderLimitItemClickListener pickupOrderLimitItemClickListener) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.pickupOrderLimitItemClickListener = pickupOrderLimitItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_store_time_interval_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_time_interval.setText(modelArrayList.get(position).getName());

        holder.rd_time_interval_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickupOrderLimitItemClickListener.onPickupOrderLimitItemClicked(modelArrayList.get(position).getName());
            }
        });

        holder.rd_time_interval_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickupOrderLimitItemClickListener.onPickupOrderLimitItemClicked(modelArrayList.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_time_interval;
        LinearLayout ll_interval_select;
        RadioButton rd_time_interval_select;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
            ll_interval_select = itemView.findViewById(R.id.ll_interval_select);
            rd_time_interval_select = itemView.findViewById(R.id.rd_time_interval_select);
        }
    }

    public interface PickupOrderLimitItemClickListener {
        void onPickupOrderLimitItemClicked(String item);
    }
}