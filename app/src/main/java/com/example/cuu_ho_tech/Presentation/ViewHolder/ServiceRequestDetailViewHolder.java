package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemLocationBinding;
import com.example.cuu_ho_tech.databinding.ItemServiceRequestDetailBinding;

public class ServiceRequestDetailViewHolder extends RecyclerView.ViewHolder{

    ItemServiceRequestDetailBinding binding;

    public ServiceRequestDetailViewHolder(ItemServiceRequestDetailBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvServiceRequestDetailName.setText(technicians);
    }
}
