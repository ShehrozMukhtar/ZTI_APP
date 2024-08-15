package com.example.zti.presentation.auth_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.zti.R;
import com.example.zti.presentation.main_activity.MainActivity;

public class AuthActivity extends AppCompatActivity {
    NavController navController;
    NavGraph navGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.auth_nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.auth_nav_graph);
        navGraph.setStartDestination(R.id.loginFragment);
        navController.setGraph(navGraph);
    }

    public void launchMainActivity() {
        startActivity(new Intent(AuthActivity.this, MainActivity.class));
        finish();
    }

}
