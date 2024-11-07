package com.example.cuu_ho_tech.Presentation.Activity.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Activity.LoginActivity;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityLoadingBinding;


public class LoadingActivity extends AppCompatActivity {
    ActivityLoadingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
                    Intent intent = new Intent(LoadingActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }
}
