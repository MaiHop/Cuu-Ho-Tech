package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.Activity.DetailServiceActivity;
import com.example.cuu_ho_tech.Presentation.ViewHolder.ServiceHolder;
import com.example.cuu_ho_tech.databinding.ItemServiceBinding;

public class ServiceHomeAdapter extends RecyclerView.Adapter<ServiceHolder>{
    private String[][] data;
    private Context context;

    public ServiceHomeAdapter(String[][] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemServiceBinding binding = ItemServiceBinding.inflate(inflater, parent, false);
        return new ServiceHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        String[] services = data[position];
        holder.bind(services, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailServiceActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
