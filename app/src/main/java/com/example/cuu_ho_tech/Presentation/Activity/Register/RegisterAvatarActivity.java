package com.example.cuu_ho_tech.Presentation.Activity.Register;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.databinding.ActivityRegisterAvatarBinding;

public class RegisterAvatarActivity extends AppCompatActivity {
    ActivityRegisterAvatarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterAvatarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize() {

    }
}