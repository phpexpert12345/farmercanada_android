package com.farmers.buyers.modules.profile.editProfile;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class EditProfileRepository extends BaseRepository {

    public void editProfile(AddMoneyRequestParams params, ApiResponseCallback<SignUpApiModel> responseCallback) {
        File file = null;
        String file_name="";
if(!params.getWallet_transation_status().equalsIgnoreCase("")){
     file=new File(params.getWallet_transation_status());
}

if(file!=null){
    if(file.exists()) {
        file_name = file.getName();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("account_photo", file_name, requestBody);

        Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().editProfile(
                ApiConstants.EDIT_PROFILE,
                RequestBody.create(MediaType.parse("text/plain"), params.getLoginId()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAccount_name()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAccount_email()),
                body,
                RequestBody.create(MediaType.parse("text/plain"), params.getAuthKey()));
        makeRequest(call, responseCallback);
    }
}
else{
    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "");
    MultipartBody.Part body = MultipartBody.Part.createFormData("account_photo", file_name, requestBody);

    Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().editProfile(
            ApiConstants.EDIT_PROFILE,
            RequestBody.create(MediaType.parse("text/plain"), params.getLoginId()),
            RequestBody.create(MediaType.parse("text/plain"), params.getAccount_name()),
            RequestBody.create(MediaType.parse("text/plain"), params.getAccount_email()),
            body,
            RequestBody.create(MediaType.parse("text/plain"), params.getAuthKey()));
    makeRequest(call, responseCallback);
}

    }
}
