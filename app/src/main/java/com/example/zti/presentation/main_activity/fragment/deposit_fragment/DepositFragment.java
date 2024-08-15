package com.example.zti.presentation.main_activity.fragment.deposit_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zti.R;

import com.example.zti.adapters.deposit.DepositItemAdapter;
import com.example.zti.adapters.deposit.DepositModel;
import com.example.zti.databinding.FragmentDepositBinding;
import com.example.zti.model.network.response.FetchUserDepositResponse;
import com.example.zti.model.network.service.FetchUserDepositService;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepositFragment extends Fragment {
    FragmentDepositBinding mBinding;
    FetchUserDepositService mFetchUserDepositService;
    Retrofit mRetrofit;
    SharedPreferences mSharedPreferences;
    List<FetchUserDepositResponse> fetchUserDepositResponses;
    DepositItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentDepositBinding.inflate(getLayoutInflater());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mSharedPreferences = requireContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://zeeenterprises.online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mFetchUserDepositService = mRetrofit.create(FetchUserDepositService.class);
        mBinding.plus.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_depositFragment_to_makeDepositFragment);
        });
        mBinding.backward.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        fetchUserDepositResponses = new ArrayList<>();
        for(int i = 1; i<=fetchUserDepositResponses.size(); i++) {
            fetchUserDepositResponses.get(i).data.userDeposits.get(i).accountHolderName.toString().trim();
        }

        adapter = new DepositItemAdapter(new ArrayList<>());
        mBinding.recyclerView.setAdapter(adapter) ;

        currentBalance("ec6734cde10a5a7615c8cbbb40f0f8ae19019245",
                mSharedPreferences.getString("id", null));

        return mBinding.getRoot();
    }

    public void currentBalance(String api, String userUid) {
        mFetchUserDepositService
                .userDeposit(api, userUid)
                .enqueue(new Callback<FetchUserDepositResponse>() {
                    @Override
                    public void onResponse(Call<FetchUserDepositResponse> call, Response<FetchUserDepositResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Invalid Response!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.body().state.equals("OK")) {
                                adapter.setDepositModels(response.body().data.userDeposits);
                            } else if (response.body().data.exceptions.missingParam.isEmpty()) {
                                Toast.makeText(requireContext(), "Some Parameter are Missing", Toast.LENGTH_SHORT).show();
                            } else if (response.body().data.exceptions.noDepositTransactionFound) {
                                Toast.makeText(requireContext(), "NO Deposit are Found", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FetchUserDepositResponse> call, Throwable t) {
                        Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}