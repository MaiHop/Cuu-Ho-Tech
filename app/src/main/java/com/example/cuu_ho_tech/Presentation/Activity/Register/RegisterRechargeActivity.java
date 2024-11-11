package com.example.cuu_ho_tech.Presentation.Activity.Register;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Utils.DeviceUtils;
import com.example.cuu_ho_tech.databinding.ActivityRegisterRechargeBinding;

import java.text.DecimalFormat;

public class RegisterRechargeActivity  extends AppCompatActivity {
    ActivityRegisterRechargeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterRechargeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize() {
        binding.layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.edtMoney.clearFocus();
                    DeviceUtils.hideKeyboard(v, RegisterRechargeActivity.this);
                    v.performClick();
                    return true;
                }
                return false;
            }
        });
        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.edtMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Khi EditText có tiêu điểm, hiển thị giá trị không định dạng
                    String currentValue = binding.edtMoney.getText().toString().replace("đ", "").replace(".", "").trim();
                    binding.edtMoney.setText(currentValue); // Đặt lại giá trị không có định dạng
                    binding.edtMoney.setSelection(currentValue.length()); // Đặt con trỏ ở cuối
                    binding.edtMoney.setFilters(new InputFilter[] { new InputFilter.LengthFilter(9)});
                } else {
                    binding.edtMoney.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15)});
                    // Khi EditText mất tiêu điểm, định dạng giá trị
                    String currentValue = binding.edtMoney.getText().toString();
                    binding.edtMoney.setText(formatCurrency(currentValue)); // Định dạng lại và gán vào EditText
                }
            }
        });
    }

    private String formatCurrency(String value) {
        value = value.replaceAll("[^\\d]", "");
        long number = Long.parseLong(value);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number) + "đ";
    }
}