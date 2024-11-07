package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.ItemViewHolder;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ItemLocationBinding;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<ItemViewHolder>{
    private List<String> list;
    private LayoutInflater minflater;
    private Context context;
    private ClickListener clickListener;
    public LocationAdapter(Context context, List<String> list, ClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
        this.minflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemLocationBinding binding = ItemLocationBinding.inflate(inflater, parent, false);

        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String tech = list.get(position);

        holder.updateUI(tech);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.clickItem(tech);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
