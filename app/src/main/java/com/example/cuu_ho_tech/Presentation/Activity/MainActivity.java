package com.example.cuu_ho_tech.Presentation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cuu_ho_tech.Presentation.ConnectInternet.BaseActivity;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.InternetUI;
import com.example.cuu_ho_tech.Presentation.Fragment.AccountFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.EmergencyRescueFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.HistoryFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.HomeFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.WorkShopFragment;
import com.example.cuu_ho_tech.Presentation.ViewModel.LocationHelper;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedMainToRescueViewModel;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private SharedMainToRescueViewModel sharedMainToRescueViewModel;
    private final HomeFragment homeFragment = new HomeFragment();
    private final HistoryFragment historyFragment = new HistoryFragment();
    private final WorkShopFragment workShopFragment = new WorkShopFragment();
    private final AccountFragment accountFragment = new AccountFragment();
    private final EmergencyRescueFragment emergencyRescueFragment = new EmergencyRescueFragment();
    private Fragment activeFragment = homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BaseActivity.setOnConnectListener(this);// TODO:
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomAppBar, (v, insets) -> {
            Insets systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    v.getPaddingBottom());
            return insets;
        });


        getSupportFragmentManager().beginTransaction().add(binding.frameLayout.getId(), homeFragment, "home").commit();
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Handler handler = new Handler(Looper.getMainLooper());
            binding.bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(MainActivity.this, R.color.bottom_nav_text_color));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.bottomNavigationView.setItemActiveIndicatorColor(ContextCompat.getColorStateList(MainActivity.this, R.color.primary_main));
                }
            }, 200);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int itemId = item.getItemId();
                    if (itemId == R.id.home) {
                        showFragment(homeFragment);
                    } else if (itemId == R.id.history) {
                        showFragment(historyFragment);
                    } else if (itemId == R.id.workShop) {
                        showFragment(workShopFragment);
                    } else if (itemId == R.id.account) {
                        showFragment(accountFragment);
                    }
                }
            }, 300);
            return true;
        });

        binding.btnFab.setOnClickListener(v -> {
            if(emergencyRescueFragment != activeFragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!emergencyRescueFragment.isAdded()) {
                    transaction.add(binding.frameLayout.getId(), emergencyRescueFragment).hide(activeFragment).show(emergencyRescueFragment).commit();
                } else transaction.hide(activeFragment).show(emergencyRescueFragment).commit();
                activeFragment = emergencyRescueFragment;
                binding.bottomNavigationView.setSelectedItemId(R.id.fab);
                binding.bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.bottom_nav_text_red_color));
                binding.bottomNavigationView.setItemActiveIndicatorColor(ContextCompat.getColorStateList(this, R.color.transparent));
            }
        });
        sharedMainToRescueViewModel = new ViewModelProvider(this).get(SharedMainToRescueViewModel.class);
        sharedMainToRescueViewModel.hideFragment().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String selectedItem) {
                binding.bottomNavigationView.setSelectedItemId(R.id.home);
            }
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(activeFragment == emergencyRescueFragment) sharedMainToRescueViewModel.setData("true");
        if (!fragment.isAdded()) {
            transaction.add(binding.frameLayout.getId(), fragment).hide(activeFragment).show(fragment).commit();
        } else if (fragment != activeFragment) {
            transaction.hide(activeFragment).show(fragment).commit();
        }
        activeFragment = fragment;
    }

    // location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LocationHelper.PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location services", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationHelper.REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Location services", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Location services not enabled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    // location

    @Override
    public void onNetworkDisconnected() {
        InternetUI.NetworkDisconnected(this, binding.connect);
    }

    @Override
    public void onNetworkConnected() {
        InternetUI.NetworkConnected(this, binding.connect);
    }

}