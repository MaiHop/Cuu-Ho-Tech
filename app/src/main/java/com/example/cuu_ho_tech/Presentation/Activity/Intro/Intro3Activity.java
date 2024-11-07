package com.example.cuu_ho_tech.Presentation.Activity.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Activity.LoginActivity;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityIntro3Binding;


public class Intro3Activity extends AppCompatActivity {
    ActivityIntro3Binding binding;
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityIntro3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstLogin", true);
                editor.apply();
                Intent intent = new Intent(Intro3Activity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}

