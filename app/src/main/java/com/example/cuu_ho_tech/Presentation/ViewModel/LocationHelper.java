package com.example.cuu_ho_tech.Presentation.ViewModel;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

public class LocationHelper {
    public static final int PERMISSION_REQUEST_CODE = 105;
    public static final int REQUEST_CHECK_SETTINGS = 103;
    private final FusedLocationProviderClient fusedLocationClient;
    private final Activity activity;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private LocationCallback callback;
    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;

    public interface LocationCallback {
        void onLocationFetched(double latitude, double longitude);
    }

    public LocationHelper(Activity activity, ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher) {
        this.activity = activity;
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        this.locationSettingsLauncher = locationSettingsLauncher;
    }

    public void setLocationCallback(LocationCallback callback) {
        this.callback = callback;
    }

    public void fetchCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST_CODE
            );
        } else {
            checkLocationSettings();
        }
    }

    private void checkLocationSettings() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setMinUpdateIntervalMillis(5000)
                .build();

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(locationSettingsResponse -> {
            getLastLocation();
        }).addOnFailureListener(e -> {
            if (e instanceof ResolvableApiException) {
                ResolvableApiException resolvable = (ResolvableApiException) e;
                IntentSenderRequest request = new IntentSenderRequest.Builder(resolvable.getResolution()).build();
                locationSettingsLauncher.launch(request);
            }
        });
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        if (callback != null) {
                            callback.onLocationFetched(latitude, longitude);
                        }
                    }
                });
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
