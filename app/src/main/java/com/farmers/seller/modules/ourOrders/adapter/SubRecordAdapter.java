package com.farmers.seller.modules.ourOrders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.seller.modules.ourOrders.model.RecordList;

import java.util.List;

public class SubRecordAdapter extends RecyclerView.Adapter<SubRecordAdapter.ViewHolder> {

    private List<RecordList> modelArrayList;

    public SubRecordAdapter(List<RecordList> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sub_records_items,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_product_name.setText(modelArrayList.get(position).product_name);
        holder.tv_product_quantity.setText(modelArrayList.get(position).item_quantity + "/" + modelArrayList.get(position).item_unit);
        holder.tv_product_price.setText("$" + modelArrayList.get(position).item_price);
        holder.tv_product_description.setText(modelArrayList.get(position).product_description);
        if (modelArrayList.get(position).product_description.isEmpty()) {
            holder.tv_product_description.setVisibility(View.GONE);
        } else {
            holder.tv_product_description.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_product_name, tv_product_quantity, tv_product_price, tv_product_description;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_quantity = itemView.findViewById(R.id.tv_product_quantity);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            tv_product_description = itemView.findViewById(R.id.tv_product_description);
        }
    }
}