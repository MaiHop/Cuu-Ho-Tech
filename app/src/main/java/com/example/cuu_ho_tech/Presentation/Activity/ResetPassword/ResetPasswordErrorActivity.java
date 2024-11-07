package com.example.cuu_ho_tech.Presentation.Activity.ResetPassword;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.databinding.ActivityResetPasswordErrorBinding;


public class ResetPasswordErrorActivity extends AppCompatActivity {
    ActivityResetPasswordErrorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordErrorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

    }
}