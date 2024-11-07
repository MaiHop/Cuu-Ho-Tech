package com.example.cuu_ho_tech.Presentation.Activity.Register;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.ActivityRegisterCalendarBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.Locale;

public class RegisterCalendarActivity  extends AppCompatActivity {
    ActivityRegisterCalendarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
        onCLickListener();
    }

    private void onCLickListener() {
        binding.monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.monday.setActivated(!binding.monday.isActivated());
            }
        });
        binding.tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tuesday.setActivated(!binding.tuesday.isActivated());
            }
        });
        binding.wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.wednesday.setActivated(!binding.wednesday.isActivated());
            }
        });
        binding.thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.thursday.setActivated(!binding.thursday.isActivated());
            }
        });
        binding.friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.friday.setActivated(!binding.friday.isActivated());
            }
        });
        binding.saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.saturday.setActivated(!binding.saturday.isActivated());
            }
        });
        binding.sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.sunday.setActivated(!binding.sunday.isActivated());
            }
        });
    }

    private void initialize() {
        binding.btnTimeCloseDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                    MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(hour)
                        .setMinute(minute)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .setTitleText("Pick Time")
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.textTimeCloseDoor.setText(String.format(Locale.getDefault(), "%02d:%02d", timePicker.getHour(), timePicker.getMinute()));
                    }
                });
                timePicker.show(getSupportFragmentManager(), "close door");

            }
        });
        binding.btnTimeOpenDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(hour)
                        .setMinute(minute)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .setTitleText("Pick Time")
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.textTimeOpenDoor.setText(String.format(Locale.getDefault(), "%02d:%02d", timePicker.getHour(), timePicker.getMinute()));
                    }
                });
                timePicker.show(getSupportFragmentManager(), "close door");
            }
        });
    }
}