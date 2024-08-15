package com.example.zti.presentation.model.network.service;
import com.example.zti.presentation.model.network.response.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegisterService {
    @Multipart
    @POST("register_user.php")
    Call<RegisterResponse> registerUser(
            @Part("__api_key__")RequestBody apiKey,
            @Part("full_name")RequestBody fullName,
            @Part("email")RequestBody email,
            @Part("password")RequestBody password,
            @Part("verification_code")RequestBody verificationCode,
            @Part MultipartBody.Part avatar  //avatar
            );
}
