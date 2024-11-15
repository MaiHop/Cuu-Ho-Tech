package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemListBinding;
public class ItemViewHolder extends RecyclerView.ViewHolder{

    ItemListBinding binding;

    public ItemViewHolder(ItemListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvLocationName.setText(technicians);
    }
}
