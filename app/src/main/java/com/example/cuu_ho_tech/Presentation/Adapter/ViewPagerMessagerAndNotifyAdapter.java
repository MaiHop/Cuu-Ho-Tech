package com.example.cuu_ho_tech.Presentation.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cuu_ho_tech.Presentation.Fragment.MessagerFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.NotifyFragment;

public class ViewPagerMessagerAndNotifyAdapter extends FragmentStateAdapter {
    public ViewPagerMessagerAndNotifyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MessagerFragment();
            case 1:
                return new NotifyFragment();
            default:
                return new MessagerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
