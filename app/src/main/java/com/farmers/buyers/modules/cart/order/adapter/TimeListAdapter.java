package com.farmers.buyers.modules.cart.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;

import java.util.List;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder> {

    private Context context;
    private List<AddressApiModel.AddressListData> modelArrayList;
    private TimeItemClickListener itemClickListener;
    private int selectedPosition = -1;

    public TimeListAdapter(Context context, List<AddressApiModel.AddressListData> modelArrayList, TimeItemClickListener itemClickListener) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time_slot, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_time_slot.setText(modelArrayList.get(position).current_time);

        if (selectedPosition == position) {
            holder.rd_check.setChecked(true);
        } else {
            holder.rd_check.setChecked(false);
        }

        holder.rd_check.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            itemClickListener.onTimeItemClicked(modelArrayList.get(position).current_time);
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_time_slot;
        private RadioButton rd_check;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time_slot = itemView.findViewById(R.id.tv_time_slot);
            rd_check = itemView.findViewById(R.id.rd_check);
        }
    }

    public interface TimeItemClickListener {
        void onTimeItemClicked(String timeSlot);
    }
}