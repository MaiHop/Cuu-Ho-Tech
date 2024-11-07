package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianSearchAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.Presentation.Fragment.ListLocationFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.ListTechnicianStatusFilterFragment;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ActivityListTechnicianBinding;

import java.util.ArrayList;
import java.util.List;

public class ListTechnicianActivity extends AppCompatActivity {
    ActivityListTechnicianBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTechnicianBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkInternet();
        setupListData();

    }

    private void setupListData() {
        List<String> list = new ArrayList<>();
        for(int i =0; i<=10;i++){
            list.add("Tech "+i);
        }
        Log.d("LIST_TECH", ""+list.size());
        //Hiển ds thợ trong rv của bottomsheet
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(ListTechnicianActivity.this,list, new ClickListener() {
            @Override
            public void clickItem(String tech) {

                Toast.makeText(ListTechnicianActivity.this, tech, Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvListTechnician.setLayoutManager(new LinearLayoutManager(ListTechnicianActivity.this));
        binding.rvListTechnician.setAdapter(adapter);

        //Chuyển sang tìm trên bản đồ
        binding.btnListTechnicianGolisttechnicianmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListTechnicianActivity.this, ListTechnicianMapActivity.class);
                startActivity(i);
            }
        });

        //Trạng thái
        binding.btnListTechnicianStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListTechnicianStatusFilterFragment listTechnicianStatusFilterFragment = new ListTechnicianStatusFilterFragment(ListTechnicianActivity.this, new ClickListener() {
                    @Override
                    public void clickItem(String name) {
                        Toast.makeText(ListTechnicianActivity.this, name, Toast.LENGTH_SHORT).show();
                        btn_selected(binding.btnListTechnicianStatus, name);
                    }
                });

                listTechnicianStatusFilterFragment.show(getSupportFragmentManager(), listTechnicianStatusFilterFragment.getTag());
            }
        });

        //Tỉnh/Thành
        binding.btnListTechnicianProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLocationFragment listLocationFragment = new ListLocationFragment(ListTechnicianActivity.this,"Tỉnh/Thành",list, new ClickListener() {
                    @Override
                    public void clickItem(String name) {
                        if(name!= null){
                            Toast.makeText(ListTechnicianActivity.this, name, Toast.LENGTH_SHORT).show();
                            //Cập nhật trạng thái button
                            btn_selected(binding.btnListTechnicianProvince, name);
                            btn_unselected(binding.btnListTechnicianDistrict, "Quận/Huyện");
                            btn_unselected(binding.btnListTechnicianCommune,"Phường/Xã");
                        }
                    }
                });

                listLocationFragment.show(getSupportFragmentManager(), listLocationFragment.getTag());
            }
        });
        //Quận/Huyện
        binding.btnListTechnicianDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLocationFragment listLocationFragment = new ListLocationFragment(ListTechnicianActivity.this,"Quận/Huyện",list, new ClickListener() {
                    @Override
                    public void clickItem(String name) {
                        if(name!= null){
                            Toast.makeText(ListTechnicianActivity.this, name, Toast.LENGTH_SHORT).show();
                            //Cập nhật trạng thái button
                            btn_selected(binding.btnListTechnicianDistrict, name);
                            btn_unselected(binding.btnListTechnicianCommune,"Phường/Xã");
                        }
                    }
                });

                listLocationFragment.show(getSupportFragmentManager(), listLocationFragment.getTag());
            }
        });
        //Phường/Xã
        binding.btnListTechnicianCommune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListLocationFragment listLocationFragment = new ListLocationFragment(ListTechnicianActivity.this,"Phường/Xã",list, new ClickListener() {
                    @Override
                    public void clickItem(String name) {
                        if(name!= null){
                            Toast.makeText(ListTechnicianActivity.this, name, Toast.LENGTH_SHORT).show();
                            //Cập nhật trạng thái button
                            btn_selected(binding.btnListTechnicianCommune, name);
                        }
                    }
                });

                listLocationFragment.show(getSupportFragmentManager(), listLocationFragment.getTag());
            }
        });

        //Xác nhận chọn Tech
        binding.btnListTechnicianConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Load lại activity
        binding.llNoInternet.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternet();
            }
        });
    }

    private void checkInternet() {
        if (CheckNetwork.isAvailable(ListTechnicianActivity.this)) {
            binding.rlListTechnician.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
        } else {
            binding.rlListTechnician.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private void btn_selected(Button btn, String name){
        btn.setText(name);
        btn.setTextColor(ContextCompat.getColor(ListTechnicianActivity.this, R.color.primary_main));
        btn.setBackground(ContextCompat.getDrawable(ListTechnicianActivity.this, R.drawable.button_primary_outline_enabled));
    }
    private void btn_unselected(Button btn, String name){
        btn.setText(name);
        btn.setTextColor(ContextCompat.getColor(ListTechnicianActivity.this, R.color.neutral_main));
        btn.setBackground(ContextCompat.getDrawable(ListTechnicianActivity.this, R.drawable.button_outline_disabled));
    }
}