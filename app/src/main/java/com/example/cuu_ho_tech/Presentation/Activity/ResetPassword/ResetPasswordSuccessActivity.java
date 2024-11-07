package com.example.cuu_ho_tech.Presentation.Activity.ResetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Activity.MainActivity;
import com.example.cuu_ho_tech.databinding.ActivityResetPasswordSuccessBinding;


public class ResetPasswordSuccessActivity extends AppCompatActivity {
    ActivityResetPasswordSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordSuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
