package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cuu_ho_tech.Presentation.ViewHolder.NotificationHolder;
import com.example.cuu_ho_tech.databinding.ItemNotificationBinding;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {
    private String[][] data;

    public NotificationAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(inflater, parent, false);
        return new NotificationHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        String[] notificationData = data[position];
        holder.bind(notificationData);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}