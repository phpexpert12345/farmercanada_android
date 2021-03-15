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

import java.util.List;

public class OrderLimitListAdapter extends RecyclerView.Adapter<OrderLimitListAdapter.ViewHolder> {

    private Context context;
    private List<DropDownData> modelArrayList;
    private OrderLimitItemClickListener onStoreTimeItemClicked;
    int type;

    public OrderLimitListAdapter(Context context, List<DropDownData> modelArrayList, OrderLimitItemClickListener onStoreTimeItemClicked,int type) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.onStoreTimeItemClicked = onStoreTimeItemClicked;
        this.type=type;
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

        holder.ll_interval_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rd_time_interval_select.setChecked(true);
                onStoreTimeItemClicked.onOrderLimitItemClicked(modelArrayList.get(position).getName(),type);
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

    public interface OrderLimitItemClickListener {
        void onOrderLimitItemClicked(String item,int type);
    }
}