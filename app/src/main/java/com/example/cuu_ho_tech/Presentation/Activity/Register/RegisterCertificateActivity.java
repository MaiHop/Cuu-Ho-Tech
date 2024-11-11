package com.example.cuu_ho_tech.Presentation.Activity.Register;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.databinding.ActivityRegisterCertificateBinding;

public class RegisterCertificateActivity extends AppCompatActivity {
    ActivityRegisterCertificateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterCertificateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {

    }
}
