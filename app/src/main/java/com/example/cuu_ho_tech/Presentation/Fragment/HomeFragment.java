package com.example.cuu_ho_tech.Presentation.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Activity.MessagerAndNotifyActivity;
import com.example.cuu_ho_tech.Presentation.Activity.SearchServiceAndOrderActivity;
import com.example.cuu_ho_tech.Presentation.Adapter.NotificationAdapter;
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
        listNotificationAdapter();
        listTechnicianAdapter();
        listScheduleTechnicianAdapter();
        listServiceAdapter();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), SearchServiceAndOrderActivity.class);
                startActivity(intent);
            }
        });
        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MessagerAndNotifyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void listServiceAdapter() {
        String[][] services = {
                {String.valueOf(R.drawable.image_car_repair), String.valueOf(R.drawable.ic_car_gear), "Sửa xe", "2.000.000VNĐ", "4"},
                {String.valueOf(R.drawable.image_charging), String.valueOf(R.drawable.ic_car_charging), "Sạc điện", "2.000.000VNĐ", "5"},
                {String.valueOf(R.drawable.image_change_tire), String.valueOf(R.drawable.ic_car_tire), "Thay lốp", "2.000.000VNĐ", "4.5"},
                {String.valueOf(R.drawable.image_oil_change), String.valueOf(R.drawable.ic_car_gas_tank), "Thay nhớt", "2.000.000VNĐ", "4"},
                {String.valueOf(R.drawable.image_car_wash), String.valueOf(R.drawable.ic_car_wash), "Rửa xe", "2.000.000VNĐ", "3.5"},
                {String.valueOf(R.drawable.image_car_modification), String.valueOf(R.drawable.ic_car_modification), "Độ xe", "2.000.000VNĐ", "4"}
        };
        ServiceHomeAdapter adapter = new ServiceHomeAdapter(services, requireContext());
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.listService.setLayoutManager(layoutManager);
        binding.listService.setAdapter(adapter);
    }

    private void listScheduleTechnicianAdapter() {
        String[][] scheduleTechnicians = {
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "Ngày đặt: 12 th 02 2024", "0"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "Ngày đặt: 12 th 02 2024", "1"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "Ngày đặt: 12 th 02 2024", "2"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "Ngày đặt: 12 th 02 2024", "3"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "Ngày đặt: 12 th 02 2024", "4"}
        };
        ScheduleTechnicianAdapter adapter = new ScheduleTechnicianAdapter(scheduleTechnicians);
        binding.listTechnicianSchedule.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.listTechnicianSchedule.setLayoutManager(layoutManager);
        binding.listTechnicianSchedule.setHorizontalScrollBarEnabled(false);
        binding.listTechnicianSchedule.setVerticalScrollBarEnabled(false);
    }

    private void listTechnicianAdapter() {
        String[][] technicians = {
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "0"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "1"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "2"},
                {"Tên kỹ thuật viên", "1 dịch vụ", "999.999.999.999.999đ", "3"}
        };
        TechnicianAdapter adapter = new TechnicianAdapter(technicians);
        binding.listTechnician.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.listTechnician.setLayoutManager(layoutManager);
        binding.listTechnician.setHorizontalScrollBarEnabled(false);
        binding.listTechnician.setVerticalScrollBarEnabled(false);
    }

    private void listNotificationAdapter() {
        String[][] notifications = {
                {"Đội sửa chữa", "Xe của bạn đã bị luộc", "1"},
                {"Đơn cứu hộ", "Đơn của bạn đã được xác nhận thành công Đơn của bạn đã được xác nhận thành công Đơn của bạn đã được xác nhận thành công", "0"},
                {"None", "None", "0"}
        };
        NotificationAdapter adapter = new NotificationAdapter(notifications);
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
