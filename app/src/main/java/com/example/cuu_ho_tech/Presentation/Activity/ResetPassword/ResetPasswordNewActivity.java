package com.example.cuu_ho_tech.Presentation.Activity.ResetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.databinding.ActivityResetPasswordNewBinding;


public class ResetPasswordNewActivity extends AppCompatActivity {
    ActivityResetPasswordNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEdtEmpty()) {
                    CustomDialog customDialog = new CustomDialog().setLoading(true);
                    customDialog.show(getSupportFragmentManager(), "dialog");
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            customDialog.cancel();
                            Intent intent = new Intent(ResetPasswordNewActivity.this, ResetPasswordSuccessActivity.class);
                            startActivity(intent);
                        }
                    };
                    handler.postDelayed(runnable, 5000);
                }
            }
        });
        binding.edtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPasswordLayout.setError(null);
            }
        });
        binding.edtCheckedPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtCheckedPasswordLayout.setError(null);
            }
        });
    }

    private boolean checkEdtEmpty() {
        String textPassword = binding.edtPassword.getText().toString();
        String textCheckedPassword = binding.edtCheckedPassword.getText().toString();
        boolean isValid  = true;
        if(textPassword.isEmpty()) {
            binding.edtPasswordLayout.setError("Mật khẩu không được để trống!");
            binding.edtPassword.clearFocus();
            isValid = false;
        }
        if(textCheckedPassword.isEmpty()) {
            binding.edtCheckedPasswordLayout.setError("Mật khẩu không được để trống!");
            binding.edtCheckedPassword.clearFocus();
            isValid = false;
        }
        if(textPassword.equals(textCheckedPassword)) {
            binding.edtCheckedPasswordLayout.setError("Mật khẩu không giống nhau ");
            binding.edtPassword.clearFocus();
            isValid = false;
        }
        return isValid;
    }
}