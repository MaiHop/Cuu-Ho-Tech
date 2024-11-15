package com.example.cuu_ho_tech.Presentation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.cuu_ho_tech.databinding.FragmentWalletIncomeBinding;


public class IncomeFragment extends Fragment {

    FragmentWalletIncomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletIncomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}