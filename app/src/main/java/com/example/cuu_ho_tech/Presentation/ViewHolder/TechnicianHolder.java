package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemTechnicianHomeBinding;

public class TechnicianHolder extends RecyclerView.ViewHolder {
    ItemTechnicianHomeBinding binding;
    public TechnicianHolder(ItemTechnicianHomeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String[] technicians) {
        binding.title.setText(technicians[0]);
        binding.numberService.setText(technicians[1]);
        binding.amount.setText(technicians[2]);
        switch (Integer.parseInt(technicians[3])) {
            case 0 : binding.processing.setVisibility(View.VISIBLE); break;
            case 1 : binding.confirmed.setVisibility(View.VISIBLE); break;
            case 2 : binding.completed.setVisibility(View.VISIBLE); break;
            case 3 : binding.delete.setVisibility(View.VISIBLE); break;
        }
    }

    public void bindSchedule(String[] scheduleTechnicians) {
        binding.title.setText(scheduleTechnicians[0]);
        binding.numberService.setText(scheduleTechnicians[1]);
        binding.amount.setText(scheduleTechnicians[2]);
        binding.date.setVisibility(View.VISIBLE);
        binding.date.setText(scheduleTechnicians[3]);
        switch (Integer.parseInt(scheduleTechnicians[4])) {
            case 0 : binding.send.setVisibility(View.VISIBLE); break;
            case 1 : binding.processing.setVisibility(View.VISIBLE); break;
            case 2 : binding.confirmed.setVisibility(View.VISIBLE); break;
            case 3 : binding.completed.setVisibility(View.VISIBLE); break;
            case 4 : binding.delete.setVisibility(View.VISIBLE); break;
        }
    }
}

