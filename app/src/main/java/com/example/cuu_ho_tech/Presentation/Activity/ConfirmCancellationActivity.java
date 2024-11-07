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
import com.example.cuu_ho_tech.databinding.ActivityRequestDetailBinding;

public class ConfirmCancellationActivity extends AppCompatActivity {
    private static final int REQUEST_CAL_CODE = 101;

    ActivityConfirmCancellationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmCancellationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Set trang thái unable
        setSwipeState(false);

        checkInternet();

        //Nút back
        Method4UI.hidehint(binding.edtRequestDetailDescription, binding.tilRequestDetailDescription,"Lý do hủy đơn");
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnRequestDetailCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCallPermission();
                setSwipeState(true);
            }
        });

        //Kéo xác nhận Hủy
        binding.swipeNoStateRequestDetailConfirmCancel.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                Method4UI method = new Method4UI();
                method.addField(binding.edtRequestDetailDescription,"Lý do không được để trống");

                if(method.validate()){
                    Intent i = new Intent(ConfirmCancellationActivity.this, RequestDetailActivity.class);
                    i.putExtra("cancel_request", true);
                    i.putExtra("cancel_description", binding.edtRequestDetailDescription.getText().toString());
                    startActivity(i);
                }else {
                    binding.swipeNoStateRequestDetailConfirmCancel.collapseButton();
                }
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
        if (CheckNetwork.isAvailable(ConfirmCancellationActivity.this)) {
            binding.svRequestCancel.setVisibility(View.VISIBLE);
            binding.swipeNoStateRequestDetailConfirmCancel.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
        } else {
            binding.svRequestCancel.setVisibility(View.GONE);
            binding.swipeNoStateRequestDetailConfirmCancel.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private void handleCallPermission() {
        String phoneNumber = binding.tvConfirmCancellationTechPhone.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // Nếu quyền đã được cấp, thực hiện cuộc gọi
            startActivity(callIntent);
        } else {
            // Yêu cầu quyền CALL_PHONE nếu chưa được cấp
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CAL_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAL_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Nếu quyền được cấp, thực hiện cuộc gọi
                handleCallPermission();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CAL_CODE);
            }
        }
    }

    private void setSwipeState(boolean state) {
        binding.swipeNoStateRequestDetailConfirmCancel.setEnabled(state);
        binding.swipeNoStateRequestDetailConfirmCancel.setActivated(state);
        if(state){
            binding.swipeNoStateRequestDetailConfirmCancel.setBackgroundResource(R.drawable.shape_squared);
        }else {
            binding.swipeNoStateRequestDetailConfirmCancel.setBackgroundResource(R.drawable.background_disable_swipe);
        }

    }
}