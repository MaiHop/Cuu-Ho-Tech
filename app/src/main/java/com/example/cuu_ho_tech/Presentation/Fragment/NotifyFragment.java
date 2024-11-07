package com.example.cuu_ho_tech.Presentation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.MessagerAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.DialogNotificationBinding;
import com.example.cuu_ho_tech.databinding.FragmentNotifyBinding;

import java.util.Arrays;
import java.util.List;

public class NotifyFragment extends Fragment {
    private FragmentNotifyBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotifyBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        List<List<String>> data = Arrays.asList(
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Nguyễn Văn Tạo", "Xe của bạn đã bị luộc", "2 giây", "1"),
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Minh Khai", "Kiểm tra xe định kỳ", "5 phút", "1"),
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Lý Thường Kiệt", "Xe của bạn đã sửa xong", "10 phút", "0"),
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Hoàng Hoa Thám", "Bảo dưỡng xe lần đầu", "15 phút", "1")
        );
        binding.rcvNotify.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessagerAdapter messagerAdapter = new MessagerAdapter(requireContext(), data);
        binding.rcvNotify.setAdapter(messagerAdapter);
    }
}
