package com.example.cuu_ho_tech.Presentation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.ServiceResponse;
import com.example.cuu_ho_tech.Presentation.Adapter.ServiceAdapter;
import com.example.cuu_ho_tech.R;

import java.util.Arrays;
import java.util.List;

public class ServiceFragment extends Fragment {
    Button btn_created_service;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_service_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcv_order_service_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btn_created_service = view.findViewById(R.id.btn_created_service);

        List<ServiceResponse> serviceResponseList = Arrays.asList(
                new ServiceResponse("Service 1", "Description 1", 100),
                new ServiceResponse("Service 2", "Description 2", 200),
                new ServiceResponse("Service 3", "Description 3", 300)
        );
        ServiceAdapter serviceAdapter = new ServiceAdapter(serviceResponseList, getActivity(), btn_created_service);
        recyclerView.setAdapter(serviceAdapter);

        return view;
    }
}
