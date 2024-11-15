package com.example.cuu_ho_tech.Presentation.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.FragmentRescueBinding;
import com.example.cuu_ho_tech.databinding.FragmentWalletWalletBinding;


public class RescueFragment extends Fragment {

    FragmentRescueBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRescueBinding.inflate(inflater, container, false);

        Drawable cog_off = ContextCompat.getDrawable(getContext(), R.drawable.ic_cog_off_24);
        binding.llNoData.ivIcon.setImageDrawable(cog_off);
        binding.llNoData.tvTitle.setText("Không có đơn cứu hộ");

        return binding.getRoot();
    }
}