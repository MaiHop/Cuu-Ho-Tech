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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.ServiceResponse;
import com.example.cuu_ho_tech.Presentation.Fragment.BottomSheetCartDetailEditFragment;
import com.example.cuu_ho_tech.Presentation.ViewHolder.ServiceViewHolder;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.databinding.ItemOrderServiceListBinding;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {
    private FragmentManager fragmentManager;
    private List<ServiceResponse> serviceResponseList;
    private Context context;
    private Button btn_created_service;
    final Double[] sum = {0.0};
    final int[] countChecked = {0};
    public ServiceAdapter(List<ServiceResponse> serviceResponseList, Context context, FragmentManager fragmentManager, Button btn_created_service){
        this.serviceResponseList = serviceResponseList;
        this.context = context;
        this.btn_created_service = btn_created_service;
        this.fragmentManager = fragmentManager;
    }
    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemOrderServiceListBinding binding = ItemOrderServiceListBinding.inflate(inflater, parent, false);

        return new ServiceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvNameService.setText(serviceResponseList.get(position).getName_service());
        holder.binding.tvPriceService.setText(""+serviceResponseList.get(position).getPrice_service()+"đ");
        holder.binding.cbService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        holder.binding.imgEditService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetCartDetailEditFragment cartDetailEditFragment = new BottomSheetCartDetailEditFragment(context, "Test", 1);
                cartDetailEditFragment.setOnClickListener(new BottomSheetInterface.OnClickEditListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String cart_detail, int position) {
                        Toast.makeText(context," "+position, Toast.LENGTH_SHORT).show();
                        bottomsheet.dismiss();
                    }
                });

                cartDetailEditFragment.show(fragmentManager, cartDetailEditFragment.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceResponseList.size();
    }
}
