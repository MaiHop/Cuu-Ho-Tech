package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.PaymentResponse;
import com.example.cuu_ho_tech.Presentation.ViewHolder.PaymentMethodViewHolder;
import com.example.cuu_ho_tech.R;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodViewHolder>{
    private List<PaymentResponse> paymentResponseList;
    private int selectedPosition = -1;
    private Context context;
    public PaymentMethodAdapter(Context context,List<PaymentResponse> paymentResponseList) {
        this.paymentResponseList = paymentResponseList;

        // Tìm item có id = 1 để chọn mặc định
        for (int i = 0; i < paymentResponseList.size(); i++) {
            if (paymentResponseList.get(i).getPayment_method().equals("Tiền mặt")) { // Giả sử id = 1 là "Tiền mặt"
                selectedPosition = i;
                String a = paymentResponseList.get(i).getPayment_method();
                Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false);
        return new PaymentMethodViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentResponse paymentResponse = paymentResponseList.get(position);
        holder.bind(paymentResponse, position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return paymentResponseList.size();
    }

    public void updateSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Refresh list to reflect selection
    }
}
