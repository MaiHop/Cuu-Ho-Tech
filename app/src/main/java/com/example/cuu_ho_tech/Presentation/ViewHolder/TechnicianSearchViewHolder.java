package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemTechnicianHomeBinding;
import com.example.cuu_ho_tech.databinding.ItemTechnicianSearchBinding;

public class TechnicianSearchViewHolder extends RecyclerView.ViewHolder {
    public ItemTechnicianSearchBinding binding;
    public TechnicianSearchViewHolder(ItemTechnicianSearchBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvTechnicianItemUsername.setText(technicians);
    }
}

