package com.example.cuu_ho_tech.Presentation.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.PaymentResponse;
import com.example.cuu_ho_tech.Presentation.Adapter.PaymentMethodAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityLoginBinding;
import com.example.cuu_ho_tech.databinding.ActivityPaymentMethodBinding;

import java.util.Arrays;
import java.util.List;

public class PaymentMethodActivity extends AppCompatActivity {
    ActivityPaymentMethodBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityPaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = binding.rcvPaymentMethod;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<PaymentResponse> paymentResponseList = Arrays.asList(
                new PaymentResponse(1, "Tiền mặt"),
                new PaymentResponse(2, "Thẻ tín dụng")
        );
        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, paymentResponseList);
        recyclerView.setAdapter(paymentMethodAdapter);

        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}