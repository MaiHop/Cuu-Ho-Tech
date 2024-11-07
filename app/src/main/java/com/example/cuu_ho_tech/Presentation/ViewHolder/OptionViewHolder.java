package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemLocationBinding;
import com.example.cuu_ho_tech.databinding.ItemOptionBinding;

public class OptionViewHolder extends RecyclerView.ViewHolder{

    public ItemOptionBinding binding;

    public OptionViewHolder(ItemOptionBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String data) {
        binding.tvName.setText(data);
    }
}
