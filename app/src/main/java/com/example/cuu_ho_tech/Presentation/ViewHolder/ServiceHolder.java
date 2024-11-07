package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemServiceBinding;

public class ServiceHolder extends RecyclerView.ViewHolder {
    ItemServiceBinding binding;
    public ServiceHolder(ItemServiceBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String[] service, Context context) {
        binding.image.setBackground(ContextCompat.getDrawable(context, Integer.parseInt(service[0])));
        binding.icon.setBackground(ContextCompat.getDrawable(context, Integer.parseInt(service[1])));
        binding.name.setText(service[2]);
        binding.price.setText(String.format("Giá: %s", service[3]));
        binding.numberStar.setText(service[4]);
    }

    public LinearLayout getLayout() {
        return binding.btnLayout;
    }
}

