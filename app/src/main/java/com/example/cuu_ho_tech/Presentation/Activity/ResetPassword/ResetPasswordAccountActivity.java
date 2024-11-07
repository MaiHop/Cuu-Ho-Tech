package com.example.cuu_ho_tech.Presentation.Activity.ResetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.databinding.ActivityResetPasswordAccountBinding;


public class ResetPasswordAccountActivity extends AppCompatActivity {
    ActivityResetPasswordAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkEdtEmpty()) {
                    CustomDialog customDialog = new CustomDialog().setLoading(true);
                    customDialog.show(getSupportFragmentManager(), "Dialog");
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            customDialog.cancel();
                            Intent intent = new Intent(ResetPasswordAccountActivity.this, ResetPasswordOTPActivity.class);
                            startActivity(intent);
                        }
                    };
                    handler.postDelayed(runnable, 5000);
                }
            }
        });
        binding.edtAccount.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtAccountLayout.setError(null);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean checkEdtEmpty() {
        if(binding.edtAccount.getText().toString().isEmpty()) {
            binding.edtAccountLayout.setError("Hãy điền đầy đủ thông tin!");
            binding.edtAccount.clearFocus();
            return false;
        }
        return true;
    }
}