package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemOrderScheduleListBinding;

public class OrderScheduleListHolder  extends RecyclerView.ViewHolder {
    ItemOrderScheduleListBinding binding;
    public OrderScheduleListHolder(ItemOrderScheduleListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(String[] data) {
        binding.tvName.setText(data[0]);
        binding.tvAddressSchedule.setText(data[1]);
        binding.tvDateSchedule.setText(String.format("Ngày đặt: %s", data[2]));
        binding.tvLicensePlateNumber.setText(data[3]);
        binding.numberService.setText(data[4]);
        binding.price.setText(data[5]);
        switch (Integer.parseInt(data[6])) {
            case 0 : binding.send.setVisibility(View.VISIBLE); break;
            case 1 : binding.processing.setVisibility(View.VISIBLE); break;
            case 2 : binding.confirmed.setVisibility(View.VISIBLE); break;
            case 3 : binding.completed.setVisibility(View.VISIBLE); break;
            case 4 : binding.delete.setVisibility(View.VISIBLE); break;
        }
    }
}
