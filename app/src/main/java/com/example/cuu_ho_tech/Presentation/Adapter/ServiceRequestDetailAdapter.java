package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.ServiceRequestDetailViewHolder;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ItemServiceRequestDetailBinding;

import java.util.List;

public class ServiceRequestDetailAdapter extends RecyclerView.Adapter<ServiceRequestDetailViewHolder>{
    private List<String> list;
    private LayoutInflater minflater;
    private Context context;
    private ClickListener clickListener;
    public ServiceRequestDetailAdapter(Context context, List<String> list, ClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
        this.minflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ServiceRequestDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemServiceRequestDetailBinding binding = ItemServiceRequestDetailBinding.inflate(inflater, parent, false);

        return new ServiceRequestDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceRequestDetailViewHolder holder, int position) {
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
