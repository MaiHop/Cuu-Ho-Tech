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
import com.example.cuu_ho_tech.databinding.FragmentMessagerBinding;

import java.util.Arrays;
import java.util.List;

public class MessagerFragment extends Fragment {
    private FragmentMessagerBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMessagerBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    private void initialize() {
        List<List<String>> data = Arrays.asList(
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Nguyễn Văn Tạo", "Xe của bạn đã bị luộc", "2 giây", "1"),
                Arrays.asList(String.valueOf(R.drawable.ic_calendar), "Gara Minh Khai", "Kiểm tra xe định kỳ", "5 phút", "1"),
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Lý Thường Kiệt", "Xe của bạn đã sửa xong", "10 phút", "0"),
                Arrays.asList(String.valueOf(R.drawable.ic_technics), "Gara Hoàng Hoa Thám", "Bảo dưỡng xe lần đầu", "15 phút", "1")
        );
        binding.rcvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessagerAdapter messagerAdapter = new MessagerAdapter(requireContext(), data);
        binding.rcvMessage.setAdapter(messagerAdapter);
    }
}
