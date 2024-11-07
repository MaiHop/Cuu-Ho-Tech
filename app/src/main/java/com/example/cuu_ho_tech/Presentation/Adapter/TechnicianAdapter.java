package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.TechnicianHolder;
import com.example.cuu_ho_tech.databinding.ItemTechnicianHomeBinding;

public class TechnicianAdapter extends RecyclerView.Adapter<TechnicianHolder>{
    private String[][] data;

    public TechnicianAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TechnicianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTechnicianHomeBinding binding = ItemTechnicianHomeBinding.inflate(inflater, parent, false);
        return new TechnicianHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicianHolder holder, int position) {
        String[] technicians = data[position];
        holder.bind(technicians);
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
