package com.example.zti.model.network.service;

import com.example.zti.model.network.response.FetchUserDepositResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FetchUserDepositService {
    @FormUrlEncoded
    @POST("fetch_user_deposits.php")
    Call<FetchUserDepositResponse> userDeposit(
            @Field("__api_key__") String apiKey,
            @Field("user_uid") String userUid
    );
}
