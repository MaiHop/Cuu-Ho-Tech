package com.example.cuu_ho_tech.Presentation.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.SearchServiceAndOrderAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianSearchAdapter;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.Presentation.ViewModel.LocationHelper;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedSearchServiceAndOrderViewModel;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.DeviceUtils;
import com.example.cuu_ho_tech.Utils.OnLocationListener;
import com.example.cuu_ho_tech.Utils.SetDataMapView;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianWorkshopBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetWorkshopTechnicianInfoBinding;
import com.example.cuu_ho_tech.databinding.FragmentWorkShopBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WorkShopFragment extends Fragment {
    FragmentWorkShopBinding binding;
    private ActivityResultLauncher<IntentSenderRequest> locationSettingsLauncher;
    private LocationHelper locationHelper;
    private Double mapLatitude;
    private Double mapLongitude;
    private SharedSearchServiceAndOrderViewModel sharedSearchServiceAndOrderViewModel;


    //Bottomsheet danh sách kết quả tìm kiếm thợ
    BottomSheetListTechnicianWorkshopBinding bottomSheetListTechnicianWorkshopBinding;
    //Bottomsheet thông tin chi tiết thợ
    BottomSheetWorkshopTechnicianInfoBinding bottomSheetWorkshopTechnicianInfoBinding;
    BottomSheetBehavior bsb_list_tech_workshop, bsb_tech_info_workshop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorkShopBinding.inflate(inflater, container, false);
        initialize();
        setupBottomSheet();

        binding.edtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(SearchTechnicianActivity.this, ListTechnicianMapActivity.class);
//                startActivity(i);
                BottomSheetEvent();
            }
        });
        return binding.getRoot();
    }

    private void setupBottomSheet() {
        //Bottom sheet list thợ trong workshop
        bottomSheetListTechnicianWorkshopBinding = BottomSheetListTechnicianWorkshopBinding.inflate(getLayoutInflater());
        bsb_list_tech_workshop = BottomSheetBehavior.from(binding.llListTechnicianWorkshop.llListTechnicianWorkshop);
        bsb_list_tech_workshop.setHideable(true);
        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianWorkshop.llListTechnicianWorkshop.post(new Runnable() {
            @Override
            public void run() {
                bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
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
        binding.llWorkshopTechnicianInfo.btnWorkshopChoose.setVisibility(View.GONE);
    }

    private void BottomSheetEvent() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add("Tech " + i);
        }
        Log.d("LIST_TECH", "" + list.size());
        //Hiển ds thợ trong rv của bottomsheet
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(getActivity(), list, new ClickListener() {
            @Override
            public void clickItem(String tech) {
                //Hiển thị bottomsheet thông tin của thợ
                bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
                Toast.makeText(getActivity(), tech, Toast.LENGTH_SHORT).show();
                //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
                binding.llListTechnicianWorkshop.llListTechnicianWorkshop.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.llWorkshopTechnicianInfo.tvWorkshopTechnicianName.setText(tech);
                        binding.llWorkshopTechnicianInfo.btnWorkshopPhone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "Gọi bằng niềm tin", Toast.LENGTH_SHORT).show();
                            }
                        });
                        binding.llWorkshopTechnicianInfo.btnWorkshopTechnicianInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                        });
                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                });
                binding.llWorkshopTechnicianInfo.btnTechnicianInfoClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bsb_tech_info_workshop.setState(BottomSheetBehavior.STATE_HIDDEN);
                        bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                });
            }
        });
        binding.llListTechnicianWorkshop.rvListTechnician.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.llListTechnicianWorkshop.rvListTechnician.setAdapter(adapter);

        bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void initialize() {
        mapView();
        listSearchServiceAndOrderAdapter();
        sharedSearchServiceAndOrderViewModel = new ViewModelProvider(requireActivity()).get(SharedSearchServiceAndOrderViewModel.class);
        binding.btnSearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutSearch.setVisibility(View.VISIBLE);
                binding.edtText.requestFocus();
                DeviceUtils.showKeyboard(binding.edtText, requireContext());
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutSearch.setVisibility(View.GONE);
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnSearchLayout.setText(Objects.requireNonNull(binding.edtText.getText()).toString());
                binding.layoutSearch.setVisibility(View.GONE);
                binding.edtText.clearFocus();
            }
        });
        binding.edtText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
                    binding.btnDelete.setVisibility(View.VISIBLE);
            } else {
                DeviceUtils.hideKeyboard(v, requireContext());
                binding.btnDelete.setVisibility(View.GONE);
            }
        });
        binding.edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
                    binding.btnDelete.setVisibility(View.VISIBLE);
                else binding.btnDelete.setVisibility(View.GONE);
                sharedSearchServiceAndOrderViewModel.setTextActFromFra(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnDelete.setVisibility(View.GONE);
                binding.edtText.setText("");
            }
        });
        sharedSearchServiceAndOrderViewModel.getTextFromItem().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String inputText) {
                Toast.makeText(requireContext(), inputText, Toast.LENGTH_SHORT).show();
                binding.edtText.setText(inputText);
                binding.edtText.setSelection(inputText.length());
            }
        });
//        binding.edtText.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus) {
//                if(!Objects.requireNonNull(binding.edtText.getText()).toString().isEmpty())
//                    binding.btnDelete.setVisibility(View.VISIBLE);
//            } else  {
//                DeviceUtils.hideKeyboard(v, requireContext());
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
    }

    private void listSearchServiceAndOrderAdapter() {
        String[][] data = {
                {"Hung auto", "1"},
                {"Minh show room", "0"},
                {"showroom", "1"},
                {"cuu ho 360", "0"},
                {"auto mobile", "1"}
        };
        SearchServiceAndOrderAdapter adapter = new SearchServiceAndOrderAdapter(requireContext(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.listSearch.setLayoutManager(layoutManager);
        binding.listSearch.setAdapter(adapter);
    }

    private void mapView() {
        SetDataMapView dataMapView = new SetDataMapView(requireActivity(), binding.mapView);
        dataMapView.actionListener = new OnLocationListener() {
            @Override
            public void onLocationSelected(double latitude, double longitude) {
                //TODO : sự kiện lắng nghe khi ấn vào
                Toast.makeText(requireContext(), "Con mèo", Toast.LENGTH_SHORT).show();
            }
        };
        dataMapView.setData();
        locationSettingsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        locationHelper.fetchCurrentLocation();
                    } else {
                        Toast.makeText(requireContext(), "Location services not enabled", Toast.LENGTH_SHORT).show();
                    }
                });
        locationHelper = new LocationHelper(requireActivity(), locationSettingsLauncher);
        locationHelper.setLocationCallback((latitude, longitude) -> {
            mapLatitude = latitude;
            mapLongitude = longitude;
            dataMapView.setLatitude(latitude);
            dataMapView.setLongitude(longitude);
            binding.btnCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.mapView.getController().setCenter(new GeoPoint(mapLatitude, mapLongitude));
                }
            });
            dataMapView.setUpLocation(mapLatitude, mapLongitude, "Current Location", "", R.drawable.ic_location_user, true);
            dataMapView.addCircleOverlay(binding.mapView, mapLatitude, mapLongitude, 200);
            dataMapView.setUpLocation(10.748302196597168, 106.69470476893976, "Technician 1", "", R.drawable.ic_technician, false);
            dataMapView.setUpLocation(10.747779565081684, 106.69301419756049, "Technician 2", "", R.drawable.ic_technician, false);
            dataMapView.setUpLocation(10.742364658804426, 106.68185455558142, "Technician 3", "", R.drawable.ic_technician, false);
        });
        CustomDialog customDialog = new CustomDialog().setLoading(true);
        customDialog.show(getChildFragmentManager(), "CustomDialog");
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                customDialog.cancel();
                locationHelper.fetchCurrentLocation();
            }
        };
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mapView.getController().zoomIn();
            }
        });
        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mapView.getController().zoomOut();
            }
        });
        handler.postDelayed(runnable, 5000);
    }
}
