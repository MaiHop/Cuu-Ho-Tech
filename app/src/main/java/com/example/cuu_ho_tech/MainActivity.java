package com.example.cuu_ho_tech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cuu_ho_tech.Presentation.Activity.Intro.WelcomeActivity;
import com.example.cuu_ho_tech.Presentation.Activity.LoginActivity;
import com.example.cuu_ho_tech.databinding.ActivityLoadingBinding;

public class MainActivity extends AppCompatActivity {
    ActivityLoadingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_loading);
        EdgeToEdge.enable(this);
        initialize();
    }
    private void initialize() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean isFirstLogin = sharedPreferences.getBoolean("firstLogin", false);
        Handler handler = new Handler(Looper.getMainLooper());
        if(!isFirstLogin) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }
}