package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.DetailerServiceViewHolder;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ItemComplentDetailServiceBinding;
import com.example.cuu_ho_tech.databinding.ItemServiceBinding;

public class DetailServiceAdapter extends RecyclerView.Adapter<DetailerServiceViewHolder>{

    private String[][] data;

    public DetailServiceAdapter(String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DetailerServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemComplentDetailServiceBinding binding = ItemComplentDetailServiceBinding.inflate(inflater, parent, false);
        return new DetailerServiceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailerServiceViewHolder holder, int position) {
        String[] comment = data[position];
        holder.bind(comment);
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
