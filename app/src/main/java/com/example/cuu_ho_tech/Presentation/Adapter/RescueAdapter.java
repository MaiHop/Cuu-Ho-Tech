package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.StatusResponse;
import com.example.cuu_ho_tech.Presentation.Activity.RequestDetailActivity;
import com.example.cuu_ho_tech.Presentation.ViewHolder.RescueViewHolder;
import com.example.cuu_ho_tech.R;

import java.util.List;


public class RescueAdapter extends RecyclerView.Adapter<RescueViewHolder>{

    private List<StatusResponse> statusResponseList;
    Context context;

    public RescueAdapter(List<StatusResponse> statusResponseList, Context context){
        this.statusResponseList = statusResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public RescueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_rescue_list, parent, false);
        return new RescueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RescueViewHolder holder, int position) {
        if(statusResponseList.get(position).getStatus().equals("Đã hoàn thành")){
            holder.completed.setText(statusResponseList.get(position).getStatus());
        }else if (statusResponseList.get(position).getStatus().equals("Đang xử lý")){
            holder.completed.setBackgroundResource(R.drawable.boder_radius_status_processing);
            holder.completed.setTextColor(ContextCompat.getColor(context, R.color.warning_main));
        }
        holder.completed.setText(statusResponseList.get(position).getStatus());

        holder.ll_OrderScheduleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RequestDetailActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusResponseList.size();
    }
}
