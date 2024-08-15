package com.example.zti.presentation.launcher_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.zti.R;
import com.example.zti.presentation.auth_activity.AuthActivity;
import com.example.zti.presentation.auth_activity.fragments.login_fragment.LoginFragment;
import com.example.zti.presentation.main_activity.MainActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(LauncherActivity.this, AuthActivity.class));
            finish();
        }, 3000);
    }
}