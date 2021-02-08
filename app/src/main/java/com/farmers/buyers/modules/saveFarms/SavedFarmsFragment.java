package com.farmers.buyers.modules.saveFarms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.SpacesItemDecoration;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.HomeTransformer;
import com.farmers.buyers.modules.saveFarms.adapter.SavedFarmsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:42
 * mohammadsajjad679@gmail.com
 */

public class SavedFarmsFragment extends Fragment {
    private RecyclerView recyclerView;
    private SavedFarmsAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_farms_fragment, container,false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id.saved_farms_recyclerView);
        adapter = new SavedFarmsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, 10, false));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        items.addAll(SaveFarmTransformer.getFarmListItem());
        adapter.updateData(items);
    }
}
