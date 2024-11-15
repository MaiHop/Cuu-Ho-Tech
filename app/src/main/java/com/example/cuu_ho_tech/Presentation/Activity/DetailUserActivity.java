package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityDetailServiceBinding;
import com.example.cuu_ho_tech.databinding.ActivityDetailUserBinding;
import com.example.cuu_ho_tech.databinding.ActivityUserBinding;

public class DetailUserActivity extends AppCompatActivity {
    ActivityDetailUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();

    }
    private void initialize() {
        binding.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEdtEmpty()) {
                    if (checkEmail(binding.edtEmail.getText().toString())){
                        CustomDialog customDialog = new CustomDialog().setLoading(true);
                        customDialog.show(getSupportFragmentManager(), "Dialog");
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                customDialog.cancel();
                                Intent intent = new Intent(DetailUserActivity.this, UserActivity.class);
                                startActivity(intent);
                                Toast.makeText(DetailUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        };
                        handler.postDelayed(runnable, 5000);
                    }
                }
            }
        });
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.edtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtNameLayout.setError(null);
            }
        });
        binding.edtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtEmailLayout.setError(null);
            }
        });
        binding.edtPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPhoneLayout.setError(null);
            }
        });
        binding.edtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.edtPhoneLayout.setError(null);
            }
        });
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
        if(binding.edtAddress.getText().toString().isEmpty()) {
            binding.edtAddressLayout.setError("Địa chỉ không được để trống!");
            binding.edtAddress.clearFocus();
            isValid = false;
        }
        return isValid;
    }
    private boolean checkEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern)) {
            binding.edtEmailLayout.setError("Email không đúng định dạng");
            binding.edtEmail.clearFocus();
            return false;
        } else {
            binding.edtEmailLayout.setError(null);  // Xóa lỗi nếu email hợp lệ
            return true;
        }
    }
}