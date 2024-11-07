package com.example.cuu_ho_tech.Presentation.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianSearchAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.Presentation.ViewModel.LocationHelper;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.Utils.SetDataMapView;
import com.example.cuu_ho_tech.databinding.ActivityOrderRescueBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetRequestCreateBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetWorkshopTechnicianInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OrderRescueActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 105;

    ActivityOrderRescueBinding binding;
    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;
    private LocationHelper locationHelper;
    private Double mapLatitude;
    private Double mapLongitude;
    private Uri image = null;

    //Bottom sheet tạo đơn cứu hộ
    private BottomSheetRequestCreateBinding bs_RequestCreateBinding;
    //Bottomsheet danh sách kết quả tìm kiếm thợ
    private BottomSheetListTechnicianSearchBinding bs_ListTechnicianSearchBinding;
    //Bottomsheet thông tin chi tiết thợ
    private BottomSheetWorkshopTechnicianInfoBinding bs_WorkshopTechnicianInfoBinding;
    private BottomSheetBehavior bsb_reqquest_create, bsb_list_tech_search, bsb_tech_info_workshop;
    private String name;
    private Method4UI method4UI = new Method4UI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderRescueBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        setupBottomSheet();
        initialize();
    }

    private void btnRequestCreateRescue_Event() {
        //TODO: loading
        CustomDialog dialog_loading = new CustomDialog().setLoading(true);
        dialog_loading.show(getSupportFragmentManager(), "CustomDialog");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dialog_loading.cancel();
                //        TODO: đăng ký thành công
                CustomDialog dialog_confirm = new CustomDialog()
                        .setType(CustomDialog.SUCCESS)
                        .setTitle("Yêu cầu cứu hộ thành công")
                        .setText("Vui lòng chú ý điện thoại hoặc xem Yêu cầu cứu hộ ở danh mục Hoạt động")
                        .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
                        .setTextBtn("THEO DÕI YÊU CẦU")
                        .setTextBtnOutline("TRANG CHỦ")
                        .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                            @Override
                            public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(OrderRescueActivity.this, RequestDetailActivity.class);
                                        startActivity(i);
                                    }
                                });
                                btnOutline.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(OrderRescueActivity.this, MainActivity.class);
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

    private void showBottomSheetListTech() {
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianSearch.llListTechnicianSearch.post(new Runnable() {
            @Override
            public void run() {
                bsb_list_tech_search.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        Log.d("LIST_TECH", "" + list.size());
        //Hiển ds thợ trong rv của bottomsheet
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(OrderRescueActivity.this, list, new ClickListener() {
            @Override
            public void clickItem(String tech) {
                //Hiển thị bottomsheet thông tin của thợ
                showBottomSheetTechInfo(tech);
            }
        });
        binding.llListTechnicianSearch.rvListTechnician.setLayoutManager(new LinearLayoutManager(OrderRescueActivity.this));
        binding.llListTechnicianSearch.rvListTechnician.setAdapter(adapter);


        //Đóng bottomsheet danh sách tech
        binding.llListTechnicianSearch.btnListTechnicianClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb_list_tech_search.setHideable(true);
                bsb_list_tech_search.setState(BottomSheetBehavior.STATE_HIDDEN);
                bsb_reqquest_create.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    private void showBottomSheetTechInfo(String tech) {
        bsb_list_tech_search.setState(BottomSheetBehavior.STATE_HIDDEN);
        Toast.makeText(OrderRescueActivity.this, tech, Toast.LENGTH_SHORT).show();
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianSearch.llListTechnicianSearch.post(new Runnable() {
            @Override
            public void run() {
                binding.llWorkshopTechnicianInfo.tvWorkshopTechnicianName.setText(tech);
                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        });

        //Xác nhận chọn
        binding.llWorkshopTechnicianInfo.btnWorkshopChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
                bsb_reqquest_create.setState(BottomSheetBehavior.STATE_EXPANDED);

                Toast.makeText(OrderRescueActivity.this, "Đã chọn", Toast.LENGTH_SHORT).show();
                binding.llRequestCreate.edtRequestCreateTechnician.setText(tech);
            }
        });
        //Gọi điện thoại
        binding.llWorkshopTechnicianInfo.btnWorkshopPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderRescueActivity.this, "Gọi bằng niềm tin", Toast.LENGTH_SHORT).show();
            }
        });
        //Xem chi tiết tech
        binding.llWorkshopTechnicianInfo.btnWorkshopTechnicianInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderRescueActivity.this, DetailTechnicianMapActivity.class);
                startActivity(i);
            }
        });
        //Đóng thông tin thợ
        binding.llWorkshopTechnicianInfo.btnTechnicianInfoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
                bsb_list_tech_search.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    private void BottomSheetOrderRescue() {
        //Chọn kĩ thuật viên
        binding.llRequestCreate.btnRequestCreateTechnicianPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderRescueActivity.this, "OKOK", Toast.LENGTH_SHORT).show();
                bsb_reqquest_create.setState(BottomSheetBehavior.STATE_COLLAPSED);
                showBottomSheetListTech();
            }
        });

        //Chọn phương thức thanh toán
        binding.llRequestCreate.btnRequestCreatePaymentMethodPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderRescueActivity.this, PaymentMethodActivity.class);
                startActivity(i);
            }
        });

        //Chụp ảnh
        binding.llRequestCreate.btnRequestCreateTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(OrderRescueActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(OrderRescueActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    launchCamera();
                }
            }
        });

        //Gọi cứu hộ
        binding.llRequestCreate.btnRequestCreateRescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isAvailable(OrderRescueActivity.this)) {
                    if(method4UI.validate()){
                        btnRequestCreateRescue_Event();
                    }
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
                                            btnRequestCreateRescue_Event();
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


//                binding.llRequestCreate.edtRequestCreateLicensePlate.setError("fdfd");
//                CustomDialog customDialog = new CustomDialog().setLoading(true);
//                customDialog.show(getSupportFragmentManager(), "dialog");
//                Handler handler = new Handler();
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        customDialog.cancel();
//                        CustomDialog dialog = new CustomDialog()
//                                .setType(CustomDialog.SUCCESS)
//                                .setTitle("Yêu cầu cứu hộ thành công")
//                                .setText("Vui lòng chú ý điện thoại hoặc xem Yêu cầu cứu hộ ở danh mục Hoạt động")
//                                .setTextBtn("THEO DÕI YÊU CẦU")
//                                .setTextBtnOutline("TRANG CHỦ")
//                                .setTypeLayoutBtn(CustomDialog.TOP_BOTTOM)
//                                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
//                                    @Override
//                                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
//                                        btn.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                            }
//                                        });
//                                        btnOutline.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                Intent intent = new Intent(OrderRescueActivity.this, MainActivity.class);
//                                                startActivity(intent);
//                                                finishAffinity();
//                                            }
//                                        });
//                                    }
//                                });
//                        dialog.show(getSupportFragmentManager(), "CustomDialog");
//                    }
//                };
//                handler.postDelayed(runnable, 2000);
            }
        });
    }

    private void setupBottomSheet() {
        //Bottom sheet tạo đơn cứu hộ
        bs_RequestCreateBinding = BottomSheetRequestCreateBinding.inflate(getLayoutInflater());
        bsb_reqquest_create = BottomSheetBehavior.from(binding.llRequestCreate.llRequestCreate);
        //Ẩn hint của textfield khi người dùng nhập trong bottom sheet create
        Method4UI.hidehint(binding.llRequestCreate.edtRequestCreateLicensePlate, binding.llRequestCreate.tilRequestCreateLicensePlate, "Nhập biển số xe");
        Method4UI.hidehint(binding.llRequestCreate.edtRequestCreateDescription, binding.llRequestCreate.tilRequestCreateDescription, "Nhập biển số xe");
        bsb_reqquest_create.setHideable(false);
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llRequestCreate.llRequestCreate.post(new Runnable() {
            @Override
            public void run() {
                if (bsb_reqquest_create.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bsb_reqquest_create.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bsb_reqquest_create.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        method4UI.addField(binding.llRequestCreate.edtRequestCreateLicensePlate,"Biển số xe không được để trống");
        method4UI.addField(binding.llRequestCreate.edtRequestCreateDescription,"Vui lòng mô tả tình trạng và gửi ảnh");

        //Set up bottom sheet danh sách tech xung quanh
        bs_ListTechnicianSearchBinding = BottomSheetListTechnicianSearchBinding.inflate(getLayoutInflater());
        bsb_list_tech_search = BottomSheetBehavior.from(binding.llListTechnicianSearch.llListTechnicianSearch);
        bsb_list_tech_search.setHideable(true);
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianSearch.llListTechnicianSearch.post(new Runnable() {
            @Override
            public void run() {
                bsb_list_tech_search.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        Method4UI.hidehint(binding.llListTechnicianSearch.edtListTechnicianSearch, binding.llListTechnicianSearch.tilListTechnicianSearch, "Nhập tên");

        //Bottom sheet thông tin thợ trong workshop
        bs_WorkshopTechnicianInfoBinding = BottomSheetWorkshopTechnicianInfoBinding.inflate(getLayoutInflater());
        bsb_tech_info_workshop = BottomSheetBehavior.from(binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo);
        binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo.post(new Runnable() {
            @Override
            public void run() {
                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        BottomSheetOrderRescue();
    }

    private void initialize() {
        SetDataMapView dataMapView = new SetDataMapView(this, binding.mapView);
        dataMapView.setData();
        locationSettingsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        locationHelper.fetchCurrentLocation();
                    } else {
                        Toast.makeText(this, "Location services not enabled", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        locationHelper = new LocationHelper(this, locationSettingsLauncher);
        locationHelper.setLocationCallback((latitude, longitude) -> {
            mapLatitude = latitude;
            mapLongitude = longitude;
            dataMapView.setLatitude(latitude);
            dataMapView.setLongitude(longitude);
            dataMapView.setUpLocation(mapLatitude, mapLongitude, "Current Location", "", R.drawable.ic_location_user, true);
            dataMapView.addCircleOverlay(binding.mapView, mapLatitude, mapLongitude, 200);
            dataMapView.setUpLocation(10.748302196597168, 106.69470476893976, "Technician 1", "", R.drawable.ic_technician, false);
            dataMapView.setUpLocation(10.747779565081684, 106.69301419756049, "Technician 2", "", R.drawable.ic_technician, false);
            dataMapView.setUpLocation(10.742364658804426, 106.68185455558142, "Technician 3", "", R.drawable.ic_technician, false);
        });
        locationHelper.fetchCurrentLocation();
        onCLickListener();
    }

    private void onCLickListener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mapView.getController().setCenter(new GeoPoint(mapLatitude, mapLongitude));
            }
        });
    }


    // location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LocationHelper.PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationHelper.fetchCurrentLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationHelper.REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationHelper.fetchCurrentLocation();
            } else {
                Toast.makeText(this, "Location services not enabled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    File photoFile;
    Uri image_Uri;

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
            image_Uri = FileProvider.getUriForFile(OrderRescueActivity.this, "com.example.cuu_ho.fileprovider", photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_Uri);
            takePictureLauncher.launch(takePictureIntent);
        } catch (IOException ex) {
            Log.e("Location_Map_Activity", "Error creating image file", ex);
        }
    }

    ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            image = processImage(image_Uri);
            binding.llRequestCreate.cvRequestCreatePhoto.setVisibility(View.VISIBLE);
            Glide.with(OrderRescueActivity.this)
                    .load(image_Uri)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.no_image)
                    .into(binding.llRequestCreate.ivRequestCreatePhoto);
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