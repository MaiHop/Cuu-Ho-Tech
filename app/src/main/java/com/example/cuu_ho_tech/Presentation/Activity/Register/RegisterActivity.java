package com.example.cuu_ho_tech.Presentation.Activity.Register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Activity.MainActivity;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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
                            showDialogSuccess();
                        }
                    };
                    handler.postDelayed(runnable, 5000);
                }
            }
        });
        binding.edtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtNameLayout.setError(null);
            }
        });
        binding.edtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtEmail.setError(null);
            }
        });
        binding.edtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPasswordLayout.setError(null);
            }
        });
        binding.edtPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPhoneLayout.setError(null);
            }
        });
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDialogSuccess() {
        CustomDialog dialog = new CustomDialog()
                .setType(CustomDialog.SUCCESS)
                .setTitle("Đăng ký thành công")
                .setText("Bạn đã là thành viên của Rescue")
                .setIconLoading(true);
        dialog.show(getSupportFragmentManager(), "CustomDialog");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
                finishAffinity();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 5000);
    }

    private boolean checkEdtEmpty() {
        boolean isValid  = true;
        if(binding.edtName.getText().toString().isEmpty()) {
            binding.edtNameLayout.setError("Tên không được để trống!");
            binding.edtName.clearFocus();
            isValid = false;
        }
        if(binding.edtEmail.getText().toString().isEmpty()) {
            binding.edtEmailLayout.setError("Email không được để trống!");
            binding.edtEmail.clearFocus();
            isValid = false;
        }
        if(binding.edtPhone.getText().toString().isEmpty()) {
            binding.edtPhoneLayout.setError("Số điện thoại không được để trống!");
            binding.edtPhone.clearFocus();
            isValid = false;
        }
        if(binding.edtPassword.getText().toString().isEmpty()) {
            binding.edtPasswordLayout.setError("Password không được để trống!");
            binding.edtPassword.clearFocus();
            isValid = false;
        }
        return isValid;
    }
}