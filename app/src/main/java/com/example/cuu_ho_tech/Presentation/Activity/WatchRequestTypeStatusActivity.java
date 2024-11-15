package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.RequestTypeStatusAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.RequestWatchOrderAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ActivityWalletCashDetailBinding;
import com.example.cuu_ho_tech.databinding.ActivityWatchRequestTypeStatusBinding;

import java.util.ArrayList;
import java.util.List;

public class WatchRequestTypeStatusActivity extends AppCompatActivity {
    ActivityWatchRequestTypeStatusBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityWatchRequestTypeStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        innit();
    }

    private void innit() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        RequestTypeStatusAdapter adapter = new RequestTypeStatusAdapter(WatchRequestTypeStatusActivity.this, list, new ClickListener.OnClickListItemListener() {
            @Override
            public void onClick(String data, int position) {
                Intent i = new Intent(WatchRequestTypeStatusActivity.this, RequestDetailActivity.class);
                startActivity(i);
                Toast.makeText(WatchRequestTypeStatusActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvRequestTypeStatus.setLayoutManager(new LinearLayoutManager(WatchRequestTypeStatusActivity.this));
        binding.rvRequestTypeStatus.setAdapter(adapter);
    }
}