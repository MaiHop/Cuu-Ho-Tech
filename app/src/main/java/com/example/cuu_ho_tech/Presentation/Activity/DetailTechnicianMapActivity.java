package com.example.cuu_ho_tech.Presentation.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.DetailServiceAdapter;
import com.example.cuu_ho_tech.databinding.ActivityDetailTechnicianMapBinding;

public class DetailTechnicianMapActivity extends AppCompatActivity {
    ActivityDetailTechnicianMapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTechnicianMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();

    }
    private void initialize() {
        listDetailServiceAdapter();
        binding.btnCopyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
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