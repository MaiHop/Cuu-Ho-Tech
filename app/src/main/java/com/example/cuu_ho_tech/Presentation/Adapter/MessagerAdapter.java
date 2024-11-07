package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.Activity.DetailMessageActivity;
import com.example.cuu_ho_tech.Presentation.ViewHolder.MessageViewHolder;
import com.example.cuu_ho_tech.databinding.ItemMessagerBinding;

import java.util.List;

public class MessagerAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    private List<List<String>> data;
    private Context context;
    public MessagerAdapter(Context context, List<List<String>> list) {
        this.context = context;
        this.data = list;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMessagerBinding binding = ItemMessagerBinding.inflate(inflater, parent, false);
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        List<String> newData = data.get(position);
        holder.bind(newData, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMessageActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
