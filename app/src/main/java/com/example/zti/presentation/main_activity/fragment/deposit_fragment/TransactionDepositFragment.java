package com.example.zti.presentation.main_activity.fragment.deposit_fragment;

import android.content.Intent;
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
import com.example.zti.databinding.FragmentTransactionDepositBinding;
import com.example.zti.presentation.MyApp;
import com.example.zti.presentation.auth_activity.fragments.register_fragment.RegisterFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class TransactionDepositFragment extends Fragment {
     FragmentTransactionDepositBinding mBinding;
     EasyImage easyImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mBinding = FragmentTransactionDepositBinding.inflate(getLayoutInflater());

        mBinding.backward.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
        mBinding.choose.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_transactionDepositFragment_to_chooseBankFragment);
        });
        easyImage = new EasyImage.Builder(requireContext())
                .setCopyImagesToPublicGalleryFolder(false)
                .allowMultiple(false)
                .setFolderName("EasyImage")
                .build();
        mBinding.img.setOnClickListener(v -> {
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
                                easyImage.openCameraForImage(TransactionDepositFragment.this);
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
        mBinding.select.setOnClickListener(v -> {
            onResume();
        });
        return mBinding.getRoot();
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        easyImage.handleActivityResult(requestCode, resultCode, data, requireActivity(), new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(@NonNull Throwable throwable, @NonNull MediaSource mediaSource) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMediaFilesPicked(@NonNull MediaFile[] mediaFiles, @NonNull MediaSource mediaSource) {
                Glide.with(mBinding.img).load(mediaFiles[0].getFile()).into(mBinding.img);
            }

            @Override
            public void onCanceled(@NonNull MediaSource mediaSource) {
                Toast.makeText(requireContext(), "Permission is Canceled", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        String banksType =  ((MyApp)requireContext().getApplicationContext()).bankType ;
           if(banksType == null){
               mBinding.select.setText("Choose Bank");
           }else {
               mBinding.select.setText(banksType);
           }
    }
}