package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.Activity.DetailTechnicianMapActivity;
import com.example.cuu_ho_tech.Presentation.ViewHolder.TechnicianSearchViewHolder;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ItemTechnicianHomeBinding;
import com.example.cuu_ho_tech.databinding.ItemTechnicianSearchBinding;

import java.util.List;

public class TechnicianSearchAdapter extends RecyclerView.Adapter<TechnicianSearchViewHolder>{
    private List<String> list;
    private LayoutInflater minflater;
    private Context context;
    private ClickListener clickListener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public TechnicianSearchAdapter(Context context,List<String> list, ClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
        this.minflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public TechnicianSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemTechnicianSearchBinding binding = ItemTechnicianSearchBinding.inflate(inflater, parent, false);

        return new TechnicianSearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicianSearchViewHolder holder, int position) {
        String tech = list.get(position);
        int p = position;

        holder.updateUI(tech);

        // Kiểm tra xem item có đang được chọn không
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.background_technician_item_checked);
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gray_02_100));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật selectedPosition và gọi notifyDataSetChanged để cập nhật giao diện
                int previousSelectedPosition = selectedPosition;
                selectedPosition = p;

                notifyItemChanged(previousSelectedPosition); // Làm mới item trước đó
                notifyItemChanged(selectedPosition); // Làm mới item hiện tại
                Log.d("ITEM", tech);

                clickListener.clickItem(tech);
            }
        });
        holder.binding.btnTechnicianItemWatchDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailTechnicianMapActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
