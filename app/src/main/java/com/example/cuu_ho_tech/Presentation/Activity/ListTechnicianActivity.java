package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import com.example.cuu_ho_tech.Presentation.Fragment.BottomSheetDialogCustomFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.ListLocationFragment;
import com.example.cuu_ho_tech.Presentation.Fragment.ListTechnicianStatusFilterFragment;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ActivityListTechnicianBinding;

import java.util.ArrayList;
import java.util.List;

public class ListTechnicianActivity extends AppCompatActivity {
    ActivityListTechnicianBinding binding;

    int selected_status_position = 0;

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
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        Log.d("LIST_TECH", "" + list.size());
        //Hiển ds thợ trong rv của bottomsheet
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(ListTechnicianActivity.this, list, new ClickListener() {
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
        List<String> list_status = new ArrayList<>();
        list_status.add("Mặc định");
        list_status.add("Hoạt động");
        list_status.add("Đóng cửa");
        BottomSheetDialogCustomFragment bsd_status = new BottomSheetDialogCustomFragment(ListTechnicianActivity.this)
                .setTitle("Trạng thái");
        binding.btnListTechnicianStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsd_status.setListOption(list_status, selected_status_position, new BottomSheetInterface.OnClickListListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                        bottomsheet.dismiss();
                        selected_status_position = position;
                        btn_selected(binding.btnListTechnicianStatus, data);
                    }
                });
                bsd_status.show(getSupportFragmentManager(), bsd_status.getTag());
            }
        });

        //Tỉnh/Thành
        binding.btnListTechnicianProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogCustomFragment bsd_province = new BottomSheetDialogCustomFragment(ListTechnicianActivity.this)
                        .setTitle("Tỉnh/Thành")
                        .setListItem(list, new BottomSheetInterface.OnClickListListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                                bottomsheet.dismiss();
                                btn_selected(binding.btnListTechnicianProvince, data);
                                btn_unselected(binding.btnListTechnicianDistrict, "Quận/Huyện");
                                btn_unselected(binding.btnListTechnicianCommune, "Phường/Xã");
                            }
                        })
                        .setSearchListItem();
                bsd_province.show(getSupportFragmentManager(), bsd_province.getTag());
            }
        });
        //Quận/Huyện
        binding.btnListTechnicianDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogCustomFragment bsd_district = new BottomSheetDialogCustomFragment(ListTechnicianActivity.this)
                        .setTitle("Quận/Huyện")
                        .setListItem(list, new BottomSheetInterface.OnClickListListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                                bottomsheet.dismiss();
                                btn_selected(binding.btnListTechnicianDistrict, data);
                                btn_unselected(binding.btnListTechnicianCommune, "Phường/Xã");
                            }
                        })
                        .setSearchListItem();
                bsd_district.show(getSupportFragmentManager(), bsd_district.getTag());
            }
        });
        //Phường/Xã
        binding.btnListTechnicianCommune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogCustomFragment bsd_district = new BottomSheetDialogCustomFragment(ListTechnicianActivity.this)
                        .setTitle("Phường/Xã")
                        .setListItem(list, new BottomSheetInterface.OnClickListListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                                bottomsheet.dismiss();
                                btn_selected(binding.btnListTechnicianCommune, data);
                            }
                        })
                        .setSearchListItem();
                bsd_district.show(getSupportFragmentManager(), bsd_district.getTag());
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

    private void btn_selected(Button btn, String text) {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_down); // Thay ic_your_icon bằng tên drawable của bạn
        drawable.setColorFilter(ContextCompat.getColor(ListTechnicianActivity.this, R.color.primary_main), PorterDuff.Mode.SRC_IN);
        btn.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        btn.setText(text);
        btn.setTextColor(ContextCompat.getColor(ListTechnicianActivity.this, R.color.button_color_primary));
        btn.setBackground(ContextCompat.getDrawable(ListTechnicianActivity.this, R.drawable.custom_button_primary_outline));

    }

    private void btn_unselected(Button btn, String text) {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_down); // Thay ic_your_icon bằng tên drawable của bạn
        drawable.setColorFilter(ContextCompat.getColor(ListTechnicianActivity.this, R.color.neutral_main), PorterDuff.Mode.SRC_IN);
        btn.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        btn.setText(text);
        btn.setTextColor(ContextCompat.getColor(ListTechnicianActivity.this, R.color.neutral_main));
        btn.setBackground(ContextCompat.getDrawable(ListTechnicianActivity.this, R.drawable.button_outline_disabled));
    }
}