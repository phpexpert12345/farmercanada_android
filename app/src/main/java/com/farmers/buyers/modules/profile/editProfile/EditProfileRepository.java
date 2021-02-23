package com.farmers.buyers.modules.profile.editProfile;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class EditProfileRepository extends BaseRepository {

    public void editProfile(AddMoneyRequestParams params, ApiResponseCallback<SignUpApiModel> responseCallback) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), params.getFile());
        MultipartBody.Part body = MultipartBody.Part.createFormData("account_photo", params.getFile().getName(), requestBody);

        RequestBody requestBodyCover = RequestBody.create(MediaType.parse("multipart/form-data"), params.getCoverFile());
        MultipartBody.Part bodyCover = MultipartBody.Part.createFormData("user_cover_image", params.getFile().getName(), requestBodyCover);

        Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().editProfile(
                ApiConstants.EDIT_PROFILE,
                RequestBody.create(MediaType.parse("text/plain"), params.getLoginId()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAccount_name()),
                RequestBody.create(MediaType.parse("text/plain"), params.getAccount_email()),
                body,
                bodyCover,
                RequestBody.create(MediaType.parse("text/plain"), params.getAuthKey()));
        makeRequest(call, responseCallback);
    }
}
