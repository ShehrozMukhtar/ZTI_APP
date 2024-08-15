package com.example.zti.presentation.main_activity.fragment.deposit_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zti.R;
import com.example.zti.databinding.FragmentMakeDepositBinding;
public class MakeDepositFragment extends Fragment {

     FragmentMakeDepositBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentMakeDepositBinding.inflate(getLayoutInflater());

        mBinding.back.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        mBinding.nextBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_makeDepositFragment_to_transactionDepositFragment);
        });
        return mBinding.getRoot();
    }
}