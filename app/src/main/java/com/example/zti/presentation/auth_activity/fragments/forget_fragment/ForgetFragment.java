package com.example.zti.presentation.auth_activity.fragments.forget_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zti.R;
import com.example.zti.presentation.auth_activity.fragments.reset_password.ResetFragment;

public class ForgetFragment extends Fragment {
    Button mForgetBtn;
    TextView mLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
        mForgetBtn = view.findViewById(R.id.forget_btn);
        mLogin = view.findViewById(R.id.login);
        mForgetBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_forgetFragment_to_resetFragment);
        });
        mLogin.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        return view;
    }
}