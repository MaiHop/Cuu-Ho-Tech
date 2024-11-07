package com.example.cuu_ho_tech.Presentation.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.PaymentResponse;
import com.example.cuu_ho_tech.Presentation.Adapter.PaymentMethodAdapter;
import com.example.cuu_ho_tech.R;

public class PaymentMethodViewHolder extends  RecyclerView.ViewHolder{
    TextView tv_payment_name;
    ImageView img_payment_method, img_checked_payment;
    PaymentMethodAdapter paymentMethodAdapter;
    String payment_method;
    public PaymentMethodViewHolder(@NonNull View itemView, PaymentMethodAdapter paymentMethodAdapter) {
        super(itemView);
        tv_payment_name = itemView.findViewById(R.id.tv_payment_name);
        img_payment_method = itemView.findViewById(R.id.img_payment_method);
        img_checked_payment = itemView.findViewById(R.id.img_checked_payment);
        this.paymentMethodAdapter = paymentMethodAdapter;

       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position = getAdapterPosition();
               if (position != RecyclerView.NO_POSITION) {
                   paymentMethodAdapter.updateSelectedPosition(position);

                   Toast.makeText(itemView.getContext(), payment_method, Toast.LENGTH_SHORT).show();
               }
               else {
               }
           }
       });
    }

    public void bind(PaymentResponse paymentResponse, boolean isSelected) {
        if (paymentResponse.getPayment_method().equals("Tiền mặt")) {
            img_payment_method.setImageResource(R.drawable.ic_dollar);
        } else {
            img_payment_method.setImageResource(R.drawable.ic_card1);
            img_payment_method.setBackgroundResource(R.drawable.background_image_card_);
        }
        img_checked_payment.setVisibility(isSelected ? View.VISIBLE : View.INVISIBLE);
        tv_payment_name.setText(paymentResponse.getPayment_method());
        payment_method = paymentResponse.getPayment_method();
    }
}
