package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ItemOrderServiceListBinding;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public ItemOrderServiceListBinding binding;
    public TextView tv_name_service, tv_price_service;
    public CheckBox cb_service;

    public ServiceViewHolder(ItemOrderServiceListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        init();
    }

    private void init(){
        tv_name_service = itemView.findViewById(R.id.tv_name_service);
        tv_price_service = itemView.findViewById(R.id.tv_price_service);
        cb_service = itemView.findViewById(R.id.cb_service);
    }
}
