package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.OrderScheduleListAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.SearchServiceAndOrderAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.ServiceHomeAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.BaseActivity;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedSearchServiceAndOrderViewModel;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivitySearchServiceAndOrderBinding;

import java.util.Objects;

public class SearchServiceAndOrderActivity extends BaseActivity {

    private ActivitySearchServiceAndOrderBinding binding;
    private SharedSearchServiceAndOrderViewModel sharedSearchServiceAndOrderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchServiceAndOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BaseActivity.setOnConnectListener(this);// TODO:
        EdgeToEdge.enable(this);
        initialize();
    }

    private void initialize() {
        listSearchServiceAndOrderAdapter();
        listServiceAdapter();
        listOrderAdapter();
        sharedSearchServiceAndOrderViewModel = new ViewModelProvider(SearchServiceAndOrderActivity.this).get(SharedSearchServiceAndOrderViewModel.class);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtText.clearFocus();
                binding.listSearch.setVisibility(View.GONE);
                binding.mainLayout.setVisibility(View.VISIBLE);
            }
        });
        binding.edtText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.mainLayout.setVisibility(View.GONE);
                binding.listSearch.setVisibility(View.VISIBLE);
                if(!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
                    binding.btnDelete.setVisibility(View.VISIBLE);
            } else  {
                hideKeyboard(v);
                binding.btnDelete.setVisibility(View.GONE);
            }
        });
        binding.edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
                    binding.btnDelete.setVisibility(View.VISIBLE);
                else  binding.btnDelete.setVisibility(View.GONE);
                sharedSearchServiceAndOrderViewModel.setTextActFromFra(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnDelete.setVisibility(View.GONE);
                binding.edtText.setText("");
            }
        });
        binding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnAll.setEnabled(false);
                binding.btnService.setEnabled(true);
                binding.btnApplication.setEnabled(true);

                binding.layoutListService.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);
                binding.layoutListOrder.setVisibility(View.VISIBLE);
            }
        });
        binding.btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnService.setEnabled(false);
                binding.btnAll.setEnabled(true);
                binding.btnApplication.setEnabled(true);

                binding.layoutListService.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.GONE);
                binding.layoutListOrder.setVisibility(View.GONE);
            }
        });
        binding.btnApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnApplication.setEnabled(false);
                binding.btnAll.setEnabled(true);
                binding.btnService.setEnabled(true);

                binding.layoutListService.setVisibility(View.GONE);
                binding.view.setVisibility(View.GONE);
                binding.layoutListOrder.setVisibility(View.VISIBLE);
            }
        });
        binding.btnListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.btnListService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sharedSearchServiceAndOrderViewModel.getTextFromItem().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String inputText) {
                Toast.makeText(SearchServiceAndOrderActivity.this, inputText, Toast.LENGTH_SHORT).show();
                binding.edtText.setText(inputText);
                binding.edtText.setSelection(inputText.length());
            }
        });
        binding.edtText.requestFocus();
    }

    private void listOrderAdapter() {
        String[][] orders = {
                {"Gara - Q7", "123 Nguyễn Thị Thập, Tân Phong, Quận 7, tp.HCM", "11 thg 07 2024, 09:09", "34C-970.22", "3 dịch vụ", "2.000.000", "0"},
                {"Gara - Q2", "456 Lê Văn Sỹ, Phường 10, Quận 3, tp.HCM", "12 thg 07 2024, 10:30", "52B-123.45", "2 dịch vụ", "1.500.000", "1"},
                {"Gara - Q9", "789 Hoàng Văn Thụ, Phường 2, Quận Tân Bình, tp.HCM", "13 thg 07 2024, 11:45", "81A-456.78", "4 dịch vụ", "2.500.000", "2"},
                {"Gara - Q3", "101 Nguyễn Văn Trỗi, Phường 12, Quận Phú Nhuận, tp.HCM", "14 thg 07 2024, 08:15", "29C-789.01", "5 dịch vụ", "3.000.000", "3"},
                {"Gara - Q5", "202 Cách Mạng Tháng 8, Phường 7, Quận Tân Bình, tp.HCM", "15 thg 07 2024, 14:00", "67D-234.56", "3 dịch vụ", "2.200.000", "4"},
                {"Gara - Q10", "303 Trần Hưng Đạo, Phường 6, Quận 5, tp.HCM", "16 thg 07 2024, 16:25", "98E-345.67", "2 dịch vụ", "1.800.000", "3"}
        };
        OrderScheduleListAdapter adapter = new OrderScheduleListAdapter(orders);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false);
        binding.listOrder.setLayoutManager(layoutManager);
        binding.listOrder.setAdapter(adapter);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void listServiceAdapter() {
        String[][] services = {
                {String.valueOf(R.drawable.image_car_repair), String.valueOf(R.drawable.ic_car_gear), "Sửa xe", "2.000.000VNĐ", "4"},
                {String.valueOf(R.drawable.image_charging), String.valueOf(R.drawable.ic_car_charging), "Sạc điện", "2.000.000VNĐ", "5"},
                {String.valueOf(R.drawable.image_change_tire), String.valueOf(R.drawable.ic_car_tire), "Thay lốp", "2.000.000VNĐ", "4.5"},
                {String.valueOf(R.drawable.image_oil_change), String.valueOf(R.drawable.ic_car_gas_tank), "Thay nhớt", "2.000.000VNĐ", "4"},
                {String.valueOf(R.drawable.image_car_wash), String.valueOf(R.drawable.ic_car_wash), "Rửa xe", "2.000.000VNĐ", "3.5"},
                {String.valueOf(R.drawable.image_car_modification), String.valueOf(R.drawable.ic_car_modification), "Độ xe", "2.000.000VNĐ", "4"}
        };
        ServiceHomeAdapter adapter = new ServiceHomeAdapter(services, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.listService.setLayoutManager(layoutManager);
        binding.listService.setAdapter(adapter);
    }

    private void listSearchServiceAndOrderAdapter() {
        String[][] data = {
                {"Thay kính", "1"},
                {"Thay xi nhan", "0"},
                {"Thay kính chắn gió", "1"},
                {"Cứu hộ", "0"},
                {"Sửa xe vip", "0"},
                {"Rửa xe", "1"}
        };
        SearchServiceAndOrderAdapter adapter = new SearchServiceAndOrderAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false);
        binding.listSearch.setLayoutManager(layoutManager);
        binding.listSearch.setAdapter(adapter);
    }
}