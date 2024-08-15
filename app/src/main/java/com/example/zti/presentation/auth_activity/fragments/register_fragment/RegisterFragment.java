package com.example.zti.presentation.auth_activity.fragments.register_fragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zti.R;
import com.example.zti.model.network.response.RegisterResponse;
import com.example.zti.model.network.service.RegisterService;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.io.File;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment {
    File mFile;
    EditText mName, mEmail, mPassword, mConfirmPassword, mVerificationCode;
    ImageView mEye, mConfirmEye;
    Boolean isPasswordVisible = false;
    EasyImage easyImage;
    CircleImageView profile;
    Button mRegisterBtn;
    Retrofit mRetrofit;
    RegisterService mRegisterService;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mName = view.findViewById(R.id.name);
        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        mConfirmPassword = view.findViewById(R.id.confirm_password);
        mEye = view.findViewById(R.id.eye);
        mConfirmEye = view.findViewById(R.id.c_eye);
        mRegisterBtn = view.findViewById(R.id.register_btn);
        profile = view.findViewById(R.id.user);
        mVerificationCode = view.findViewById(R.id.verify_code);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://zeeenterprises.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       mRegisterService = mRetrofit.create(RegisterService.class);
        //easyImage
        easyImage = new EasyImage.Builder(requireContext())
                .setCopyImagesToPublicGalleryFolder(false)
                .allowMultiple(false)
                .setFolderName("EasyImage")
                .build();
        profile.setOnClickListener(v -> {
            Dexter.withContext(requireContext())
                    .withPermissions(
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.CAMERA
                    )
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                            if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                easyImage.openCameraForImage(RegisterFragment.this);
                            } else {
                                Toast.makeText(requireContext(), "Give Permission to your Android phone", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }

                    })
                    .check();
        });

//endOfEasyImage
        mEye.setOnClickListener(v -> {
            if (isPasswordVisible) {
                mEye.setImageResource(R.drawable.ic_eye);
                mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//write 129 otherwise
                isPasswordVisible = false;
            } else {
                mPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                mEye.setImageResource(R.drawable.hide_eye);
                isPasswordVisible = true;
            }
            //isPasswordVisible = !isPasswordVisible;
        });
        mConfirmEye.setOnClickListener(v -> {
            if (isPasswordVisible) {
                mConfirmEye.setImageResource(R.drawable.ic_eye);
                mConfirmPassword.setInputType(129);
            } else {
                mConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                mConfirmEye.setImageResource(R.drawable.hide_eye);
            }
            isPasswordVisible = !isPasswordVisible;
        });

        mRegisterBtn.setOnClickListener(v -> {
           if(mName.getText().toString().trim().isEmpty()){
               mName.setError("Enter Name");
               mName.requestFocus();
           } else if (mEmail.getText().toString().trim().isEmpty()) {
               mEmail.setError("Enter Email");
               mEmail.requestFocus();
           } else if (mPassword.getText().toString().isEmpty()) {
               mPassword.setError("Enter Password");
               mPassword.requestFocus();
           } else {
                // Todo: Call Register User API
                registerUser(
                        "ec6734cde10a5a7615c8cbbb40f0f8ae19019245",
                        mName.getText().toString().trim(),
                        mEmail.getText().toString().trim(),
                        mPassword.getText().toString(),
                        mVerificationCode.getText().toString().trim(),
                        mFile,
                        view
                );
            }
        });
        return view;
    }

    //easyImage
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        easyImage.handleActivityResult(requestCode, resultCode, data, requireActivity(), new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(@NonNull Throwable throwable, @NonNull MediaSource mediaSource) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMediaFilesPicked(@NonNull MediaFile[] mediaFiles, @NonNull MediaSource mediaSource) {
                mFile = mediaFiles[0].getFile();
                Glide.with(profile).load(mFile).into(profile);
            }

            @Override
            public void onCanceled(@NonNull MediaSource mediaSource) {
                Toast.makeText(requireContext(), "Permission is Canceled", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void registerUser(String apiKey, String fullName, String email, String password, String verificationCode, File avatar, View view
    ){ mRegisterService.registerUser(
            MultiPartHelper.createRequestBody(apiKey),
            MultiPartHelper.createRequestBody(fullName),
            MultiPartHelper.createRequestBody(email),
            MultiPartHelper.createRequestBody(password),
            MultiPartHelper.createRequestBody(verificationCode),
            MultiPartHelper.createMultiPartBodyPartImage("avatar", avatar.getName(), avatar)
    ).enqueue(new Callback<RegisterResponse>() {
        @Override
        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
            Toast.makeText(requireContext(), "Response Arrived", Toast.LENGTH_SHORT).show();
            if (!response.isSuccessful()) {
                Toast.makeText(requireContext(), "Response is not Successful", Toast.LENGTH_SHORT).show();
            } else {
                if (response.body().state.equals("OK")) {
                    Toast.makeText(requireContext(), "You have been registered login now", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).popBackStack();
                }else if (response.body().state.equals("FAILURE")) {
                    if (response.body().data.exceptions.isUserAlreadyExist != null) {
                        Toast.makeText(requireContext(), "User already exist", Toast.LENGTH_SHORT).show();
                    }if (response.body().data.exceptions.missingParam.isEmpty()){
                        Toast.makeText(requireContext(), "Password,Avatar,VerifyCode is missing", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            t.printStackTrace();
            Toast.makeText(requireContext(), "Server fail", Toast.LENGTH_SHORT).show();
        }
    });

    }
}