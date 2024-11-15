package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemListBinding;
import com.example.cuu_ho_tech.databinding.ItemRequestWatchOrderBinding;

public class RequestWatchOrderViewHolder extends RecyclerView.ViewHolder{

    ItemRequestWatchOrderBinding binding;

    public RequestWatchOrderViewHolder(ItemRequestWatchOrderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public void updateUI(String technicians) {
        binding.tvCustomerName.setText(technicians);
    }
}
