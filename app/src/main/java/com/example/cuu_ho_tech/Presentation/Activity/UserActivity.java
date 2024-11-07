package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityPaymentMethodBinding;
import com.example.cuu_ho_tech.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    ActivityUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.llUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, DetailUserActivity.class);
                startActivity(intent);
            }
        });
        binding.llPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, PaymentMethodActivity.class);
                startActivity(intent);
            }
        });
    }
}