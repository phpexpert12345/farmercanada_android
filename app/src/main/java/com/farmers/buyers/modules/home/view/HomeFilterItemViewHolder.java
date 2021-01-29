package com.farmers.buyers.modules.home.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.MultipleTextItems;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.adapter.HomeFilterTypeAdapter;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:19
 * mohammadsajjad679@gmail.com
 */

public class HomeFilterItemViewHolder extends BaseViewHolder {
    private HomeFilterTypeAdapter adapter;
    private RecyclerView recyclerView;

    public HomeFilterItemViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.multiple_text_view_holder_layout));
        recyclerView = itemView.findViewById(R.id.multiple_text_item_recyclerView);
        adapter = new HomeFilterTypeAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), HORIZONTAL, false));

    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        adapter.updateData(((HomeFilterListItems)items).getItem());
    }
}
