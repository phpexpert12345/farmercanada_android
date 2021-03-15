package com.farmers.seller.modules.setupSellerAccount;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreRequestParams;
import com.google.android.gms.common.api.Api;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 05-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class SetupStoreRepository extends BaseRepository {

    public void doStoreSetup(SetupStoreRequestParams params, ApiResponseCallback<SetupStoreApiModel> responseCallback) {
        RequestBody logoBody;
        RequestBody getDoc1Body;
        RequestBody getDoc2Body;
        RequestBody getDoc3Body;
        RequestBody getDoc4Body;

        if (params.getLogo() != null) {
            logoBody = RequestBody.create(MediaType.parse("multipart/form-data"), params.getLogo());
        } else {
            logoBody = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc1() != null) {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc1());
        } else {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

        if (params.getDoc2() != null) {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc2());
        } else {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc3() != null) {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc3());
        } else {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc4() != null) {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc4());
        } else {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

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
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getLat())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getLng())),
                MultipartBody.Part.createFormData("store_logo", "Image.png", logoBody),
                MultipartBody.Part.createFormData("document_type_1_front_photo", "Image.png", getDoc1Body),
                MultipartBody.Part.createFormData("document_type_1_back_photo", "Image.png", getDoc2Body),
                MultipartBody.Part.createFormData("document_type_2_front_photo", "Image.png", getDoc3Body),
                MultipartBody.Part.createFormData("document_type_2_back_photo", "Image.png", getDoc4Body));

        makeRequest(call, responseCallback);
    }

    public void doStoreSetup2(SetupStoreRequestParams params, ApiResponseCallback<SetupStoreApiModel> responseCallback) {
        RequestBody logoBody;
        RequestBody getDoc1Body;
        RequestBody getDoc2Body;
        RequestBody getDoc3Body;
        RequestBody getDoc4Body;

        if (params.getLogo() != null) {
            logoBody = RequestBody.create(MediaType.parse("multipart/form-data"), params.getLogo());
        } else {
            logoBody = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc1() != null) {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc1());
        } else {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

        if (params.getDoc2() != null) {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc2());
        } else {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc3() != null) {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc3());
        } else {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc4() != null) {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc4());
        } else {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

        Call<SetupStoreApiModel> call = RetrofitBuilder.createServiceContract().doSetupStore2(
                ApiConstants.SETUP_STORE,
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
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getLat())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getLng())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getStore_type_farm())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getStore_type_local())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getPickup_available())),
                RequestBody.create(MediaType.parse("text/plain"), String.valueOf(params.getDelivery_available())),
                MultipartBody.Part.createFormData("store_logo", "Image.png", logoBody),
                MultipartBody.Part.createFormData("document_type_1_front_photo", "Image.png", getDoc1Body),
                MultipartBody.Part.createFormData("document_type_1_back_photo", "Image.png", getDoc2Body),
                MultipartBody.Part.createFormData("document_type_2_front_photo", "Image.png", getDoc3Body),
                MultipartBody.Part.createFormData("document_type_2_back_photo", "Image.png", getDoc4Body));

        makeRequest(call, responseCallback);
    }
    public void updateDocs(SetupStoreRequestParams params, ApiResponseCallback<SetupStoreApiModel> responseCallback){
        RequestBody getDoc1Body;
        RequestBody getDoc2Body;
        RequestBody getDoc3Body;
        RequestBody getDoc4Body;
        if (params.getDoc1() != null) {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc1());
        } else {
            getDoc1Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

        if (params.getDoc2() != null) {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc2());
        } else {
            getDoc2Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc3() != null) {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc3());
        } else {
            getDoc3Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }
        if (params.getDoc4() != null) {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), params.getDoc4());
        } else {
            getDoc4Body = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        }

        Call<SetupStoreApiModel> call = RetrofitBuilder.createServiceContract().UpdateDocs(ApiConstants.UPDATE_DOCS, MultipartBody.Part.createFormData("document_type_1_front_photo", "Image.png", getDoc1Body),
                MultipartBody.Part.createFormData("document_type_1_back_photo", "Image.png", getDoc2Body),
                MultipartBody.Part.createFormData("document_type_2_front_photo", "Image.png", getDoc3Body),
                MultipartBody.Part.createFormData("document_type_2_back_photo", "Image.png", getDoc4Body), RequestBody.create(MediaType.parse("text/plain"), params.getTxt_doc_1()),
                RequestBody.create(MediaType.parse("text/plain"), params.getTxt_doc_2()),
                RequestBody.create(MediaType.parse("text/plain"), params.getLoginId()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAuthKey()));
        makeRequest(call, responseCallback);
    }

}
