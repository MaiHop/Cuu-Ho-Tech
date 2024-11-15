package com.example.cuu_ho_tech.Presentation.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Custom.ConnectEffect.Connect;
import com.example.cuu_ho_tech.Presentation.Activity.MessagerAndNotifyActivity;
import com.example.cuu_ho_tech.Presentation.Activity.SearchServiceAndOrderActivity;
import com.example.cuu_ho_tech.Presentation.Adapter.NotificationTechAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.ScheduleTechnicianAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.ServiceHomeAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        listNotificationTechAdapter();
        binding.btnLayoutConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect.animateWidth(v, binding.textNote);
            }
        });
    }


    private void listNotificationTechAdapter() {
        String[][] notifications = {
                {"Đơn cứu hộ", "Xe của bạn đã bị luộc", "1"},
                {"Đơn cứu hộ", "Đơn của bạn đã được xác nhận thành công", "0"},
                {"None", "None", "0"}
        };
        NotificationTechAdapter adapter = new NotificationTechAdapter(notifications);
        binding.listNotification.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.listNotification.setLayoutManager(layoutManager);
        binding.listNotification.setHorizontalScrollBarEnabled(false);
        binding.listNotification.setVerticalScrollBarEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
