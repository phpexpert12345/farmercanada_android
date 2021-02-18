package com.farmers.buyers.modules.farmDetail;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.model.SingleTextItem;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailsAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsHeaderItems;
import com.farmers.buyers.modules.farmDetail.model.farmList.request.FarmProductListReq;
import com.farmers.buyers.modules.farmDetail.model.farmList.response.FarmListProductResponse;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderViewHolder;
import com.farmers.buyers.modules.home.models.farmList.FarmData;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.modules.home.view.HomeHeaderViewHolder;
import com.farmers.buyers.modules.signUp.SignUpViewModel;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.storage.Constant;

import java.util.ArrayList;
import java.util.List;

public class FarmDetailActivity extends BaseActivity implements HomeHeaderViewHolder.HeaderItemClickListener, FarmDetailHeaderViewHolder.FarmHeaderClickListener {
    private RecyclerView recyclerView;
    private FarmDetailsAdapter adapter;
    private List<RecyclerViewListItem> items = new ArrayList<>();
    private AppController appController = AppController.get();


    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(FarmDetailViewModel.class)) {
                return (T) new FarmDetailViewModel();
            }
            return null;
        }
    };

    private FarmDetailViewModel viewModel = factory.create(FarmDetailViewModel.class);
    private MutableLiveData<DataFetchState<FarmListProductResponse>> stateMachine = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_detail);

        prepareItems();
        Intent intent=getIntent();
        if (intent!=null){
            SubProductItemRecord farmListData=(SubProductItemRecord)intent.getSerializableExtra(Constant.SERIALIZABLE_INTENT);
        }
        init();
        getFarmProductDetail();
    }

    private void prepareItems() {
       // FarmDetailsHeaderItems h1=new FarmDetailsHeaderItems("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        items.add(FarmDetailTransformer.getHeaderItems());
        items.add(FarmDetailTransformer.getFarmDetailItems());

    /*    items.add(new SingleTextItem("Vegetables"));
        items.add(FarmDetailTransformer.getFarmDetailVegList());
        items.add(new SingleTextItem("Fruits"));
        items.add(FarmDetailTransformer.getFarmDetailFruitList());*/
    }
    private void init() {
        recyclerView = findViewById(R.id.farmers_detail_recyclerView);
        adapter = new FarmDetailsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateMachine.observe(this, new Observer<DataFetchState<FarmListProductResponse>>() {
            @Override
            public void onChanged(DataFetchState<FarmListProductResponse> response) {
                switch (response.status){
                    case SUCCESS:

                        for (int i=0;i<response.data.getData().getCategoryList().size();i++){
                            items.add(new SingleTextItem(response.data.getData().getCategoryList().get(i).getCategoryName()));
                          //  items.addAll(response.data.getData().getCategoryList().get(i).getSubProductItemsRecord());
                        }
                        adapter.updateData(items);

                        break;
                    case LOADING:

                        break;
                    case ERROR:

                        break;
                }
            }
        });
        adapter.updateData(items);


    }


    private void getFarmProductDetail(){
        FarmProductListReq farmProductListReq=new FarmProductListReq(appController.getAuthenticationKey(),"1");
        viewModel.getFarmProductList(stateMachine,farmProductListReq);
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    @Override
    public void onEditAddressClickListener(int position) {
    }

    @Override
    public void onBecomeSellerClicked() {

    }

    @Override
    public void onOnBackClickListener() {
        onBackPressed();
    }
}