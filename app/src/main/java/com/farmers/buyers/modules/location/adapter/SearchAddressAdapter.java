package com.farmers.buyers.modules.location.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.location.model.SearchAddressListItem;

import java.util.ArrayList;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 18:11
 * mohammadsajjad679@gmail.com
 */

public class SearchAddressAdapter extends RecyclerView.Adapter<SearchAddressAdapter.SearchAddressViewHolder> {
    private ArrayList<SearchAddressListItem> items;
    private Context context;

    public SearchAddressAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_address_item_layout, parent, false);
        return new SearchAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAddressViewHolder holder, int position) {
        holder.locationName.setText(items.get(position).getLocation());
        holder.address.setText(items.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private void updateData(ArrayList<SearchAddressListItem> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    class SearchAddressViewHolder extends RecyclerView.ViewHolder {

        public TextView locationName, address;

        public SearchAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.search_address_item_location_tv);
            address = itemView.findViewById(R.id.search_address_item_address_tv);
        }
    }

}

