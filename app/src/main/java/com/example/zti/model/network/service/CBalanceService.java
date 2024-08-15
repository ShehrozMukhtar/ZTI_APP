package com.example.zti.model.network.service;

import com.example.zti.model.network.response.CBalanceResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CBalanceService {
    @FormUrlEncoded
    @POST("fetch_user_current_balance.php")
    Call<CBalanceResponse> balance(
            @Field("__api_key__") String apiKey,
            @Field("user_uid") String userUid
    );
}
