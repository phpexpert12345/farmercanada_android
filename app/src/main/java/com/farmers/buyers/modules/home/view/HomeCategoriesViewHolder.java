package com.farmers.buyers.modules.home.view;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.adapter.HomeCategoryAdapter;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 01:39
 * mohammadsajjad679@gmail.com
 */

public class HomeCategoriesViewHolder extends BaseViewHolder {
    private HomeCategoryAdapter adapter ;
    private RecyclerView recyclerView;

    public HomeCategoriesViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_categories_view_holder_layout));
        adapter = new HomeCategoryAdapter();
        recyclerView = itemView.findViewById(R.id.home_category_view_holder_recyclerView);
        recyclerView.setAdapter(adapter);
        int spacingInPixels = itemView.getContext().getResources().getDimensionPixelSize(R.dimen._8sdp);
        recyclerView.addItemDecoration(new LinearSpacesItemDecoration(spacingInPixels));
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), HORIZONTAL, false));

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        if (adapter == null) {
            Log.e("adapter", "null");
        }
        this.adapter.updateData(((HomeCategoryListItem)items).homeCategoryItems);
    }
}
