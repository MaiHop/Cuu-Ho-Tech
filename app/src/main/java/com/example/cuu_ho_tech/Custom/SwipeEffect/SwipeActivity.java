package com.example.cuu_ho_tech.Custom.SwipeEffect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cuu_ho_tech.Custom.SwipeBtn.SwipeButton;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.CustomSwipeBinding;

public class SwipeActivity extends AppCompatActivity {
    CustomSwipeBinding binding;
    private SwipeButton swipeBtnEnabled;
    private SwipeButton swipeNoState;
    private Button toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomSwipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        swipeBtnEnabled = binding.swipeBtnEnabled;
        swipeNoState = binding.swipeNoState;
        toggleBtn = binding.toggleBtn;

        swipeBtnEnabled.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_button2));
        swipeBtnEnabled.setSlidingButtonBackground(ContextCompat.getDrawable(this, R.drawable.shape_rounded2));
        swipeBtnEnabled.setOnStateChangeListener(active -> {
            Toast.makeText(SwipeActivity.this, "State: " + active, Toast.LENGTH_SHORT).show();
            if (active) {
                swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(SwipeActivity.this, R.drawable.shape_button));
            } else {
                swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(SwipeActivity.this, R.drawable.shape_button3));
            }
        });
        swipeBtnEnabled.setEnabledStateNotAnimated();
        swipeNoState.setOnActiveListener(() -> {
            Toast.makeText(SwipeActivity.this, "Active!", Toast.LENGTH_SHORT).show();
        });
        toggleBtn.setOnClickListener(view -> {
            if (!swipeBtnEnabled.isActive()) {
                swipeBtnEnabled.toggleState();
            }
        });
    }
}