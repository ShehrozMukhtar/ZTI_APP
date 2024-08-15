package com.example.zti.presentation.auth_activity.fragments.register_fragment;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultiPartHelper {
    public static RequestBody createRequestBody(String payload) {
        return RequestBody.create(payload, MediaType.parse("multipart/form-data"));

    }

    public static MultipartBody.Part createMultiPartBodyPartImage(String partKey, String imageName, File image) {
        RequestBody requestBody = RequestBody.create(image, MediaType.parse("image/*"));
        return MultipartBody.Part.createFormData(partKey, imageName, requestBody);
    }
}
