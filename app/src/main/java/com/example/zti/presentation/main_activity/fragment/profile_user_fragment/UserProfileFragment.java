package com.example.zti.presentation.main_activity.fragment.profile_user_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zti.R;
import com.example.zti.databinding.FragmentUserProfileBinding;
import com.example.zti.presentation.auth_activity.fragments.register_fragment.RegisterFragment;
import com.example.zti.presentation.main_activity.MainActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class UserProfileFragment extends Fragment {
    FragmentUserProfileBinding mBinding;
    SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mBinding = FragmentUserProfileBinding.inflate(getLayoutInflater());
        mSharedPreferences = requireContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        mBinding.backward.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        mBinding.logoutBtn.setOnClickListener(v -> {
          mSharedPreferences
                  .edit()
                  .remove("email")
                  .remove("name")
                  .remove("id")
                  .apply();
            ((MainActivity) requireActivity()).launchAuthActivity();
        });

        String name = mSharedPreferences.getString("name",null);
        String email = mSharedPreferences.getString("email",null);
        String referral = mSharedPreferences.getString("referral_no",null);
        String avatar = mSharedPreferences.getString("avatar",null);

        mBinding.name.setText(name);
        mBinding.email.setText(email);
        mBinding.referralNo.setText(referral);
       Glide.with(requireContext())
                       .load(avatar)
                       .into(mBinding.profile1);

       Glide.with(requireContext())
                       .load("https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg?cs=srgb&dl=pexels-pixabay-60597.jpg&fm=jpg")
                               .into(mBinding.profile2);
       Glide.with(requireContext())
                       .load("https://w.forfun.com/fetch/83/83b001d629f121eea6797b62cdcb4c68.jpeg")
                               .into(mBinding.profile3);


      Glide.with(requireContext())
              .load("https://img.freepik.com/free-vector/night-ocean-landscape-full-moon-stars-shine_107791-7397.jpg")
              .into(mBinding.profile4);

      Glide.with(requireContext())
              .load("https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__340.jpg")
              .into(mBinding.profile5);



        return mBinding.getRoot();
    }
}