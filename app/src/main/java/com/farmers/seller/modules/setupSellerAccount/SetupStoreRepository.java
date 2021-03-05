package com.farmers.seller.modules.setupSellerAccount;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreRequestParams;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 05-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreRepository extends BaseRepository {

    public void doStoreSetup(SetupStoreRequestParams params, ApiResponseCallback<SetupStoreApiModel> responseCallback){
        RequestBody logoBody = RequestBody.create(MediaType.parse("image/*"),params.getLogo());
                RequestBody doc1Body = RequestBody.create(MediaType.parse("image/*"),params.getDoc1());
                RequestBody doc2Body = RequestBody.create(MediaType.parse("image/*"),params.getDoc2());
                RequestBody doc3Body = RequestBody.create(MediaType.parse("image/*"),params.getDoc3());
                RequestBody doc4Body = RequestBody.create(MediaType.parse("image/*"),params.getDoc4());


        Call<SetupStoreApiModel> call = RetrofitBuilder.createServiceContract().doSetupStore(ApiConstants.SETUP_STORE,
                RequestBody.create(MediaType.parse("text/plain"), params.getStoreType()),
                RequestBody.create(MediaType.parse("text/plain"), params.getStoreName()),
                RequestBody.create(MediaType.parse("text/plain"), params.getStoreAddress()),
                RequestBody.create(MediaType.parse("text/plain"), params.getCity()),
                RequestBody.create(MediaType.parse("text/plain"), params.getState()),
                RequestBody.create(MediaType.parse("text/plain"), params.getCountry()),
                RequestBody.create(MediaType.parse("text/plain"), params.getPostCode()),
                RequestBody.create(MediaType.parse("text/plain"), params.getOrderType()),
                RequestBody.create(MediaType.parse("text/plain"), params.getPickUpMinAmount()),
                RequestBody.create(MediaType.parse("text/plain"), params.getPickUpMsg()),
                RequestBody.create(MediaType.parse("text/plain"), params.getDeliveryMapLocationArea()),
                RequestBody.create(MediaType.parse("text/plain"), params.getRadius()),
                RequestBody.create(MediaType.parse("text/plain"), params.getDeliveryCharge()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAdditionalCharge()),
                RequestBody.create(MediaType.parse("text/plain"), params.getDeliveryCharge()),
                RequestBody.create(MediaType.parse("text/plain"), params.getDeliveryMsg()),
                RequestBody.create(MediaType.parse("text/plain"), params.getCompanyType()),
                RequestBody.create(MediaType.parse("text/plain"), params.getDocType()),
                RequestBody.create(MediaType.parse("text/plain"), params.getLoginId()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAuthKey()),
                MultipartBody.Part.createFormData("store_logo","Image.png",logoBody),
                MultipartBody.Part.createFormData("document_type_1_front_photo","Image.png",doc1Body),
                MultipartBody.Part.createFormData("document_type_1_back_photo","Image.png",doc2Body),
                MultipartBody.Part.createFormData("document_type_2_front_photo","Image.png",doc3Body),
                MultipartBody.Part.createFormData("document_type_2_back_photo","Image.png",doc4Body));

        makeRequest(call, responseCallback);
    }
}
