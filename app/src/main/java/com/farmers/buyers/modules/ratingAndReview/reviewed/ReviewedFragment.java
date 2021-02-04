package com.farmers.buyers.modules.ratingAndReview.reviewed;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.InboxTransformer;
import com.farmers.buyers.modules.inbox.adapter.NotificationListAdapter;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewedListAdapter;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewedFragment extends BaseFragment implements ReviewedListViewHolder.ReviewedItemClickListener {

    private RecyclerView rv_reviewed_list;
    private ReviewedListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();

    public ReviewedFragment get() {
        return new ReviewedFragment();
    }

    @Override
    public String getTitle() {
        return "Reviewed";
    }

    @Override
    public int getResourceFile() {
        return R.layout.fragment_reviewed;
    }

    @Override
    public void bindView(View view) {
        rv_reviewed_list = view.findViewById(R.id.rv_reviewed_list);
        adapter = new ReviewedListAdapter(this);
        rv_reviewed_list.setAdapter(adapter);
        rv_reviewed_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        getReviewed();
        adapter.updateData(items);
    }

    public void getReviewed() {
        items.addAll(ReviewTransfarmer.getReviewedList());
    }

    @Override
    public void onReviewedItemClicked(int position) {
        Toast.makeText(getActivity(), "Reviewed", Toast.LENGTH_SHORT).show();
    }
}