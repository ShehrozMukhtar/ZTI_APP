package com.example.zti.presentation.launcher_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.zti.R;
import com.example.zti.presentation.auth_activity.AuthActivity;
import com.example.zti.presentation.auth_activity.fragments.login_fragment.LoginFragment;
import com.example.zti.presentation.main_activity.MainActivity;

public class LauncherActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.slider));
        mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        if (mSharedPreferences.getString("email", null) != null) {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                finish();
            }, 3000);
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                startActivity(new Intent(LauncherActivity.this, AuthActivity.class));
                finish();
            }, 3000);
        }
    }
}
