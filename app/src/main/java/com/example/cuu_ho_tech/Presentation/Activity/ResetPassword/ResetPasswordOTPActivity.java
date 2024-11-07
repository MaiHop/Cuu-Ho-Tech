package com.example.cuu_ho_tech.Presentation.Activity.ResetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.Utils.DeviceUtils;
import com.example.cuu_ho_tech.databinding.ActivityResetPasswordOtpBinding;


public class ResetPasswordOTPActivity extends AppCompatActivity {
    ActivityResetPasswordOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        binding.otp1.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                if (s.length() > 1) {
                    isUpdating = true;
                    binding.otp1.setText(String.valueOf(s.charAt(1)));
                    binding.otp1.setSelection(1);
                    binding.otp2.requestFocus();
                    isUpdating = false;
                } else if (s.length() == 1) {
                    binding.otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) binding.otp1.setActivated(false);
            }
        });

        binding.otp2.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                if (s.length() > 1) {
                    isUpdating = true;
                    binding.otp2.setText(String.valueOf(s.charAt(1)));
                    binding.otp2.setSelection(1);
                    binding.otp3.requestFocus();
                    isUpdating = false;
                } else if (s.length() == 1) {
                    binding.otp3.requestFocus();
                } else {
                    binding.otp1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) binding.otp2.setActivated(false);
            }
        });

        binding.otp3.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                if (s.length() > 1) {
                    isUpdating = true;
                    binding.otp3.setText(String.valueOf(s.charAt(1)));
                    binding.otp3.setSelection(1);
                    binding.otp4.requestFocus();
                    isUpdating = false;
                } else if (s.length() == 1) {
                    binding.otp4.requestFocus();
                } else {
                    binding.otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) binding.otp3.setActivated(false);
            }
        });

        binding.otp4.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;
                if (s.length() > 1) {
                    isUpdating = true;
                    binding.otp4.setText(String.valueOf(s.charAt(1)));
                    binding.otp4.setSelection(1);
                    isUpdating = false;
                } else if (s.length() != 1) {
                    binding.otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) binding.otp4.setActivated(false);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(checkEdtEmpty()) {
                    CustomDialog customDialog = new CustomDialog().setLoading(true);
                    customDialog.show(getSupportFragmentManager(), "dialog");
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            customDialog.cancel();
                            Intent intent = new Intent(ResetPasswordOTPActivity.this, ResetPasswordNewActivity.class);
                            startActivity(intent);
                        }
                    };
                    handler.postDelayed(runnable, 5000);
                } else {
                    DeviceUtils.vibratePhone(ResetPasswordOTPActivity.this);
                    DeviceUtils.shakeScreen(binding.getRoot());
                }
            }
        });
    }

    private boolean checkEdtEmpty() {
        boolean isValid  = true;
        if(binding.otp1.getText().toString().isEmpty()) {
            binding.otp1.setActivated(true);
            binding.otp1.clearFocus();
            isValid = false;
        }
        if(binding.otp2.getText().toString().isEmpty()) {
            binding.otp2.setActivated(true);
            binding.otp2.clearFocus();
            isValid = false;
        }
        if(binding.otp3.getText().toString().isEmpty()) {
            binding.otp3.setActivated(true);
            binding.otp3.clearFocus();
            isValid = false;
        }
        if(binding.otp4.getText().toString().isEmpty()) {
            binding.otp4.setActivated(true);
            binding.otp4.clearFocus();
            isValid = false;
        }
        return isValid;
    }
}