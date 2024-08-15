package com.example.zti.presentation.auth_activity.fragments.reset_password;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zti.R;

public class ResetFragment extends Fragment {
    Button mResetBtn;
    EditText mEmail , mPassword,mNewPass,mNewConfirmPass;
    TextView  mLogin;
    ImageView mEye1 ,mEye2,mEye3;
    boolean isPasswordVisible = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
        mEmail =view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        mNewPass = view.findViewById(R.id.new_password);
        mNewConfirmPass = view.findViewById(R.id.newConfirmPassword);
        mResetBtn = view.findViewById(R.id.reset_password_btn);
        mLogin = view.findViewById(R.id.login_btn);

        if(isPasswordVisible){

        }else {

        }

        return view;
    }
}