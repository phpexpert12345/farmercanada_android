package com.farmers.buyers.modules.ratingAndReview.customerReview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.ratingAndReview.RatingAndReviewViewModel;
import com.farmers.buyers.modules.ratingAndReview.customerReview.model.CustomerReviewListApiModel;

public class CustomerReviewListActivity extends BaseActivity {
    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(CustomerReviewListViewModel.class)) {
                return (T) new CustomerReviewListViewModel();
            }
            return null;
        }
    };
    private CustomerReviewListViewModel viewModel = factory.create(CustomerReviewListViewModel.class);
    private MutableLiveData<DataFetchState<CustomerReviewListApiModel>> stateMachine = new MutableLiveData<>();
    private CustomerReviewListAdapter adapter = new CustomerReviewListAdapter();
    private RecyclerView recyclerView;
    private LinearLayout reviewErrorLL;
    private TextView errorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review_list);
        setupToolbar(new ToolbarConfig("Review", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        }, false, null));

        init();
        getCustomerReviewList();
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }

    private void init() {
        recyclerView = findViewById(R.id.customer_review_recyclerView);
        reviewErrorLL = findViewById(R.id.customer_review_error_ll);
        errorTv = findViewById(R.id.customer_review_error_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        stateMachine.observe(this, customerReviewListApiModelDataFetchState -> {
            switch (customerReviewListApiModelDataFetchState.status) {
                case LOADING: loading(); break;
                case SUCCESS: success(); success();break;
                case ERROR:    error(customerReviewListApiModelDataFetchState.status_message); break;
            }
        });
    }

    private void getCustomerReviewList() {
        viewModel.getCustomerReviewList(stateMachine);
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        recyclerView.setVisibility(View.VISIBLE);
        reviewErrorLL.setVisibility(View.GONE);
        bindAdapter();
    }

    private void error(String error) {
        dismissLoader();
        recyclerView.setVisibility(View.GONE);
        reviewErrorLL.setVisibility(View.VISIBLE);
        errorTv.setText(error);
    }

    private void bindAdapter(){
        adapter.updateData(viewModel.items);
    }
}