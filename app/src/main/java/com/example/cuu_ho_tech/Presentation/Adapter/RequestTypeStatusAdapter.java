package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.RequestTypeStatusViewHolder;
import com.example.cuu_ho_tech.Presentation.ViewHolder.RequestWatchOrderViewHolder;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ItemRequestTypeStatusBinding;
import com.example.cuu_ho_tech.databinding.ItemRequestWatchOrderBinding;

import java.util.List;

public class RequestTypeStatusAdapter extends RecyclerView.Adapter<RequestTypeStatusViewHolder>{
    private List<String> list_item;
    private LayoutInflater minflater;
    private Context context;
    private ClickListener.OnClickListItemListener clickListener;
    public RequestTypeStatusAdapter(Context context, List<String> list_item, ClickListener.OnClickListItemListener clickListener) {
        this.list_item = list_item;
        this.clickListener = clickListener;
        this.minflater = LayoutInflater.from(context);
    }

    public void setData(List<String> list_item) {
        this.list_item = list_item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestTypeStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemRequestTypeStatusBinding binding = ItemRequestTypeStatusBinding.inflate(inflater, parent, false);

        return new RequestTypeStatusViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestTypeStatusViewHolder holder, int position) {
        String tech = list_item.get(position);
        int p = position;

        holder.updateUI(tech);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(tech, p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }
}