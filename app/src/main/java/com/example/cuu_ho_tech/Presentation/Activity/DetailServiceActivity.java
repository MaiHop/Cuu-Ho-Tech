package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.DetailServiceAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.Presentation.Fragment.BottomSheetDialogCustomFragment;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.databinding.ActivityDetailServiceBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailServiceActivity extends AppCompatActivity {
    ActivityDetailServiceBinding binding;

    private int time_sort_position=0,rating_sort_position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();
    }

    private void initialize() {
        listDetailServiceAdapter();
        binding.ivPackDetailAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnPrimaryOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog().setLoading(true);
                customDialog.show(getSupportFragmentManager(), "dialog");
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        customDialog.cancel();
                        CustomDialog dialog = new CustomDialog()
                                .setType(CustomDialog.SUCCESS)
                                .setTitle("Thêm thành công")
                                .setText("Bạn có thể xem dịch vụ được lưu của bạn tại  danh mục Hoạt động")
                                .setTextBtn("XEM DANH SÁCH")
                                .setTextBtnOutline("TRANG CHỦ")
                                .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
                                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                                    @Override
                                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                        btnOutline.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                    }
                                });
                        dialog.show(getSupportFragmentManager(), "CustomDialog");
                    }
                };
                handler.postDelayed(runnable, 5000);
            }
        });
        binding.btnPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog().setLoading(true);
                customDialog.show(getSupportFragmentManager(), "dialog");
                Intent intent = new Intent(DetailServiceActivity.this, OrderRescueActivity.class);
                startActivity(intent);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        customDialog.cancel();
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        });

        //Button sắp xếp review theo thời gian
        List<String> list_time = new ArrayList<>();
        list_time.add("Mới nhất");
        list_time.add("Cũ nhất");
        BottomSheetDialogCustomFragment bsd_sortbytime = new BottomSheetDialogCustomFragment(DetailServiceActivity.this)
                .setTitle("Lọc theo thời gian");
        binding.btnPrimaryIconOutline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsd_sortbytime.setListOption(list_time, time_sort_position, new BottomSheetInterface.OnClickListListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                        bottomsheet.dismiss();
                        time_sort_position = position;
                        binding.btnPrimaryIconOutline.setText(data);
                    }
                });
                bsd_sortbytime.show(getSupportFragmentManager(), bsd_sortbytime.getTag());
            }
        });

        //Button sắp xếp review theo thời gian
        List<String> list_rating = new ArrayList<>();
        list_rating.add("Đánh giá cao nhất");
        list_rating.add("Đánh giá thấp nhất");
        BottomSheetDialogCustomFragment bsd_sortbyrate = new BottomSheetDialogCustomFragment(DetailServiceActivity.this)
                .setTitle("Lọc theo đánh giá");
        binding.btnPrimaryIconOutline1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsd_sortbyrate.setListOption(list_rating, rating_sort_position, new BottomSheetInterface.OnClickListListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                        Toast.makeText(DetailServiceActivity.this,"" +position,Toast.LENGTH_SHORT).show();
                        bottomsheet.dismiss();
                        rating_sort_position = position;
                        changeButtonstatus(binding.btnPrimaryIconOutline1, data);
                    }
                });
                bsd_sortbyrate.show(getSupportFragmentManager(), bsd_sortbyrate.getTag());
            }
        });


    }


    private void changeButtonstatus(AppCompatTextView textView, String text){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_down); // Thay ic_your_icon bằng tên drawable của bạn
        drawable.setColorFilter(ContextCompat.getColor(DetailServiceActivity.this, R.color.primary_main), PorterDuff.Mode.SRC_IN);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        textView.setText(text);
        textView.setTextColor(ContextCompat.getColor(DetailServiceActivity.this, R.color.button_color_primary));
        textView.setBackground(ContextCompat.getDrawable(DetailServiceActivity.this, R.drawable.custom_button_primary_outline));
    }

    private void listDetailServiceAdapter() {
        String[][] data = {
                {"", "Hùng Style", "11 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Đăng Mister", "12 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Dũng FC", "13 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Mai Hòa Hợp", "14 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Con Mèo", "15 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."},
                {"", "Con Trâu", "16 thg 07 2024", "Supporting line text lorem ipsum dolor sit amet, consectetur."}
        };
        DetailServiceAdapter adapter = new DetailServiceAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false);
        binding.rcvComplentDetailService.setLayoutManager(layoutManager);
        binding.rcvComplentDetailService.setAdapter(adapter);
    }
}