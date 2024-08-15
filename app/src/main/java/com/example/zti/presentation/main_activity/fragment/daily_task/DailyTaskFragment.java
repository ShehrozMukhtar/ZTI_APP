package com.example.zti.presentation.main_activity.fragment.daily_task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zti.R;
import com.example.zti.databinding.FragmentDailyTaskBinding;

public class DailyTaskFragment extends Fragment {
   FragmentDailyTaskBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentDailyTaskBinding.inflate(getLayoutInflater());
        mBinding.backward.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        return mBinding.getRoot();
    }
}