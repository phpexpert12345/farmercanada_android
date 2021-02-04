package com.farmers.buyers.modules.cart.myCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.MyCartTransformer;
import com.farmers.buyers.modules.cart.checkout.CheckOutFromCartActivity;
import com.farmers.buyers.modules.cart.myCart.adapter.MyCartAdapter;
import com.farmers.buyers.modules.cart.myCart.model.MyCartCheckOutItem;
import com.farmers.buyers.modules.cart.myCart.view.MyCartCheckoutViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:39
 * mohammadsajjad679@gmail.com
 */

public class MyCartFragment extends Fragment implements MyCartCheckoutViewHolder.MyCartCheckOutClickListeners {

    private RecyclerView recyclerView;
    private MyCartAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_cart, container,false);
        prepareData();
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id._my_cart_recyclerView);
        adapter = new MyCartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.updateData(items);
    }

    private void prepareData() {
        items.addAll(MyCartTransformer.getMyCartItem());
        items.add(new MyCartCheckOutItem());
    }


    @Override
    public void onCheckOutClicked() {
        startActivity(new Intent(getContext(), CheckOutFromCartActivity.class));
    }
}
