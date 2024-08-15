package com.example.zti.presentation.model.network.service;
import com.example.zti.presentation.model.network.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("login_user.php")
    Call<LoginResponse> login(
            @Field("__api_key__") String apiKey,
            @Field("email") String email,
            @Field("password") String Password
    );
}
