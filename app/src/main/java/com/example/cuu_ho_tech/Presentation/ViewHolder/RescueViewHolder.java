package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.R;

public class RescueViewHolder extends RecyclerView.ViewHolder{
    public TextView completed;
    public LinearLayout ll_OrderScheduleList;
    public RescueViewHolder(@NonNull View itemView) {
        super(itemView);
        completed = itemView.findViewById(R.id.tv_status_rescue);
        ll_OrderScheduleList = itemView.findViewById(R.id.ll_OrderRescueList);
    }
}
