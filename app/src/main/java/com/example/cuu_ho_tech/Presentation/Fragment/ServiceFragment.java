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
import com.example.cuu_ho_tech.databinding.FragmentOrderServiceListBinding;

import java.util.Arrays;
import java.util.List;

public class ServiceFragment extends Fragment {
    FragmentOrderServiceListBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderServiceListBinding.inflate(inflater, container, false);
        binding.rcvOrderServiceList.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<ServiceResponse> serviceResponseList = Arrays.asList(
                new ServiceResponse("Service 1", "Description 1", 100),
                new ServiceResponse("Service 2", "Description 2", 200),
                new ServiceResponse("Service 3", "Description 3", 300)
        );
        ServiceAdapter serviceAdapter = new ServiceAdapter(serviceResponseList, getActivity(),getActivity().getSupportFragmentManager(), binding.btnCreatedService);
        binding.rcvOrderServiceList.setAdapter(serviceAdapter);

        return binding.getRoot();
    }
}
