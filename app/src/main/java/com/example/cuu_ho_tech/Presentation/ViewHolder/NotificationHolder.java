package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemNotificationBinding;


public class NotificationHolder extends RecyclerView.ViewHolder {
    ItemNotificationBinding binding;
    public NotificationHolder(ItemNotificationBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String[] notification) {
        binding.title.setText(notification[0]);
        binding.stickerNew.setVisibility(Integer.parseInt(notification[2]) != 1 ? View.GONE : View.VISIBLE);
        binding.text.setText(notification[1]);
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

