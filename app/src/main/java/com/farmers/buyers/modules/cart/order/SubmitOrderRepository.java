package com.farmers.buyers.modules.cart.order;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.address.MyAddressRequestParams;
import com.farmers.buyers.modules.address.model.AddressApiModel;
import com.farmers.buyers.modules.cart.myCart.model.applyCoupon.ApplyCouponResponse;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitRequestParam;
import com.farmers.buyers.modules.cart.order.model.submit.SubmitResponse;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

/**
 * Created by Ganesh ɐɯɹɐɥs on 2/19/2021.
 */
public class SubmitOrderRepository extends BaseRepository {

    public void submitOrder(SubmitRequestParam p, ApiResponseCallback<SubmitResponse> responseCallback) {
        Call<SubmitResponse> call = RetrofitBuilder.createServiceContract().SUBMIT_RESPONSE_CALL(ApiConstants.SUBMIT_ORDER_URL,
                p.getAuth_key(), p.getCustomer_long(), p.getCustomer_lat(), p.getCustomer_postcode(), p.getCustomer_city(),
                p.getCustomer_address(), p.getWalletPay(), p.getOrder_type(), p.getSpecialInstruction(), p.getDelivery_time(),
                p.getDelivery_date(), p.getDiscount_amount(), p.getCoupon_discount_amount(), p.getTotal_amount(), p.getDelivery_amount(),
                p.getService_tax_amount(), p.getGst_tax_amount(), p.getSubtotal(), p.getPayment_type(), p.getAddress_id(), p.getLoginId(), p.getInstructions(),
                p.getItem_unit_type(), p.getStrsizeid(), p.getPrice(), p.getQuantity(), p.getItemId(), p.getPayment_transaction_id(), p.getFarm_id());
        makeRequest(call, responseCallback);
    }

    public void getOrderDate(SubmitRequestParam params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().getDateData(
                ApiConstants.GET_DATE_DATA,
                params.getAuth_key(), params.getItemId());
        makeRequest(call, responseCallback);
    }

    public void getOrderTimeByDate(SubmitRequestParam params, ApiResponseCallback<AddressApiModel> responseCallback) {
        Call<AddressApiModel> call = RetrofitBuilder.createServiceContract().getOrderTimeByDate(
                ApiConstants.GET_TIME_BY_DATE_DATA,
                params.getAuth_key(),
                params.getItemId(),
                params.getFarm_id(),
                params.getCurrent_date());
        makeRequest(call, responseCallback);
    }
}
