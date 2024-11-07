package com.example.cuu_ho_tech.Presentation.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cuu_ho_tech.Custom.PulseEffect.PulseView;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedMainToRescueViewModel;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.FragmentEmergencyRescueBinding;

public class EmergencyRescueFragment extends Fragment implements
        View.OnClickListener,
        PulseView.PulseListener {
    private FragmentEmergencyRescueBinding binding;
    private SharedMainToRescueViewModel viewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEmergencyRescueBinding.inflate(inflater, container, false);
        initialize();
        return binding.getRoot();
    }

    public void initialize() {
        binding.pulseRedAlert.setPulseListener(this);
        binding.pulseRedAlert.setOnClickListener(this);
        binding.pulseRedAlert.startPulse();
        binding.pulseClear.setPulseListener(this);
        binding.pulseClear.setOnClickListener(this);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedMainToRescueViewModel.class);
        viewModel.hideFragment().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String selectedItem) {
                binding.pulseClear.finishPulse();
                binding.btnLoading.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.ic_red_alert));
                binding.layoutPulseClear.setVisibility(View.GONE);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                binding.loadingSeekbar.setVisibility(View.GONE);
            }
        });

        binding.btnLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.loadingSeekbar.getVisibility() != View.VISIBLE) {
                    binding.btnLoading.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.button_danger_filled_enabled));
                    binding.layoutPulseClear.setVisibility(View.VISIBLE);
                    binding.pulseClear.startPulse();
                    binding.loadingSeekbar.setVisibility(View.VISIBLE);
                    binding.loadingSeekbar.setMax(500);
                    pos = 0;
                    binding.loadingSeekbar.setProgress(pos);
                    handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (pos <= 500) {
                                binding.loadingSeekbar.setProgress(pos);
                                pos++;
                                handler.postDelayed(this, 500);
                            }
                        }
                    }, 500);
                }
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewModel.setData("true");
                binding.pulseClear.finishPulse();
                binding.btnLoading.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.ic_red_alert));
                binding.layoutPulseClear.setVisibility(View.GONE);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                binding.loadingSeekbar.setVisibility(View.GONE);
            }
        });

    }
    private Handler handler;
    private int pos = 0;
    private int mCounterRed = 0;
    private int mCounterClear = 0;
    @Override
    public void onClick(final View v) {
        if (v.getId() == binding.pulseRedAlert.getId()) {
            if (mCounterRed++ % 2 == 0) {
                binding.pulseClear.startPulse();
                binding.layoutPulseClear.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == binding.pulseClear.getId()) {
            if (mCounterClear++ % 2 == 0) {
                binding.pulseClear.startPulse();
            } else {
                binding.pulseClear.finishPulse();
            }
        }
    }

    @Override
    public void onStartPulse() {
        Toast.makeText(requireContext(), "Start pulse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishPulse() {
        Toast.makeText(requireContext(), "Finish pulse", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
