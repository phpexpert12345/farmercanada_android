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
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewedListAdapter;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewedListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewedFragment extends BaseFragment implements ReviewedListViewHolder.ReviewedItemClickListener {

    private RecyclerView rv_reviewed_list;
    private LinearLayout responseMessage;
    private ReviewedListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private List<RecyclerViewListItem> filteredItem = new ArrayList<>();


    private AppController appController = AppController.get();

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
    private MutableLiveData<DataFetchState<ReviewListResponse>> reviewMachine = new MutableLiveData<>();

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
        adapter = new ReviewedListAdapter(this);
        rv_reviewed_list.setAdapter(adapter);
        rv_reviewed_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        //getReviewed();

        ReviewdListParams reviewdListParams = new ReviewdListParams(appController.getAuthenticationKey(), "22");
        viewModel.getReview(reviewMachine, reviewdListParams);

        reviewMachine.observe(this, new Observer<DataFetchState<ReviewListResponse>>() {
            @Override
            public void onChanged(DataFetchState<ReviewListResponse> response) {
                switch (response.status) {

                    case ERROR:
                        dismissLoader();
                        rv_reviewed_list.setVisibility(View.GONE);
                        responseMessage.setVisibility(View.VISIBLE);
                        //  Toast.makeText(getActivity(), response.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        responseMessage.setVisibility(View.VISIBLE);
                        rv_reviewed_list.setVisibility(View.GONE);
                        responseMessage.setVisibility(View.VISIBLE);
                        dismissLoader();
                        for (int i = 0; response.data.getData().getReviewData().size() > i; i++) {
                            if (response.data.getData().getReviewData().get(i).getReviewId().equals(appController.getLoginId())) {
                                filteredItem.add(response.data.getData().getReviewData().get(i));
                            }
                        }

                        if (filteredItem.size() > 0) {
                            rv_reviewed_list.setVisibility(View.VISIBLE);
                            responseMessage.setVisibility(View.GONE);
                        } else {
                            responseMessage.setVisibility(View.VISIBLE);
                        }

                        items.addAll(filteredItem);
                        adapter.updateData(items);

                        break;
                    case LOADING:
                        showLoader();
                        break;
                }
            }
        });
        adapter.updateData(items);
    }

    public void getReviewed() {
        //  items.addAll(ReviewTransfarmer.getReviewedList());
    }

    @Override
    public void onReviewedItemClicked(int position) {
    }
}