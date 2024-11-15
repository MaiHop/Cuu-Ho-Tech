package com.example.cuu_ho_tech.Presentation.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cuu_ho_tech.Presentation.Fragment.RescueFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.ScheduleFragment;

public class WatchRequestAdapter extends FragmentStateAdapter {
    public WatchRequestAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ScheduleFragment();
            case 1:
                return new RescueFragment();
            default:
                return new ScheduleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
