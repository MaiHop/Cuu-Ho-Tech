package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.OrderScheduleListHolder;
import com.example.cuu_ho_tech.databinding.ItemOrderScheduleListBinding;

public class OrderScheduleListAdapter extends RecyclerView.Adapter<OrderScheduleListHolder>{
    private String[][] data;

    public OrderScheduleListAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderScheduleListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderScheduleListBinding binding = ItemOrderScheduleListBinding.inflate(inflater, parent, false);
        return new OrderScheduleListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderScheduleListHolder holder, int position) {
        String[] texts = data[position];
        holder.bind(texts);
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