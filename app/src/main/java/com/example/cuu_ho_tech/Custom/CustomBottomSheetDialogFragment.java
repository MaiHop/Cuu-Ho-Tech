package com.example.cuu_ho_tech.Custom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.FragmentCustomBottomSheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private FragmentCustomBottomSheetDialogBinding binding;
    private Fragment fragment;

    public CustomBottomSheetDialogFragment setCustomFragment(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCustomBottomSheetDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getChildFragmentManager().beginTransaction()
                .replace(binding.layoutFragment.getId(), fragment)
                .commit();
    }


    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialogTheme;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
