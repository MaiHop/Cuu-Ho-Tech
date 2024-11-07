package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.DetailServiceAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.databinding.ActivityDetailServiceBinding;

public class DetailServiceActivity extends AppCompatActivity {
    ActivityDetailServiceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();
    }

    private void initialize() {
        listDetailServiceAdapter();
        binding.ivPackDetailAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnPrimaryOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog().setLoading(true);
                customDialog.show(getSupportFragmentManager(), "dialog");
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        customDialog.cancel();
                        CustomDialog dialog = new CustomDialog()
                                .setType(CustomDialog.SUCCESS)
                                .setTitle("Thêm thành công")
                                .setText("Bạn có thể xem dịch vụ được lưu của bạn tại  danh mục Hoạt động")
                                .setTextBtn("XEM DANH SÁCH")
                                .setTextBtnOutline("TRANG CHỦ")
                                .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
                                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                                    @Override
                                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                        btnOutline.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                    }
                                });
                        dialog.show(getSupportFragmentManager(), "CustomDialog");
                    }
                };
                handler.postDelayed(runnable, 5000);
            }
        });
        binding.btnPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog().setLoading(true);
                customDialog.show(getSupportFragmentManager(), "dialog");
                Intent intent = new Intent(DetailServiceActivity.this, OrderRescueActivity.class);
                startActivity(intent);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        customDialog.cancel();
                    }
                };
                handler.postDelayed(runnable, 2000);
            };
        });
    }

    private void listDetailServiceAdapter() {
        String[][] data = {
                {"", "Hùng Style", "11 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Đăng Mister", "12 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Dũng FC", "13 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Mai Hòa Hợp", "14 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Con Mèo", "15 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Con Trâu", "16 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."}
        };
        DetailServiceAdapter adapter = new DetailServiceAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false);
        binding.rcvComplentDetailService.setLayoutManager(layoutManager);
        binding.rcvComplentDetailService.setAdapter(adapter);
    }
}