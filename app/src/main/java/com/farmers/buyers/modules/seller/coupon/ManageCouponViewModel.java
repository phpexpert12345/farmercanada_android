package com.farmers.buyers.modules.seller.coupon;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListRequestParams;
import com.farmers.buyers.remote.StandardError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 07-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class ManageCouponViewModel extends BaseViewModel {

    private ManageCouponRepository repository = new ManageCouponRepository();
    public List<RecyclerViewListItem> items = new ArrayList<>();
    private AppController appController = AppController.get();

    public void getCouponList(MutableLiveData<DataFetchState<CouponListApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.loading());

        CouponListRequestParams params = new CouponListRequestParams(appController.getAuthenticationKey(), appController.getFarmId(), appController.getLoginId() );

        repository.getCouponList(params, new ApiResponseCallback<CouponListApiModel>() {
            @Override
            public void onSuccess(CouponListApiModel response) {
                stateMachine.postValue(DataFetchState.success(response, ""));
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new CouponListApiModel()));
            }
        });
    }

}
