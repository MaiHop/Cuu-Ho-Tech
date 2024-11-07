package com.example.cuu_ho_tech.Presentation.Activity.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityIntro2Binding;


public class Intro2Activity extends AppCompatActivity {
    ActivityIntro2Binding binding;
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityIntro2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro2Activity.this, Intro3Activity.class);
                startActivity(intent);
            }
        });
    }
}
