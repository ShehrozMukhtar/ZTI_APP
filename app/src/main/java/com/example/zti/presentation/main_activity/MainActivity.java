package com.example.zti.presentation.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.zti.R;
import com.example.zti.presentation.auth_activity.AuthActivity;

public class MainActivity extends AppCompatActivity {

   NavController navController;
   NavGraph navGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.app_nav_host_fragment);
         navController = navHostFragment.getNavController();
         navGraph = navController.getNavInflater().inflate(R.navigation.app_nav_graph);
        navGraph.setStartDestination(R.id.dashBoardFragment);
        navController.setGraph(navGraph);

    }
    public void launchAuthActivity() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }
}