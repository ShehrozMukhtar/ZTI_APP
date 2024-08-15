package com.example.zti.model.network.service;

import com.example.zti.model.network.response.FetchNotificationResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FetchNotificationService {
    @FormUrlEncoded
    @POST("fetch_user_notifications.php")
    Call<FetchNotificationResponse> fetchNotification(
            @Field("__api_key__") String apiKey,
            @Field("user_uid") String userUid
    );
}
