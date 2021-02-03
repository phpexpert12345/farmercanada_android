package com.farmers.buyers.core;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 10:27
 * mohammadsajjad679@gmail.com
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    // all the elements for this adapter

    private List<RecyclerViewListItem> items = new ArrayList<>();
    private ViewGroup viewGroup;

    // the delegates needed for this adapter
    protected HashMap<Integer, DelegateInterface> delegates = new HashMap<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewGroup = parent;
        BaseViewHolder viewHolder = null;

        try {
            DelegateInterface delegateInterface = (DelegateInterface) this.delegates.get(viewType);

            viewHolder = delegateInterface != null ? delegateInterface.onCreateViewHolder(parent) : null;
            throw new NoDelegateFoundException(viewType, this.getClass().getSimpleName());
        } catch (NoDelegateFoundException ex) {
            ex.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        DelegateInterface delegateInterface = (DelegateInterface) this.delegates.get(this.getItemViewType(position));
        if (delegateInterface != null) {
            delegateInterface.onBindViewHolder(holder, (RecyclerViewListItem) this.items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        Log.e("items", items.toString());
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    public void updateData(List<RecyclerViewListItem> items) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new BaseDiffUtils(items, items));
        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

    public RecyclerViewListItem getItemAt(int position) {
        return items.get(position);
    }

    public abstract void initDelegate();


}
