package com.farmers.buyers.modules.seller.addProduct;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductApiResponseModel;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class AddProductRepository extends BaseRepository {

    public void addProduct(AddProductRequestParams params, ApiResponseCallback<AddProductApiResponseModel> responseCallback) {

        RequestBody productImage = RequestBody.create(MediaType.parse("multipart/form-data"), params.getImage());
        MultipartBody.Part body = MultipartBody.Part.createFormData("product_images", params.getImage().getName(), productImage);


        Call<AddProductApiResponseModel> call = RetrofitBuilder.createServiceContract().addProduct(ApiConstants.ADD_PRODUCT,
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getProductName()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getQuantity()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getUnit()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getCategoryId()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getUnitPrice()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getSalesPrice()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getNote()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getFarmId()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getLoginId()),
                RequestBody.create(MediaType.parse("multipart/form-data"), params.getAuthKey()),
                body
        );
        makeRequest(call, responseCallback);
    }
}
