package com.example.zti.presentation.main_activity.fragment.dashboard_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.zti.R;
import com.example.zti.databinding.FragmentDashBoardBinding;
import com.example.zti.model.network.response.CBalanceResponse;
import com.example.zti.model.network.service.CBalanceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class DashBoardFragment extends Fragment {
    Retrofit mRetrofit;

    FragmentDashBoardBinding mBinding;
    SharedPreferences mSharedPreferences;
    CBalanceService mCBalanceService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentDashBoardBinding.inflate(inflater);
        mSharedPreferences = requireContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        mBinding.jacky.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_userProfileFragment);
        });
        mBinding.stats.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_statisticFragment);
        });
        mBinding.dailyTask.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_dailyTaskFragment);
        });
        mBinding.deposit.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_depositFragment);
        });
        mBinding.bell.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_notificationFragment);
        });
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://testing.zeeenterprises.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCBalanceService = mRetrofit.create(CBalanceService.class);

        sendRequestToApi(
                "ec6734cde10a5a7615c8cbbb40f0f8ae19019245",
                mSharedPreferences.getString("id",null)
        );

        return mBinding.getRoot();
    }

    public void sendRequestToApi(String apiKey, String userUid) {

        mCBalanceService.balance(apiKey, userUid)
                .enqueue(new Callback<CBalanceResponse>() {
                    @Override
                    public void onResponse(Call<CBalanceResponse> call, Response<CBalanceResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Invalid Response", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.body().state.equals("OK")) {
                                int a = response.body().data.userBalance.dailyTaskProfit;
                                int b = response.body().data.userBalance.referralProfit;
                                int c = a + b;
                                mBinding.rupees.setText(c+"");
                            } else if (response.body().data.exceptions.missingParam.isEmpty()) {
                                Toast.makeText(requireContext(), "User is Missing", Toast.LENGTH_SHORT).show();

                            }else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CBalanceResponse> call, Throwable t) {
                        Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}