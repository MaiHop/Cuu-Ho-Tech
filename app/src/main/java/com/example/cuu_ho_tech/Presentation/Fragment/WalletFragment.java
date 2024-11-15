package com.example.cuu_ho_tech.Presentation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.FragmentWalletWalletBinding;
import com.example.cuu_ho_tech.databinding.FragmentWorkShopBinding;


public class WalletFragment extends Fragment {

    FragmentWalletWalletBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletWalletBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}