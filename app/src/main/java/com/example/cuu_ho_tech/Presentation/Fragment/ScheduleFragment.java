package com.example.cuu_ho_tech.Presentation.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Activity.RequestDetailActivity;
import com.example.cuu_ho_tech.Presentation.Adapter.RequestWatchOrderAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.FragmentScheduleBinding;

import java.util.ArrayList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    FragmentScheduleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);

        innit();


        return binding.getRoot();
    }

    private void innit() {
        Drawable ic_calendar_remove = ContextCompat.getDrawable(getContext(), R.drawable.ic_calendar_remove_24);
        binding.llNoData.ivIcon.setImageDrawable(ic_calendar_remove);
        binding.llNoData.tvTitle.setText("Không có đơn đặt lịch nào");


        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        RequestWatchOrderAdapter adapter = new RequestWatchOrderAdapter(getActivity(), list, new ClickListener.OnClickListItemListener() {
            @Override
            public void onClick(String data, int position) {
                Intent i = new Intent(getContext(), RequestDetailActivity.class);
                getActivity().startActivity(i);
                Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvListSent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvListSent.setAdapter(adapter);
        binding.btnGoToListSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}