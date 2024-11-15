package com.example.cuu_ho_tech.Presentation.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cuu_ho_tech.Presentation.Fragment.WalletFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.IncomeFragment;

public class WalletAndIncomeAdapter extends FragmentStateAdapter {
    public WalletAndIncomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WalletFragment();
            case 1:
                return new IncomeFragment();
            default:
                return new WalletFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
