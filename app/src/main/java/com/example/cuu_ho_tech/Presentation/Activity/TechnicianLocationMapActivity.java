package com.example.cuu_ho_tech.Presentation.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.Presentation.ViewModel.LocationHelper;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.SetDataMapView;
import com.example.cuu_ho_tech.databinding.ActivityListTechnicianMapBinding;
import com.example.cuu_ho_tech.databinding.ActivityTechnicianLocationMapBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetTechinicianIsCommingBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetWorkshopTechnicianInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class TechnicianLocationMapActivity extends AppCompatActivity {
    ActivityTechnicianLocationMapBinding binding;
    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;
    private LocationHelper locationHelper;
    private Double mapLatitude;
    private Double mapLongitude;


    //Bottom sheet list thợ trong workshop
    BottomSheetTechinicianIsCommingBinding bottomSheetTechinicianIsCommingBinding;
    BottomSheetBehavior bsb_tech_is_coming;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTechnicianLocationMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
        setupBottomSheet();
    }

    private void setupBottomSheet() {
        //Bottom sheet list thợ trong workshop
        bottomSheetTechinicianIsCommingBinding = BottomSheetTechinicianIsCommingBinding.inflate(getLayoutInflater());
        bsb_tech_is_coming = BottomSheetBehavior.from(binding.llTechinicianIsComing.llTechinicianIsComing);


        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llTechinicianIsComing.llTechinicianIsComing.post(new Runnable() {
            @Override
            public void run() {
                bsb_tech_is_coming.setHideable(false);
                bsb_tech_is_coming.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.llTechinicianIsComing.btnTechnicianIsComClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb_tech_is_coming.setHideable(false);
                Toast.makeText(TechnicianLocationMapActivity.this,"OKOK",Toast.LENGTH_SHORT).show();
                bsb_tech_is_coming.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    private void initialize() {
        mapView();

        binding.btnTechnicianIsComClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}