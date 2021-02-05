package com.farmers.buyers.modules.ratingAndReview.review;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.address.AddNewAddressActivity;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends BaseFragment implements ReviewListViewHolder.ReviewItemClickListener {

    private RecyclerView rv_review_list;
    private ReviewListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public ReviewFragment get() {
        return new ReviewFragment();
    }

    public ReviewFragment() {
    }

    @Override
    public String getTitle() {
        return "Review";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_review;
    }

    @Override
    public void bindView(View view) {

        rv_review_list = view.findViewById(R.id.rv_review_list);
        adapter = new ReviewListAdapter(this);
        rv_review_list.setAdapter(adapter);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        prepareItems();
        adapter.updateData(items);
    }

    private void prepareItems() {
        items.addAll(ReviewTransfarmer.getReviewList());
    }

    public void getReview() {
        items.addAll(ReviewTransfarmer.getReviewList());
        Log.e("review", "review");
    }

    @Override
    public void onReviewItemClicked(int position) {
        startActivity(new Intent(getContext(), AddNewAddressActivity.class));
    }
}