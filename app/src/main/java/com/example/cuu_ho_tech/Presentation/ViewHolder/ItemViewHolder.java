package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemLocationBinding;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    ItemLocationBinding binding;

    public ItemViewHolder(ItemLocationBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvLocationName.setText(technicians);
    }
}
