package com.example.cuu_ho_tech.Presentation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.NotifyViewHolder;
import com.example.cuu_ho_tech.R;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyViewHolder>{
    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify, parent, false);
        return new NotifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 40;
    }
}
