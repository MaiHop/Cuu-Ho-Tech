package com.example.cuu_ho_tech.Presentation.Activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.ServiceRequestDetailAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.ActivityMainBinding;
import com.example.cuu_ho_tech.databinding.ActivityRegisterBinding;
import com.example.cuu_ho_tech.databinding.ActivityRequestDetailBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RequestDetailActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 104;
    ActivityRequestDetailBinding binding;
    private Uri image = null;
    private boolean cancel_flag;
    private String cancel_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRequestDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkInternet();

        innit();
        showlistservice();


    }

    private void showlistservice() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        Log.d("LIST_TECH", "" + list.size());

        ServiceRequestDetailAdapter adapter = new ServiceRequestDetailAdapter(RequestDetailActivity.this, list, new ClickListener() {
            @Override
            public void clickItem(String service) {
                Toast.makeText(RequestDetailActivity.this, service, Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvRequestDetailListService.setLayoutManager(new LinearLayoutManager(RequestDetailActivity.this));
        binding.rvRequestDetailListService.setAdapter(adapter);
    }

    private void btnRequestDetailBooking_Event() {
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
                        .setTitle("Yêu cầu đặt lịch thành công")
                        .setText("Vui lòng chú ý thông báo hoặc xem Yêu cầu cứu hộ ở danh mục Hoạt động")
                        .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
                        .setTextBtn("THEO DÕI YÊU CẦU")
                        .setTextBtnOutline("TRANG CHỦ")
                        .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                            @Override
                            public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog_confirm.cancel();
                                    }
                                });
                                btnOutline.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(RequestDetailActivity.this, MainActivity.class);
                                        finish();
                                        startActivity(i);
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

    private void btnRequestDetailSave_Event() {
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
                        .setTitle("Lưu yêu cầu thành công")
                        .setText("Vui lòng chú ý thông báo hoặc xem Yêu cầu cứu hộ ở danh mục Hoạt động")
                        .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
                        .setTextBtn("THEO DÕI YÊU CẦU")
                        .setTextBtnOutline("TRANG CHỦ")
                        .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                            @Override
                            public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog_confirm.cancel();
                                    }
                                });
                                btnOutline.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(RequestDetailActivity.this, MainActivity.class);
                                        finish();
                                        startActivity(i);
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

    private void btnRequestDetailCancel_Event() {
        CustomDialog dialog = new CustomDialog();
        dialog.setType(CustomDialog.WARNING)
                .setTitle("Cảnh báo")
                .setText("Bạn có chắc hủy đơn không?")
                .setTextBtn("KHÔNG")
                .setTextBtnOutline("CÓ")
                .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT)
                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                    @Override
                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                        super.onViewReadyTwoBtn(btn, btnOutline);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(RequestDetailActivity.this, ConfirmCancellationActivity.class);
                                startActivity(i);
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

    private void btnRequestDetailGoToMapDetail_Event() {
        Intent i = new Intent(RequestDetailActivity.this, TechnicianLocationMapActivity.class);
        startActivity(i);
    }

    private void innit() {
        Method4UI.hidehint(binding.edtRequestDetailLicensePlate, binding.tilRequestDetailLicensePlate, "Nhập biển số xe");
        Method4UI.hidehint(binding.edtRequestDetailDescription, binding.tilRequestDetailDescription, "Mô tả tình trạng tại đây");

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tilRequestDetailDateRequestAtPicker.setHelperText("Ngày đặt lịch không quá 30 ngày");

        //Kiểm tra yêu cầu Hủy đơn
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("cancel_request")) {
            cancel_flag = intent.getBooleanExtra("cancel_request", false);
            cancel_description = intent.getStringExtra("cancel_description");
            if (cancel_flag) {
                //TODO: loading
                CustomDialog dialog_loading = new CustomDialog().setLoading(true);
                dialog_loading.show(getSupportFragmentManager(), "CustomDialog");
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        dialog_loading.cancel();
                        if (cancel_description != null) {
                            Toast.makeText(RequestDetailActivity.this, cancel_description, Toast.LENGTH_SHORT).show();
                        }
                        //        TODO: đã hủy đơn
                        CustomDialog dialog_cancel = new CustomDialog();
                        dialog_cancel.setType(CustomDialog.SUCCESS)
                                .setTitle("Đã hủy đơn")
                                .setTextBtnOutline("ĐÓNG")
                                .setTypeLayoutBtn(CustomDialog.CENTER)
                                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                                    @Override
                                    public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
                                        super.onViewReadyOneBtn(btnOutline);
                                        btnOutline.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog_cancel.cancel();
                                            }
                                        });
                                    }
                                });
                        dialog_cancel.show(getSupportFragmentManager(), "CustomDialog");
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }

        //Set up trạng thái
        updateStatus(binding.llRequestDetailStatus, binding.tvRequestDetailStatus, "Đã hủy");

        //Xem chi tiết trên bản đồ
        binding.btnRequestDetailGoToMapDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
                    btnRequestDetailGoToMapDetail_Event();
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
                                            btnRequestDetailGoToMapDetail_Event();
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

        //Mở dialog chọn ngày
        binding.btnRequestDetailOpenDatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đặt ngôn ngữ sang tiếng việt
                Locale.setDefault(new Locale("vi"));
                Configuration config = getResources().getConfiguration();
                config.setLocale(new Locale("vi"));
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                // Ngày hôm nay
                Date todayDate = new Date();
                long today = todayDate.getTime();

                // Tính ngày kết thúc là 30 ngày sau ngày hôm nay
                long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
                long end = today + thirtyDaysInMillis;

                // Tạo CalendarConstraints để giới hạn phạm vi ngày
                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                        .setStart(today)
                        .setEnd(end)
                        .setValidator(DateValidatorPointForward.from(today));

                // Tạo MaterialDatePicker với giới hạn ngày
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Chọn ngày")
                        .setSelection(today)
                        .setCalendarConstraints(constraintsBuilder.build())
                        .build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM, yyyy", new Locale("vi"));
                        String formattedDate = dateFormat.format(new Date(selection));

                        binding.edtRequestDetailDateRequestAtPicker.setText(formattedDate);
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "MaterialDatePicker");
            }
        });

        //Mở dialog chọn giờ
        binding.btnRequestDetailOpenTimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đặt ngôn ngữ sang tiếng việt
                Locale.setDefault(new Locale("vi"));
                Configuration config = getResources().getConfiguration();
                config.setLocale(new Locale("vi"));
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                // Tạo MaterialTimePicker với định dạng 24 giờ
                MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(hour)
                        .setMinute(minute)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .setTitleText("Chọn giờ")
                        .build();

                materialTimePicker.show(getSupportFragmentManager(), "MaterialTimePicker");

                materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedHour = materialTimePicker.getHour();
                        int selectedMinute = materialTimePicker.getMinute();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault()); // Ví dụ: 02:30 PM
                        String formattedTime = timeFormat.format(calendar.getTime());

                        binding.edtRequestDetailTimeRequestAtPicker.setText(formattedTime);
                    }
                });
            }
        });

        //Button chuyển sang trang ds Tech
        binding.btnRequestDetailTechnicianPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestDetailActivity.this, ListTechnicianActivity.class);
                startActivity(i);
            }
        });

        //Chọn phượng thức thanh toán
        binding.btnRequestDetailPaymentMethodPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestDetailActivity.this, PaymentMethodActivity.class);
                startActivity(i);
            }
        });
        binding.edtRequestDetailPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestDetailActivity.this, PaymentMethodActivity.class);
                startActivity(i);
            }
        });

        //EditText chuyển sang trang ds Tech
        binding.edtRequestCreateTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestDetailActivity.this, ListTechnicianActivity.class);
                startActivity(i);
            }
        });

        //Chụp ảnh
        binding.btnRequestDetailTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(RequestDetailActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RequestDetailActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    launchCamera();
                }
            }
        });

        //Đặt lịch
        binding.btnRequestDetailBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
                    btnRequestDetailBooking_Event();
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
                                            btnRequestDetailBooking_Event();
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

        //Lưu đơn
        binding.btnRequestDetailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
                    btnRequestDetailSave_Event();
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
                                            btnRequestDetailSave_Event();
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

        //Thanh toán
        binding.btnRequestDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show dialog QR code
            }
        });

        //Đánh giá
        binding.btnRequestDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestDetailActivity.this, ReviewActivity.class);
                startActivity(i);
            }
        });

        //Hủy đơn
        binding.btnRequestDetailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
                    if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
                        btnRequestDetailCancel_Event();
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
                                                btnRequestDetailCancel_Event();
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
        if (CheckNetwork.isAvailable(RequestDetailActivity.this)) {
            binding.rlRequestDetail.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
        } else {
            binding.rlRequestDetail.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
        }
    }

    private void updateStatus(LinearLayout linearLayout, TextView textView, String status) {
        switch (status) {
            case "Đã tạo":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_sent);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio1.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine1.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.ivRequestDetailPhotoConfirm.setVisibility(View.GONE);
                break;
            case "Đã gửi":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_sent);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio2.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine2.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.ivRequestDetailPhotoConfirm.setVisibility(View.GONE);
                break;
            case "Đã xác nhận":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_confirmed_successed);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.success_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio3.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine3.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.ivRequestDetailPhotoConfirm.setVisibility(View.VISIBLE);
                break;
            case "Đang xử lý":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_processing);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.warning_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio4.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine4.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.ivRequestDetailPhotoConfirm.setVisibility(View.VISIBLE);
                break;
            case "Đã hoàn thành":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_confirmed_successed);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.success_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio5.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine5.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                binding.ivRequestDetailPhotoConfirm.setVisibility(View.VISIBLE);
                break;
            case "Đã hủy":
                linearLayout.setBackgroundResource(R.drawable.background_request_detail_state_cancel);
                textView.setTextColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.danger_main));
                binding.tvRequestDetailStatus.setText(status);
                binding.ivRequestDetailStatusRadio6.setColorFilter(ContextCompat.getColor(this, R.color.primary_main));
                binding.ivRequestDetailStatusLine6.setBackgroundColor(ContextCompat.getColor(RequestDetailActivity.this, R.color.primary_main));
                break;
        }

    }

    File photoFile;
    Uri image_Uri;

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
            image_Uri = FileProvider.getUriForFile(RequestDetailActivity.this, "com.example.cuu_ho.fileprovider", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_Uri);
            takePictureLauncher.launch(takePictureIntent);
        } catch (IOException ex) {
            Log.e("Location_Map_Activity", "Error creating image file", ex);
        }
    }

    ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            image = processImage(image_Uri);
            binding.cvRequestDetailPhoto.setVisibility(View.VISIBLE);
            Glide.with(RequestDetailActivity.this)
                    .load(image_Uri)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.no_image)
                    .into(binding.ivRequestDetailPhoto);
        }
    });

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    // Xử lý ảnh: nếu không phải .jpg thì chuyển sang .jpg và trả về Uri
    private Uri processImage(Uri imageUri) {
        String mimeType = getMimeTypeFromUri(imageUri);  // Lấy loại MIME của ảnh
        if ("image/jpg".equals(mimeType)) {
            // Đã là .jpg, trả về chính URI này
            return imageUri;
        } else {
            // Không phải .jpg, chuyển đổi và trả về URI mới
            return convertToJPG(imageUri);
        }
    }

    // Lấy loại MIME của hình ảnh từ URI
    private String getMimeTypeFromUri(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        return contentResolver.getType(uri);
    }

    // Chuyển đổi hình ảnh sang định dạng .jpg và trả về URI mới
    private Uri convertToJPG(Uri imageUri) {
        try {
            // Lấy bitmap từ URI của ảnh đã chọn
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

            // Tạo đường dẫn lưu tệp .jpg
            File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Thư mục lưu trữ tạm thời
            File file = new File(directory, "" + System.currentTimeMillis() + ".jpg"); // Tạo tên tệp mới .jpg

            // Lưu bitmap dưới dạng .jpg
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // 100 = chất lượng cao nhất
            out.flush();
            out.close();

            // Trả về URI của tệp .jpg mới
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
