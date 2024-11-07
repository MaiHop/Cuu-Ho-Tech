package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.R;

public class ScheduleViewHolder extends RecyclerView.ViewHolder{
    public TextView completed;
    public LinearLayout ll_OrderScheduleList;
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        completed = itemView.findViewById(R.id.tv_status_schedule);
        ll_OrderScheduleList = itemView.findViewById(R.id.ll_OrderScheduleList);
    }
}
