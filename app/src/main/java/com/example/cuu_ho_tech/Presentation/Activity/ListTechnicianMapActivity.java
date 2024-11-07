package com.example.cuu_ho_tech.Presentation.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianSearchAdapter;
import com.example.cuu_ho_tech.Presentation.ConnectInternet.CheckNetwork;
import com.example.cuu_ho_tech.Presentation.ViewModel.LocationHelper;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.SetDataMapView;
import com.example.cuu_ho_tech.databinding.ActivityListTechnicianMapBinding;
import com.example.cuu_ho_tech.databinding.ActivityRequestDetailBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianWorkshopBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetWorkshopTechnicianInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class ListTechnicianMapActivity extends AppCompatActivity {
    ActivityListTechnicianMapBinding binding;
    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;
    private LocationHelper locationHelper;
    private Double mapLatitude;
    private Double mapLongitude;

    //Bottom sheet list thợ trong workshop
    BottomSheetListTechnicianSearchBinding bottomSheetListTechnicianSearchBinding;
    //Bottom sheet thông tin thợ trong workshop
    BottomSheetWorkshopTechnicianInfoBinding bottomSheetWorkshopTechnicianInfoBinding;
    BottomSheetBehavior bsb_list_tech_search, bsb_tech_info_workshop;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityListTechnicianMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
        setupBottomSheet();
        if(checkInternet()){
            testbottomsheet();
        }

    }

    private void setupBottomSheet() {
        //Bottom sheet list thợ trong workshop
        bottomSheetListTechnicianSearchBinding = BottomSheetListTechnicianSearchBinding.inflate(getLayoutInflater());
        bsb_list_tech_search = BottomSheetBehavior.from(binding.llListTechnicianSearch.llListTechnicianSearch);

        bsb_list_tech_search.setHideable(false);
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianSearch.llListTechnicianSearch.post(new Runnable() {
            @Override
            public void run() {
                if(bsb_list_tech_search.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bsb_list_tech_search.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bsb_list_tech_search.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        //Bottom sheet thông tin thợ trong workshop
        bottomSheetWorkshopTechnicianInfoBinding = BottomSheetWorkshopTechnicianInfoBinding.inflate(getLayoutInflater());
        bsb_tech_info_workshop = BottomSheetBehavior.from(binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo);
        binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo.post(new Runnable() {
            @Override
            public void run() {
                bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        binding.llWorkshopTechnicianInfo.btnWorkshopPhone.setVisibility(View.GONE);
    }

    private void testbottomsheet() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        Log.d("LIST_TECH", "" + list.size());
        //Hiển ds thợ trong rv của bottomsheet
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(ListTechnicianMapActivity.this, list, new ClickListener() {
            @Override
            public void clickItem(String tech) {
                //Hiển thị bottomsheet thông tin của thợ
                bsb_list_tech_search.setHideable(true);
                bsb_list_tech_search.setState(BottomSheetBehavior.STATE_HIDDEN);
                Toast.makeText(ListTechnicianMapActivity.this, tech, Toast.LENGTH_SHORT).show();
                //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
                binding.llListTechnicianSearch.llListTechnicianSearch.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.llWorkshopTechnicianInfo.tvWorkshopTechnicianName.setText(tech);

                        binding.llWorkshopTechnicianInfo.btnWorkshopChoose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ListTechnicianMapActivity.this, RequestDetailActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });

                        binding.llWorkshopTechnicianInfo.btnWorkshopPhone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ListTechnicianMapActivity.this, "Gọi bằng niềm tin", Toast.LENGTH_SHORT).show();
                            }
                        });
                        binding.llWorkshopTechnicianInfo.btnWorkshopTechnicianInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ListTechnicianMapActivity.this, DetailTechnicianMapActivity.class);
                                startActivity(i);
                            }
                        });
                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                });
                binding.llWorkshopTechnicianInfo.btnTechnicianInfoClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
                        bsb_list_tech_search.setHideable(false);
                        bsb_list_tech_search.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });
            }
        });
        binding.llListTechnicianSearch.rvListTechnician.setLayoutManager(new LinearLayoutManager(ListTechnicianMapActivity.this));
        binding.llListTechnicianSearch.rvListTechnician.setAdapter(adapter);

        bsb_list_tech_search.setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.llListTechnicianSearch.btnListTechnicianClose.setVisibility(View.GONE);

    }

    private void initialize() {
        mapView();

        //Thoát
        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Load lại activity
        binding.llNoInternet.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()){
                    testbottomsheet();
                }
            }
        });
    }

    private void mapView() {
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
    // location


    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean checkInternet() {
        if (CheckNetwork.isAvailable(ListTechnicianMapActivity.this)) {
            binding.flListTechMap.setVisibility(View.VISIBLE);
            binding.llListTechnicianSearch.llListTechnicianSearch.setVisibility(View.VISIBLE);
            binding.llNoInternet.llNoInternet.setVisibility(View.GONE);
            return true;
        } else {
            binding.flListTechMap.setVisibility(View.GONE);
            binding.llListTechnicianSearch.llListTechnicianSearch.setVisibility(View.GONE);
            binding.llNoInternet.llNoInternet.setVisibility(View.VISIBLE);
            return false;
        }
    }
}