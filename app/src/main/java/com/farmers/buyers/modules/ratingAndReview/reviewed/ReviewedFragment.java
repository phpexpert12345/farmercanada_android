package com.farmers.buyers.modules.ratingAndReview.reviewed;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.inbox.InboxTransformer;
import com.farmers.buyers.modules.inbox.adapter.NotificationListAdapter;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewViewModel;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewedListAdapter;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewedListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewedFragment extends BaseFragment implements ReviewedListViewHolder.ReviewedItemClickListener {

    private RecyclerView rv_reviewed_list;
    private LinearLayout responseMessage;
    private ReviewListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private List<RecyclerViewListItem> filteredItem = new ArrayList<>();


    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RatingAndReviewViewModel.class)) {
                return (T) new RatingAndReviewViewModel();
            }
            return null;
        }
    };

    public RatingAndReviewViewModel viewModel = factory.create(RatingAndReviewViewModel.class);
    private MutableLiveData<DataFetchState<FarmReviewedListApiModel>> stateMachine = new MutableLiveData<>();

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
        responseMessage = view.findViewById(R.id.responseMessage);
        adapter = new ReviewListAdapter();
        rv_reviewed_list.setAdapter(adapter);
        rv_reviewed_list.setLayoutManager(new LinearLayoutManager(baseActivity));

        stateMachine.observe(this, reviewListResponseDataFetchState -> {
            switch (reviewListResponseDataFetchState.status) {
                case LOADING: loading();
                case SUCCESS: success();
                case ERROR:   error(reviewListResponseDataFetchState.status_message);
            }
        });

    }


    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        bindAdapter();
    }

    private void error(String error) {
        dismissLoader();
    }

    private void bindAdapter() {
        adapter.updateData(viewModel.items);
    }


    public void getReviewed() {
        viewModel.getFarmReviewedList(stateMachine);
    }

    @Override
    public void onReviewedItemClicked(int position) {
    }
}