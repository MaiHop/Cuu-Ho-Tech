package com.example.cuu_ho_tech.Presentation.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cuu_ho_tech.Custom.SwipeBtn.OnActiveListener;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.ActivityConfirmCancellationBinding;
import com.example.cuu_ho_tech.databinding.ActivityConfirmPaidBinding;

public class ConfirmPaidActivity extends AppCompatActivity {

    ActivityConfirmPaidBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPaidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Set trang thái unable
        setSwipeState(true);

        checkInternet();

        //Nút back
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Kéo xác nhận Hủy
        binding.swipeNoStateRequestDetailConfirmCancel.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Intent i = new Intent(ConfirmPaidActivity.this, RequestDetailActivity.class);
                startActivity(i);
            }
        });

        //Load lại
        binding.llNoInternet.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternet();
            }
        });

    }

    private void checkInternet() {
        if (CheckNetwork.isAvailable(ConfirmPaidActivity.this)) {
            binding.svRequestCancel.setVisibility(View.VISIBLE);
            binding.swipeNoStateRequestDetailConfirmCancel.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
        } else {
            binding.svRequestCancel.setVisibility(View.GONE);
            binding.swipeNoStateRequestDetailConfirmCancel.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private void setSwipeState(boolean state) {
        binding.swipeNoStateRequestDetailConfirmCancel.setEnabled(state);
        binding.swipeNoStateRequestDetailConfirmCancel.setActivated(state);
        if (state) {
            binding.swipeNoStateRequestDetailConfirmCancel.setBackgroundResource(R.drawable.shape_squared);
        } else {
            binding.swipeNoStateRequestDetailConfirmCancel.setBackgroundResource(R.drawable.background_disable_swipe);
        }

    }
}