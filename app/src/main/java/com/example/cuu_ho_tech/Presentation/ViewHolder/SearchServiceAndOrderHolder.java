package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemTextSearchHomeBinding;

import java.util.List;

public class SearchServiceAndOrderHolder extends RecyclerView.ViewHolder {
    ItemTextSearchHomeBinding binding;
    public SearchServiceAndOrderHolder(ItemTextSearchHomeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(List<String> data) {
        binding.text.setText(data.get(0));
        if(Integer.parseInt(data.get(1)) != 0)
            binding.image.setVisibility(View.VISIBLE);
        else
            binding.image.setVisibility(View.GONE);
    }

    public TextView getEdtText() {
        return binding.text;
    }
}
