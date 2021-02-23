package com.farmers.buyers.modules.home.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
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
    private HomeCategoryListItemViewHolder.CategoryItemClickListener categoryItemClickListener;



    public HomeCategoriesViewHolder(@NonNull ViewGroup parent, HomeCategoryListItemViewHolder.CategoryItemClickListener categoryItemClickListener) {
        super(Extensions.inflate(parent, R.layout.home_categories_view_holder_layout));

        adapter = new HomeCategoryAdapter(categoryItemClickListener);
        recyclerView = itemView.findViewById(R.id.home_category_view_holder_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new LinearSpacesItemDecoration(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), HORIZONTAL, false));

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        this.adapter.updateData(((HomeCategoryListItem)items).homeCategoryItems);
    }
}
