package com.example.cuu_ho_tech.Presentation.Activity.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityIntro1Binding;


public class Intro1Activity extends AppCompatActivity {
    ActivityIntro1Binding binding;
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntro1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro1Activity.this, Intro2Activity.class);
                startActivity(intent);
            }
        });
    }
}
