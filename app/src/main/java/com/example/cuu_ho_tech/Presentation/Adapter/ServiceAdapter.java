package com.example.cuu_ho_tech.Presentation.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.ServiceResponse;
import com.example.cuu_ho_tech.Presentation.ViewHolder.ServiceViewHolder;
import com.example.cuu_ho_tech.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {
    private List<ServiceResponse> serviceResponseList;
    private Context context;
    private Button btn_created_service;
    final Double[] sum = {0.0};
    final int[] countChecked = {0};
    public ServiceAdapter(List<ServiceResponse> serviceResponseList, Context context, Button btn_created_service){
        this.serviceResponseList = serviceResponseList;
        this.context = context;
        this.btn_created_service = btn_created_service;
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_service_list, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name_service.setText(serviceResponseList.get(position).getName_service());
        holder.tv_price_service.setText(""+serviceResponseList.get(position).getPrice_service()+"đ");
        holder.cb_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sum[0] += serviceResponseList.get(position).getPrice_service();
                    countChecked[0]++;
                    Toast.makeText(context, "checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    sum[0] -= serviceResponseList.get(position).getPrice_service();
                    countChecked[0]--;
                    Toast.makeText(context, "unchecked", Toast.LENGTH_SHORT).show();
                }
                btn_created_service.setText("TẠO ĐƠN ĐẶT LỊCH ("+countChecked[0]+") - "+sum[0]+"đ");
                if (countChecked[0] > 0) {
                    btn_created_service.setVisibility(View.VISIBLE); // Hiện nút nếu có ít nhất một checkbox được chọn
                } else {
                    btn_created_service.setVisibility(View.GONE); // Ẩn nút nếu không có checkbox nào được chọn
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceResponseList.size();
    }
}
