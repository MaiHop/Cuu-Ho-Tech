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
import com.example.cuu_ho_tech.Presentation.Adapter.WalletAndIncomeAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.BaseActivity;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityMessagerNotifyBinding;
import com.example.cuu_ho_tech.databinding.ActivityWalletIncomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WalletAndIncomeActivity extends BaseActivity {
    ActivityWalletIncomeBinding binding;
    private WalletAndIncomeAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletIncomeBinding.inflate(getLayoutInflater());
        BaseActivity.setOnConnectListener(WalletAndIncomeActivity.this);
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();
    }

    @Override
    public void registerNetworkReceiver() {
        super.registerNetworkReceiver();
    }

    @Override
    public void onNetworkDisconnected() {
        super.onNetworkDisconnected();
    }

    @Override
    public void onNetworkConnected() {
        super.onNetworkConnected();
    }

    private void initialize() {

        // Set adapter cho ViewPager2
        viewPagerAdapter = new WalletAndIncomeAdapter(this);
        binding.viewPager.setAdapter(viewPagerAdapter);

        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Đồng bộ TabLayout với ViewPager2
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Ví");
                    break;
                case 1:
                    tab.setText("Thu nhập");
                    break;
                default:
                    tab.setText("Ví");
                    break;
            }
        }).attach();
        for (int i = 0; i <= 2; i++) {
            TextView textView = (TextView) LayoutInflater.from(WalletAndIncomeActivity.this).inflate(R.layout.table_title_message_notify, null);
            if (binding.tabLayout.getTabAt(i) != null) {
                binding.tabLayout.getTabAt(i).setCustomView(textView);
            }
        }

        // Đặt padding/margin cho mỗi tab
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            params.setMargins(-18, 0, -18, 0); // Thay 16 bằng giá trị margin bạn muốn
            tab.requestLayout();
        }
    }
}