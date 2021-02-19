package com.farmers.buyers.modules.addMoney;

import com.farmers.buyers.core.ApiResponseCallback;
import com.farmers.buyers.core.BaseRepository;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.model.SendOtpApiModel;
import com.farmers.buyers.modules.signUp.model.SendOtpRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.signUp.model.SignUpRequestParams;
import com.farmers.buyers.modules.signUp.model.VerifyOtpApiModel;
import com.farmers.buyers.modules.signUp.model.VerifyOtpRequestParams;
import com.farmers.buyers.remote.ApiConstants;
import com.farmers.buyers.remote.RetrofitBuilder;

import retrofit2.Call;

public class AddMoneyRepository extends BaseRepository {

    public void addMoney(AddMoneyRequestParams params, ApiResponseCallback<SignUpApiModel> responseCallback) {
        Call<SignUpApiModel> call = RetrofitBuilder.createServiceContract().addMoney(
                ApiConstants.ADD_MONEY,
                params.getLoginId(),
                params.getWallet_amount(),
                params.getWallet_transation_id(),
                params.getWallet_transation_status(),
                params.getAuthKey()
        );
        makeRequest(call, responseCallback);
    }
}
