package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.NotificationTechHolder;
import com.example.cuu_ho_tech.databinding.ItemNotificationTechBinding;

public class NotificationTechAdapter extends RecyclerView.Adapter<NotificationTechHolder> {
    private String[][] data;

    public NotificationTechAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public NotificationTechHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNotificationTechBinding binding = ItemNotificationTechBinding.inflate(inflater, parent, false);
        return new NotificationTechHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationTechHolder holder, int position) {
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