package com.farmers.buyers.remote;

import com.farmers.buyers.modules.login.model.LoginApiModel;
import com.farmers.buyers.modules.login.model.LoginRequestParams;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * created by Mohammad Sajjad
 * on 29-01-2021 at 11:10
 * mohammadsajjad679@gmail.com
 */

public interface ApiController {


//    @POST("users/new")
//    void createUser(@Body User user, Callback<User> cb);}

//    @FormUrlEncoded
//    @POST("getdata.php")
//    fun getListOfImages(
//            @Field("user_id") userId: Int,
//            @Field("offset") page: Int,
//            @Field("type") type: String
//    ): Call<ListOfImagesApiModel>

    @POST("api/user/authenticate.php")
    Call<LoginApiModel> getUserLogin(@Body LoginRequestParams params);

    @POST
    <C, T>void makeRequest(@Url String path,  @Body C params, Callback<T> response);


}