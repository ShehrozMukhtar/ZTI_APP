package com.example.zti.presentation.auth_activity.fragments.login_fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.zti.R;
import com.example.zti.databinding.FragmentLoginBinding;
import com.example.zti.model.network.response.LoginResponse;
import com.example.zti.model.network.service.LoginService;
import com.example.zti.presentation.auth_activity.AuthActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class LoginFragment extends Fragment {

    FragmentLoginBinding mBinding;
    Retrofit mRetrofit;
    boolean isPasswordVisible = false;
    SharedPreferences mSharedPreferences;
    LoginService mLoginService;
    LottieAnimationView lottieAnimationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        mSharedPreferences = requireContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://testing.zeeenterprises.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mLoginService = mRetrofit.create(LoginService.class);
        mBinding.eye.setOnClickListener(v -> {
            mBinding.eye.setImageResource(isPasswordVisible ? R.drawable.hide_eye : R.drawable.ic_eye);
            mBinding.password.setInputType(isPasswordVisible ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
            isPasswordVisible = !isPasswordVisible;
        });
        mBinding.register.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
        });

        mBinding.forget.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetFragment);
        });
        mBinding.loginBtn.setOnClickListener(v -> {
            if (mBinding.emailLogin.getText().toString().trim().isEmpty()) {
                mBinding.emailLogin.setError("Enter Email");
                mBinding.emailLogin.requestFocus();
            } else if (mBinding.password.getText().toString().trim().isEmpty()) {
                mBinding.password.setError("Enter Password");
                mBinding.password.requestFocus();
            } else {
                sendRequestToUserApi(
                        "ec6734cde10a5a7615c8cbbb40f0f8ae19019245",
                        mBinding.emailLogin.getText().toString().trim(),
                        mBinding.password.getText().toString()
                );
            }
        });

        return mBinding.getRoot();
    }

    public void sendRequestToUserApi(String apiKey, String email, String password) {
        Log.d("CHECKING", "sendRequestToUserApi: " + apiKey + "," + email + "," + password + ".");
        Toast.makeText(requireContext(), "Signing IN...", Toast.LENGTH_SHORT).show();
        mLoginService
                .login(apiKey, email, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Invalid Response!", Toast.LENGTH_SHORT).show();
                        } else {
                            LoginResponse loginUser = response.body();
                            if (loginUser.state.equals("OK")) {
                                String avatar = loginUser.data.user.avatar;
                                avatar = avatar.replace("testing.","");
                                mSharedPreferences.edit()
                                        .putString("name", loginUser.data.user.fullName)
                                        .putString("email", loginUser.data.user.email)
                                        .putString("id",loginUser.data.user.uid)
                                        .putString("referral_no",loginUser.data.user.referralNo)
                                        .putString("avatar",avatar)
                                        .apply();
                                // Todo: Save User To Shared Preferences, and also check it in LauncherActivity if he/she detail is already saved in preferences, then don't take it to AuthActivity, take him/her direct to MainActivity.
                                ((AuthActivity) requireActivity()).launchMainActivity();
                            } else {
                                if (loginUser.data.exceptions.noUserFound != null) {
                                    Toast.makeText(requireContext(), "No User is Registered with that Email", Toast.LENGTH_SHORT).show();
                                }
                                if (loginUser.data.exceptions.invalidValueOfParam != null) {
                                    Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}