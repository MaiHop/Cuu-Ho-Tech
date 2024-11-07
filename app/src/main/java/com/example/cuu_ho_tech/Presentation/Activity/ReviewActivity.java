package com.example.cuu_ho_tech.Presentation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.ActivityRequestDetailBinding;
import com.example.cuu_ho_tech.databinding.ActivityReviewBinding;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    ActivityReviewBinding binding;
    private List<ImageButton> list_star = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Method4UI.hidehint(binding.edtReviewComment, binding.tilReviewComment, "Viết nhận xét ở đây");
        checkInternet();

        innit();

    }

    private void btnRequestDetailReview_Event() {
        //TODO: loading
        CustomDialog dialog_loading = new CustomDialog().setLoading(true);
        dialog_loading.show(getSupportFragmentManager(), "CustomDialog");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dialog_loading.cancel();
                //        TODO: đăng ký thành công
                CustomDialog dialog_confirm = new CustomDialog();
                dialog_confirm.setType(CustomDialog.SUCCESS)
                        .setTitle("Đã gửi đánh giá")
                        .setText("Cảm ơn bạn đã sử dụng dịch vụ chúng tôi")
                        .setTypeLayoutBtn(CustomDialog.CENTER)
                        .setTextBtnOutline("TRANG CHỦ")
                        .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                            @Override
                            public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
                                super.onViewReadyOneBtn(btnOutline);
                                btnOutline.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(ReviewActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            }
                        });
                dialog_confirm.show(getSupportFragmentManager(), "send_request_success");
                dialog_confirm.cancel();
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void innit() {
        list_star.add(binding.btnReviewStar1);
        list_star.add(binding.btnReviewStar2);
        list_star.add(binding.btnReviewStar3);
        list_star.add(binding.btnReviewStar4);
        list_star.add(binding.btnReviewStar5);

        binding.btnReviewStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratting(5);
            }
        });

        binding.btnReviewStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratting(4);
            }
        });

        binding.btnReviewStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratting(3);
            }
        });

        binding.btnReviewStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratting(2);
            }
        });

        binding.btnReviewStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratting(1);
            }
        });

        binding.btnRequestDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(ReviewActivity.this)) {
                    btnRequestDetailReview_Event();
                } else {
                    CustomDialog dialog = new CustomDialog();
                    dialog.setType(CustomDialog.DISCONNECT)
                            .setTitle("Lỗi")
                            .setText("Không có kết nối Internet?")
                            .setTextBtn("ĐÓNG")
                            .setTextBtnOutline("THỬ LẠI")
                            .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT)
                            .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                                @Override
                                public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                    super.onViewReadyTwoBtn(btn, btnOutline);
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            btnRequestDetailReview_Event();
                                        }
                                    });
                                    btnOutline.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.cancel();
                                        }
                                    });
                                }
                            });
                    dialog.show(getSupportFragmentManager(), "CustomDialog");
                }
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.llNoInternet.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternet();
            }
        });
    }

    private void ratting(int i) {
        for (int j =0 ; j<i;j++){
            ImageButton ib = list_star.get(j);
            ib.setColorFilter(ContextCompat.getColor(this, R.color.warning_main));
        }
        for (int j =i ; j<5;j++){
            ImageButton ib = list_star.get(j);
            ib.setColorFilter(ContextCompat.getColor(this, R.color.neutral_focus));
        }
    }

    private void checkInternet() {
        if (CheckNetwork.isAvailable(ReviewActivity.this)) {
            binding.rlReview.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
        } else {
            binding.rlReview.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
        }
    }
}