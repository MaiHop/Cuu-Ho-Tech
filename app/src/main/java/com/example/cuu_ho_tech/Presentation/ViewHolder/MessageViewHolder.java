package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ItemMessagerBinding;
import com.example.cuu_ho_tech.databinding.ItemOrderScheduleListBinding;

import java.util.List;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    ItemMessagerBinding binding;
    public MessageViewHolder(ItemMessagerBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(List<String> data, Context context) {
        binding.icon.setBackground(ContextCompat.getDrawable(context, Integer.parseInt(data.get(0))));
        binding.title.setText(data.get(1));
        binding.name.setText(data.get(2));
        binding.time.setText(data.get(3));
        binding.iconNew.setVisibility(Integer.parseInt(data.get(4)) != 1 ? View.GONE : View.VISIBLE);
    }
}
