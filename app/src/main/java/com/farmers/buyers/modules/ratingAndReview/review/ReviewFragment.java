package com.farmers.buyers.modules.ratingAndReview.review;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.BaseFragment;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewViewModel;
import com.farmers.buyers.modules.ratingAndReview.ReviewTransfarmer;
import com.farmers.buyers.modules.ratingAndReview.adapter.ReviewListAdapter;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewListResponse;
import com.farmers.buyers.modules.ratingAndReview.model.reviewAndRating.ReviewdListParams;
import com.farmers.buyers.modules.ratingAndReview.view.ReviewListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends BaseFragment implements ReviewListViewHolder.ReviewItemClickListener {

    private RecyclerView rv_review_list;
    private ReviewListAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
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
    private MutableLiveData<DataFetchState<ReviewListResponse>> stateMachine = new MutableLiveData<>();


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
       // prepareItems();

        ReviewdListParams reviewdListParams=new ReviewdListParams(appController.getAuthenticationKey(),"22");
        viewModel.getReview(stateMachine,reviewdListParams);

        stateMachine.observe(this,new Observer<DataFetchState<ReviewListResponse>>(){
            @Override
            public void onChanged(DataFetchState<ReviewListResponse> response) {
                switch (response.status){
                    case ERROR:
                        dismissLoader();
                        Toast.makeText(getActivity(), response.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        dismissLoader();
                        items.addAll(response.data.getData().getReviewData());
                        adapter.updateData(items);
                        Toast.makeText(getActivity(), response.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        //showLoader();
                        break;

                }
            }
        });
    }

    private void prepareItems() {
      //  items.addAll(ReviewTransfarmer.getReviewList());
    }

    public void getReview() {
        //items.addAll(ReviewTransfarmer.getReviewList());
    }

    @Override
    public void onReviewItemClicked(int position) {
    }
}