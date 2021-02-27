package com.farmers.buyers.modules.ratingAndReview.review;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewViewModel;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.model.FarmReviewListApiModel;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends BaseFragment {

    private RecyclerView rv_review_list;
    private ReviewListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private LinearLayout ll_data_not_available;
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
    private MutableLiveData<DataFetchState<FarmReviewListApiModel>> stateMachine = new MutableLiveData<>();
    private String farmId;

    public ReviewFragment(String farmId) {
        this.farmId = farmId;
    }


    public ReviewFragment get(String farmId) {
        return new ReviewFragment(farmId);
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
        ll_data_not_available = view.findViewById(R.id.ll_data_not_available);
        rv_review_list.setLayoutManager(new LinearLayoutManager(baseActivity));
        adapter = new ReviewListAdapter();
        rv_review_list.setAdapter(adapter);

        stateMachine.observe(this, new Observer<DataFetchState<FarmReviewListApiModel>>() {
            @Override
            public void onChanged(DataFetchState<FarmReviewListApiModel> farmReviewListApiModelDataFetchState) {
                switch (farmReviewListApiModelDataFetchState.status) {
                    case LOADING: loading(); break;
                    case SUCCESS: success(); break;
                    case ERROR:  error(farmReviewListApiModelDataFetchState.status_message);
                }
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

    public void getFarmReviewList() {
        viewModel.getFarmReviewList(stateMachine, SharedPreferenceManager.getInstance().getFarmId());
    }

}