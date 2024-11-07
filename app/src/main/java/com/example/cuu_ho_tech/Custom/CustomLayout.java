package com.example.cuu_ho_tech.Custom;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.cuu_ho_tech.Custom.ConnectEffect.Connect;
import com.example.cuu_ho_tech.Custom.PulseEffect.PulseView;
import com.example.cuu_ho_tech.Custom.SwipeBtn.SwipeButton;
import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityCustomButtonLayoutBinding;



public class CustomLayout extends AppCompatActivity implements
        View.OnClickListener,
        PulseView.PulseListener {
    ActivityCustomButtonLayoutBinding binding;
    private SwipeButton swipeBtnEnabled;
    private SwipeButton swipeNoState;
    private Button toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomButtonLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();

//        binding.btnPrimary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.edtPhonelayout.setError("Vui lòng chọn ngày");
//            }
//        });
//
//        binding.swipeNoState.setEnabled(false);
//        binding.swipeNoState.setBackgroundResource(R.drawable.background_disable_swipe);
    }

    private void initialize() {
//        TODO: loading
//        CustomDialog customDialog = new CustomDialog().setLoading(true);
//        customDialog.show(getSupportFragmentManager(), "CustomDialog");
//        TODO: đăng ký thành công
//        CustomDialog dialog = new CustomDialog()
//                .setType(CustomDialog.SUCCESS)
//                .setTitle("Đăng ký thành công")
//                .setText("Bạn đã là thành viên của Rescue")
//                .setIconLoading(true);
//        dialog.show(getSupportFragmentManager(), "CustomDialog");
//        dialog.cancel();
//        TODO: thêm thành công
//        CustomDialog dialog = new CustomDialog()
//                .setType(CustomDialog.SUCCESS)
//                .setTitle("Thêm thành công")
//                .setText("Bạn có thể xem dịch vụ được lưu của bạn tại  danh mục Hoạt động")
//                .setTextBtn("XEM DANH SÁCH")
//                .setTextBtnOutline("TRANG CHỦ")
//                .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
//        .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//            @Override
//            public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                btnOutline.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//            }
//        });
//         dialog.show(getSupportFragmentManager(), "CustomDialog");
//         dialog.cancel();
//        TODO: đã hủy đơn
//        CustomDialog dialog = new CustomDialog()
//                .setType(CustomDialog.SUCCESS)
//                .setTitle("Đã hủy đơn")
//                .setTextBtnOutline("ĐÓNG")
//                .setTypeLayoutBtn(CustomDialog.CENTER)
//                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                    @Override
//                    public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
//                        super.onViewReadyOneBtn(btnOutline);
//                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                    }
//                });
//        dialog.show(getSupportFragmentManager(), "CustomDialog");
//
//        TODO: cảnh báo
//        CustomDialog dialog = new CustomDialog()
//                .setType(CustomDialog.WARNING)
//                .setTitle("Cảnh báo")
//                .setText("Bạn có chắc hủy đơn không?")
//                .setTextBtn("KHÔNG")
//                .setTextBtnOutline("CÓ")
//                .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT)
//                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                    @Override
//                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
//                        super.onViewReadyTwoBtn(btn, btnOutline);
//                        btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                    }
//                });
//        dialog.show(getSupportFragmentManager(), "CustomDialog");
//        TODO: lỗi kết nối wifi
        CustomDialog dialog = new CustomDialog()
                .setType(CustomDialog.DISCONNECT)
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
//        TODO: Thông báo có listView
//        CustomDialog dialog = new CustomDialog(this)
//                .setTitle("Thông báo")
//                .setText("Dịch vụ mới được thêm vào")
//                .setListView(true)
//                .setTextBtnOutline("ĐÓNG")
//                .setTypeLayoutBtn(CustomDialog.CENTER)
//                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                    @Override
//                    public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
//                        super.onViewReadyOneBtn(btnOutline);
//                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onReadyListView(RecyclerView recyclerView) {
//                        super.onReadyListView(recyclerView);
//                    }
//                });
//        dialog.show(getSupportFragmentManager(), "CustomDialog");
//        TODO: đơn cứu hộ mới
//        CustomDialog dialog = new CustomDialog()
//                .setType(CustomDialog.NOTIFICATION)
//                .setTitle("Đơn cứu hộ mới")
//                .setTextBtn("TỪ CHỐI")
//                .setTextBtnOutline("XÁC NHẬN")
//                .setBtnDrawableOutline(R.drawable.custom_button_danger_outline)
//                .setTextDrawableOutline(R.color.button_color_danger)
//                .setBack(true)
//                .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT)
//                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                    @Override
//                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
//                        super.onViewReadyTwoBtn(btn, btnOutline);
//                        btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                    }
//                });
//        dialog.show(getSupportFragmentManager(), "CustomDialog");
//        TODO: xác nhận
//        CustomDialog dialog = new CustomDialog(this)
//                .setType(CustomDialog.AVATAR_WRENCH)
//                .setTitle("Đã có người xác nhận rồi")
//                .setText("Cảm ơn bạn đã chú ý")
//                .setTextBtnOutline("ĐÓNG")
//                .setTitleDrawable(R.color.success_main)
//                .setBtnDrawableOutline(R.drawable.custom_button_success_outline)
//                .setTextDrawableOutline(R.color.button_color_success)
//                .setTypeLayoutBtn(CustomDialog.CENTER)
//                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                    @Override
//                    public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
//                        super.onViewReadyOneBtn(btnOutline);
//                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
//                    }
//                });
//        dialog.show(getSupportFragmentManager(), "CustomDialog");

        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                dialog.cancel();
//            }
//        };
//        handler.postDelayed(runnable, 5000);
        binding.pulseRedAlert.setPulseListener(this);
        binding.pulseRedAlert.setOnClickListener(this);
        binding.btnLayoutConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connect.animateWidth(view, binding.textNote);
            }
        });

    }

    private int mCounter;

    @Override
    public void onClick(final View v) {
        if (mCounter++ % 2 == 0) binding.pulseRedAlert.startPulse();
        else binding.pulseRedAlert.finishPulse();
    }

    @Override
    public void onStartPulse() {
        Toast.makeText(CustomLayout.this, "Start pulse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishPulse() {
        Toast.makeText(CustomLayout.this, "Finish pulse", Toast.LENGTH_SHORT).show();
    }
}