package com.farmers.buyers.modules.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.SimpleTitleItem;
import com.farmers.buyers.common.utils.EqualSpacingItemDecoration;
import com.farmers.buyers.common.utils.LinearSpacesItemDecoration;
import com.farmers.buyers.common.view.MultipleTextItemViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.adapter.HomeAdapter;
import com.farmers.buyers.modules.home.models.DeliveryTypeItems;
import com.farmers.buyers.modules.home.models.HomeCategoryListItem;
import com.farmers.buyers.modules.home.models.HomeFarmTypeItem;
import com.farmers.buyers.modules.home.models.HomeFilterListItems;
import com.farmers.buyers.modules.home.models.HomeHeaderItem;
import com.farmers.buyers.modules.home.models.HomeSearchListItem;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 15:09
 * mohammadsajjad679@gmail.com
 */

public class HomeFragment extends Fragment implements HomeHeaderViewHolder.HeaderItemClickListener, MultipleTextItemViewHolder.FilterItemClickListener {
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        prepareListItems();
        init();
        return view;
    }

    private void init() {

        adapter = new HomeAdapter(HomeFragment.this, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(40));
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemAt(position) instanceof HomeHeaderItem ||
                        adapter.getItemAt(position) instanceof HomeSearchListItem ||
                        adapter.getItemAt(position) instanceof HomeCategoryListItem ||
                        adapter.getItemAt(position) instanceof SimpleTitleItem ||
                        adapter.getItemAt(position) instanceof HomeTopOffersListItems ||
                        adapter.getItemAt(position) instanceof HomeFilterListItems ||
                        adapter.getItemAt(position) instanceof DeliveryTypeItems ||
                        adapter.getItemAt(position) instanceof HomeFarmTypeItem) {

                    return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(manager);
        adapter.updateData(items);
    }

    private void prepareListItems() {
        items.add(HomeTransformer.getHeaderItems());
        items.add(HomeTransformer.getSearchItems());
        items.add(HomeTransformer.getFilterItems());
        items.add(HomeTransformer.getCategoryList());
        items.add(new SimpleTitleItem("Top Offers"));
        items.add(HomeTransformer.getTopOffers());
        items.add(new DeliveryTypeItems());
        items.add(new HomeFarmTypeItem());
        items.addAll(HomeTransformer.getHomeFarmListItem());
    }


    @Override
    public void onEditAddressClickListener(int position) {
        Log.e("position", String.valueOf(position));
    }

    @Override
    public void onBecomeSellerClicked() {

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.buyer_seller_switch_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onFilterItemClicked(int position) {
        adapter.notifyDataSetChanged();
        adapter.notifyItemChanged(position);
    }
}
