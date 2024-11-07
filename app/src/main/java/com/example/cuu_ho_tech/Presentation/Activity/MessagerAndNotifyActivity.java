package com.example.cuu_ho_tech.Presentation.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cuu_ho_tech.Presentation.Adapter.ViewPagerMessagerAndNotifyAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityMessagerNotifyBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MessagerAndNotifyActivity extends AppCompatActivity {
    ActivityMessagerNotifyBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerMessagerAndNotifyAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessagerNotifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();
    }

    private void initialize() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Set adapter cho ViewPager2
        viewPagerAdapter = new ViewPagerMessagerAndNotifyAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Đồng bộ TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Trò chuyện");
                    break;
                case 1:
                    tab.setText("Thông báo");
                    break;
                default:
                    tab.setText("Trò chuyện");
                    break;
            }
        }).attach();
        for (int i = 0; i <= 2; i++) {
            TextView textView = (TextView) LayoutInflater.from(MessagerAndNotifyActivity.this).inflate(R.layout.table_title_message_notify, null);
            if (binding.tabLayout.getTabAt(i) != null) {
                binding.tabLayout.getTabAt(i).setCustomView(textView);
            }
        }

        // Đặt padding/margin cho mỗi tab
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            params.setMargins(-18, 0, -18, 0); // Thay 16 bằng giá trị margin bạn muốn
            tab.requestLayout();
        }
    }
}