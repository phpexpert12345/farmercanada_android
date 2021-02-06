package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsVegetablesAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsVegetablesListItem;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 12:00
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailVegetableViewHolder extends BaseViewHolder {
    private FarmDetailsVegetablesAdapter adapter;

    public FarmDetailVegetableViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.farm_detail_vegetables_holder_layout));
        RecyclerView recyclerView = itemView.findViewById(R.id.farm_detail_vegetables_recyclerView);
        adapter = new FarmDetailsVegetablesAdapter();
//        recyclerView.addItemDecoration(new LinearSpacesItemDecoration(R.dimen._8sdp));
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        adapter.updateData(((FarmDetailsVegetablesListItem) items).getItem());

    }
}
