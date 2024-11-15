package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemRequestTypeStatusBinding;
import com.example.cuu_ho_tech.databinding.ItemRequestWatchOrderBinding;

public class RequestTypeStatusViewHolder extends RecyclerView.ViewHolder{

    ItemRequestTypeStatusBinding binding;

    public RequestTypeStatusViewHolder(ItemRequestTypeStatusBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvName.setText(technicians);
    }
}
