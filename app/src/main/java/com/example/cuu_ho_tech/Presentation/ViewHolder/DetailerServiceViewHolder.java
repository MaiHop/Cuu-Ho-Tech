package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemComplentDetailServiceBinding;
import com.example.cuu_ho_tech.databinding.ItemServiceBinding;

public class DetailerServiceViewHolder extends RecyclerView.ViewHolder{
    ItemComplentDetailServiceBinding binding;
    public DetailerServiceViewHolder(ItemComplentDetailServiceBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String[] data) {
        binding.tvNameUser.setText(data[1]);
        binding.tvDateComplent.setText(data[2]);
        binding.tvContentComplent.setText(data[3]);
    }
}
