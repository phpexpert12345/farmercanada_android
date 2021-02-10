package com.farmers.buyers.modules.profile.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.adapter.MyProfileOptionItemAdapter;
import com.farmers.buyers.modules.profile.model.MyProfileOptionItem;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 04:28
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionMenuHolder extends BaseViewHolder {
    private RecyclerView recyclerView;
    private MyProfileOptionItemAdapter adapter;

    public MyProfileOptionMenuHolder(@NonNull ViewGroup parent, MyProfileOptionItemViewHolder.OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked) {
        super(Extensions.inflate(parent, R.layout.my_profile_option_item_layout));
        recyclerView = itemView.findViewById(R.id.my_profile_option_item_recyclerView);
        adapter = new MyProfileOptionItemAdapter(profileOptionsGridMenuClicked);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, 40, false));
        recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        adapter.updateData(((MyProfileOptionItem)items).getItem());

    }
}
