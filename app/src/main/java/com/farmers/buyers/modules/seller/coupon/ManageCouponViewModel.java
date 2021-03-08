package com.farmers.buyers.modules.seller.coupon;

import androidx.lifecycle.MutableLiveData;

import com.farmers.buyers.app.App;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseViewModel;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.coupon.list.ManageCouponTransformer;
import com.farmers.buyers.modules.seller.coupon.list.model.AddCouponApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListApiModel;
import com.farmers.buyers.modules.seller.coupon.list.model.CouponListRequestParams;
import com.farmers.buyers.modules.seller.coupon.list.model.ManageCouponItem;
import com.farmers.buyers.modules.seller.coupon.odel.EditCouponRequestParams;
import com.farmers.buyers.modules.seller.product.models.DeleteProductApiModel;
import com.farmers.buyers.modules.seller.product.models.DeleteProductRequestParams;
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
    public List<ManageCouponItem> couponList = new ArrayList<>();
    private AppController appController = AppController.get();
    public ManageCouponItem selectedItem = null;

    public void getCouponList(MutableLiveData<DataFetchState<CouponListApiModel>> stateMachine) {
        items.clear();
        stateMachine.postValue(DataFetchState.loading());

        CouponListRequestParams params = new CouponListRequestParams(appController.getAuthenticationKey(), appController.getFarmId(), appController.getLoginId() );

        repository.getCouponList(params, new ApiResponseCallback<CouponListApiModel>() {
            @Override
            public void onSuccess(CouponListApiModel response) {
                if (response.getStatus()) {
                    items.addAll(ManageCouponTransformer.getCoupons(response.getData().getCouponCodeListSeller()));
                    couponList.addAll(ManageCouponTransformer.getCoupons(response.getData().getCouponCodeListSeller()));
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new CouponListApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new CouponListApiModel()));
            }
        });
    }

    private void deleteCoupon(MutableLiveData<DataFetchState<DeleteProductApiModel>> stateMachine){
        stateMachine.postValue(DataFetchState.loading());

        DeleteProductRequestParams params = new DeleteProductRequestParams(selectedItem.getCouponId(), appController.getAuthenticationKey());

        repository.deleteCoupon(params, new ApiResponseCallback<DeleteProductApiModel>() {
            @Override
            public void onSuccess(DeleteProductApiModel response) {
                if (response.getStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new DeleteProductApiModel()));

                }
            }

            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new DeleteProductApiModel()));
            }
        });
    }

    public void editCoupon(MutableLiveData<DataFetchState<AddCouponApiModel>> stateMachine) {
        stateMachine.postValue(DataFetchState.loading());

        EditCouponRequestParams params = new EditCouponRequestParams("","","","","","","","","","", "", "");

        repository.editCoupon(params, new ApiResponseCallback<AddCouponApiModel>() {
            @Override
            public void onSuccess(AddCouponApiModel response) {
                if (response.getStatus()) {
                    stateMachine.postValue(DataFetchState.success(response, response.getStatusMessage()));
                }
                else {
                    stateMachine.postValue(DataFetchState.error(response.getStatusMessage(), new AddCouponApiModel()));
                }
            }
a
            @Override
            public void onFailure(StandardError standardError) {
                stateMachine.postValue(DataFetchState.error(standardError.getDisplayError(), new AddCouponApiModel()));
            }
        });

    }


    public void onDeleteCoupon(MutableLiveData<DataFetchState<DeleteProductApiModel>> stateMachine, int position) {
        selectedItem = couponList.get(position);
        deleteCoupon(stateMachine);
    }

}
