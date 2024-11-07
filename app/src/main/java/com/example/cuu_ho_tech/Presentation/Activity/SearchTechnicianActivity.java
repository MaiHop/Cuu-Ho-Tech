//package com.example.cuu_ho.Presentation.Activity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.IntentSenderRequest;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.cuu_ho.Presentation.Activity.Register.RegisterActivity;
//import com.example.cuu_ho.Presentation.Adapter.TechnicianSearchAdapter;
//import com.example.cuu_ho.Presentation.Dialog.CustomDialog;
//import com.example.cuu_ho.Presentation.ViewModel.LocationHelper;
//import com.example.cuu_ho.R;
//import com.example.cuu_ho.Utils.ClickListener;
//import com.example.cuu_ho.Utils.SetDataMapView;
//import com.example.cuu_ho.databinding.ActivitySearchTechnicianBinding;
//import com.example.cuu_ho.databinding.BottomSheetListTechnicianWorkshopBinding;
//import com.example.cuu_ho.databinding.BottomSheetWorkshopTechnicianInfoBinding;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class SearchTechnicianActivity extends AppCompatActivity {
//    ActivitySearchTechnicianBinding binding;
//    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;
//    private LocationHelper locationHelper;
//    private Double mapLatitude;
//    private Double mapLongitude;
//
//    //Bottomsheet danh sách kết quả tìm kiếm thợ
//    BottomSheetListTechnicianWorkshopBinding bottomSheetListTechnicianWorkshopBinding;
//    //Bottomsheet thông tin chi tiết thợ
//    BottomSheetWorkshopTechnicianInfoBinding bottomSheetWorkshopTechnicianInfoBinding;
//    BottomSheetBehavior bsb_list_tech_workshop,bsb_tech_info_workshop;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivitySearchTechnicianBinding.inflate(getLayoutInflater());
//        EdgeToEdge.enable(this);
//        setContentView(binding.getRoot());
//        initialize();
//        setupBottomSheet();
//
//        binding.edtText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(SearchTechnicianActivity.this, ListTechnicianMapActivity.class);
////                startActivity(i);
//                BottomSheetEvent();
//            }
//        });
//    }
//
//    private void setupBottomSheet() {
//        //Bottom sheet list thợ trong workshop
//        bottomSheetListTechnicianWorkshopBinding=BottomSheetListTechnicianWorkshopBinding.inflate(getLayoutInflater());
//        bsb_list_tech_workshop = BottomSheetBehavior.from(binding.llListTechnicianWorkshop.llListTechnicianWorkshop);
//        bsb_list_tech_workshop.setHideable(true);
//        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
//        binding.llListTechnicianWorkshop.llListTechnicianWorkshop.post(new Runnable() {
//            @Override
//            public void run() {
//                bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
//            }
//        });
//        //Bottom sheet thông tin thợ trong workshop
//        bottomSheetWorkshopTechnicianInfoBinding=BottomSheetWorkshopTechnicianInfoBinding.inflate(getLayoutInflater());
//        bsb_tech_info_workshop = BottomSheetBehavior.from(binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo);
//        binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo.post(new Runnable() {
//            @Override
//            public void run() {
//                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
//            }
//        });
//    }
//
//    private void BottomSheetEvent() {
//        List<String> list = new ArrayList<>();
//        for(int i =0; i<=10;i++){
//            list.add("Tech "+i);
//        }
//        Log.d("LIST_TECH", ""+list.size());
//        //Hiển ds thợ trong rv của bottomsheet
//        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(SearchTechnicianActivity.this,list, new ClickListener() {
//            @Override
//            public void clickItem(String tech) {
//                //Hiển thị bottomsheet thông tin của thợ
//                bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
//                Toast.makeText(SearchTechnicianActivity.this, tech, Toast.LENGTH_SHORT).show();
//                //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
//                binding.llListTechnicianWorkshop.llListTechnicianWorkshop.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.llWorkshopTechnicianInfo.tvWorkshopTechnicianName.setText(tech);
//                        binding.llWorkshopTechnicianInfo.btnWorkshopPhone.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(SearchTechnicianActivity.this,"Gọi bằng niềm tin", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        binding.llWorkshopTechnicianInfo.btnWorkshopTechnicianInfo.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(SearchTechnicianActivity.this,"Không có dữ liệu", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
//
//                    }
//                });
//                binding.llWorkshopTechnicianInfo.btnTechnicianInfoClose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
//                        bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    }
//                });
//            }
//        });
//        binding.llListTechnicianWorkshop.rvListTechnician.setLayoutManager(new LinearLayoutManager(SearchTechnicianActivity.this));
//        binding.llListTechnicianWorkshop.rvListTechnician.setAdapter(adapter);
//
//        bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
//    }
//
//    private void initialize() {
//        mapView();
//        binding.edtText.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus) {
//                if(!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
//                    binding.btnDelete.setVisibility(View.VISIBLE);
//            } else  {
//                hideKeyboard(v);
//                binding.btnDelete.setVisibility(View.GONE);
//            }
//        });
//        binding.edtText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE ||
//                        (event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
//                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                    binding.edtText.clearFocus();
////                    CustomBottomSheetDialogFragment bottomSheet = new CustomBottomSheetDialogFragment();
////                    bottomSheet.show(getSupportFragmentManager(), "show");
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        binding.edtText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
//                    binding.btnDelete.setVisibility(View.VISIBLE);
//                else  {
//                    binding.btnDelete.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.btnDelete.setVisibility(View.GONE);
//                binding.edtText.setText("");
//            }
//        });
//    }
//
//    private void mapView() {
//        SetDataMapView dataMapView = new SetDataMapView(this, binding.mapView);
//        dataMapView.setData();
//        locationSettingsLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartIntentSenderForResult(),
//                result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        locationHelper.fetchCurrentLocation();
//                    } else {
//                        Toast.makeText(this, "Location services not enabled", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                });
//        locationHelper = new LocationHelper(this, locationSettingsLauncher);
//        locationHelper.setLocationCallback((latitude, longitude) -> {
//            mapLatitude = latitude;
//            mapLongitude = longitude;
//            dataMapView.setLatitude(latitude);
//            dataMapView.setLongitude(longitude);
//            dataMapView.setUpLocation(mapLatitude, mapLongitude, "Current Location", "", R.drawable.ic_location_user, true);
//            dataMapView.addCircleOverlay(binding.mapView, mapLatitude, mapLongitude, 200);
//            dataMapView.setUpLocation(10.748302196597168, 106.69470476893976, "Technician 1", "", R.drawable.ic_technician, false);
//            dataMapView.setUpLocation(10.747779565081684, 106.69301419756049, "Technician 2", "", R.drawable.ic_technician, false);
//            dataMapView.setUpLocation(10.742364658804426, 106.68185455558142, "Technician 3", "", R.drawable.ic_technician, false);
//        });
//        CustomDialog customDialog = new CustomDialog().setLoading(true);
//        customDialog.show(getSupportFragmentManager(), "CustomDialog");
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                customDialog.cancel();
//                locationHelper.fetchCurrentLocation();
//            }
//        };
//        handler.postDelayed(runnable, 5000);
//    }
//
//
//    // location
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == LocationHelper.PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                locationHelper.fetchCurrentLocation();
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == LocationHelper.REQUEST_CHECK_SETTINGS) {
//            if (resultCode == Activity.RESULT_OK) {
//                locationHelper.fetchCurrentLocation();
//            } else {
//                Toast.makeText(this, "Location services not enabled", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }
//    // location
//
//
//    private void hideKeyboard(View view) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//}
