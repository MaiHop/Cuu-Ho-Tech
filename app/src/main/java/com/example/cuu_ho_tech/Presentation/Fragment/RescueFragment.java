package com.example.cuu_ho_tech.Presentation.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Domain.Response.StatusResponse;
import com.example.cuu_ho_tech.Presentation.Adapter.RescueAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.BottomSheetListRequestFilterBinding;
import com.example.cuu_ho_tech.databinding.FragmentOrderRescueListBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RescueFragment extends Fragment {
    FragmentOrderRescueListBinding binding;
    int selected_sort_position = 0;

    BottomSheetListRequestFilterBinding bottomSheetListRequestFilterBinding;
    BottomSheetDialog bsb_request_filter;
    private List<String> list_status = new ArrayList<>();
    private List<AppCompatToggleButton> list_togglebutton = new ArrayList<>();
    private String date_from = "", date_to = "";
    private boolean isfilter = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderRescueListBinding.inflate(inflater, container, false);
        binding.rcvOrderRescueList.setLayoutManager(new LinearLayoutManager(getActivity()));

        bottomSheetListRequestFilterBinding = BottomSheetListRequestFilterBinding.inflate(getLayoutInflater());
        bsb_request_filter = new BottomSheetDialog(getActivity());
        bsb_request_filter.setContentView(bottomSheetListRequestFilterBinding.getRoot().getRootView());
        setupbottomSheetListRequestFilter();

        List<StatusResponse> statusResponseList = Arrays.asList(
                new StatusResponse("Đã hoàn thành"),
                new StatusResponse("Đang xử lý")
        );

        RescueAdapter rescueAdapter = new RescueAdapter(statusResponseList, getActivity());
        binding.rcvOrderRescueList.setAdapter(rescueAdapter);

        //Trạng thái
        List<String> list_sort = new ArrayList<>();
        list_sort.add("Mặc định");
        list_sort.add("Tiền tăng dần");
        list_sort.add("Tiền giảm dần");
        list_sort.add("Số lượng dịch vụ tăng dần");
        list_sort.add("Số lượng dịch vụ giảm dần");
        list_sort.add("Ngày đặt gần nhất");
        list_sort.add("Ngày đặt xa nhất");
        BottomSheetDialogCustomFragment bsd_status = new BottomSheetDialogCustomFragment(getActivity())
                .setTitle("Sắp xếp")
                .setListOption(list_sort, selected_sort_position, new BottomSheetInterface.OnClickListListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                        bottomsheet.dismiss();
                        selected_sort_position = position;
                        binding.togbtnSort.setChecked(true);
                        binding.togbtnFilter.setChecked(false);
                    }
                });
        binding.togbtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((AppCompatToggleButton) v).isChecked()) {
                    binding.togbtnSort.setChecked(true);
                    binding.togbtnFilter.setChecked(false);
                } else {
                    binding.togbtnSort.setChecked(true);
                    binding.togbtnFilter.setChecked(false);
                }
                bsd_status.setListOption(list_sort, selected_sort_position, new BottomSheetInterface.OnClickListListener() {
                    @Override
                    public void onClick(BottomSheetInterface bottomsheet, String data, int position) {
                        bottomsheet.dismiss();
                        selected_sort_position = position;
                    }
                });
                bsd_status.show(getActivity().getSupportFragmentManager(), bsd_status.getTag());
            }
        });

        binding.togbtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AppCompatToggleButton btn : list_togglebutton) {
                    if (btn.isChecked()) {
                        isfilter = true;
                    }
                }
                if (isfilter || !bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo.getText().toString().equals("Chọn ngày") ||
                        !bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom.getText().toString().equals("Chọn ngày")) {
                    binding.togbtnSort.setChecked(false);
                    binding.togbtnFilter.setChecked(true);
                } else {
                    binding.togbtnSort.setChecked(false);
                    binding.togbtnFilter.setChecked(false);
                }

                if (bsb_request_filter.isShowing()) {
                    bsb_request_filter.dismiss();
                } else {
                    bsb_request_filter.show();
                }
            }
        });
        return binding.getRoot();
    }

    private void setupbottomSheetListRequestFilter() {
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterSaved);
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterSent);
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterConfirmedAt);
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterInProgress);
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterCompleted);
        list_togglebutton.add(bottomSheetListRequestFilterBinding.tbListRequestFilterCancelled);


        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        bottomSheetListRequestFilterBinding.llListRequestFilter.post(new Runnable() {
            @Override
            public void run() {
                bsb_request_filter.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        Method4UI.hidehint(bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom, bottomSheetListRequestFilterBinding.tilListRequestFilterDateFrom, "Chọn ngày");
        Method4UI.hidehint(bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo, bottomSheetListRequestFilterBinding.tilListRequestFilterDateTo, "Chọn ngày");

        //Mở dialog chọn ngày bắt đầu
        bottomSheetListRequestFilterBinding.btnListRequestFilterDateFromPicker.setOnClickListener(new View.OnClickListener() {
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

                // Tính ngày kết thúc là 30 ngày trước ngày hôm nay
                long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
                long before = today - thirtyDaysInMillis;

                // Tạo CalendarConstraints để giới hạn phạm vi ngày
                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(today));

                // Tạo MaterialDatePicker với giới hạn ngày
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày").setSelection(before).setCalendarConstraints(constraintsBuilder.build()).build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM yyyy", new Locale("vi"));
                        String formattedDate = dateFormat.format(new Date(selection));

                        bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom.setText(formattedDate);
                        if (bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom.getText().toString().equals("Chọn ngày")) {
                            date_from = "";
                        } else {
                            date_from = formattedDate;
                        }
                    }
                });
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "MaterialDatePicker");
            }
        });

        //Mở dialog chọn ngày kết thúc
        bottomSheetListRequestFilterBinding.btnListRequestFilterDateToPicker.setOnClickListener(new View.OnClickListener() {
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

                // Tạo CalendarConstraints để giới hạn phạm vi ngày
                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(today));

                // Tạo MaterialDatePicker với giới hạn ngày
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày").setSelection(today).setCalendarConstraints(constraintsBuilder.build()).build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM yyyy", new Locale("vi"));
                        String formattedDate = dateFormat.format(new Date(selection));

                        bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo.setText(formattedDate);
                        if (bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo.getText().toString().equals("Chọn ngày")) {
                            date_to = "";
                        } else {
                            date_to = formattedDate;
                        }

                    }
                });
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "MaterialDatePicker");
            }
        });

        bottomSheetListRequestFilterBinding.btnPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_status = setValueForListStatus(list_togglebutton);
                isfilter = false;
                for (AppCompatToggleButton btn : list_togglebutton) {
                    if (btn.isChecked()) {
                        isfilter = true;
                        Log.d("STATUS", btn.getText().toString());
                    }
                }
                if (isfilter || !bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo.getText().toString().equals("Chọn ngày") ||
                        !bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom.getText().toString().equals("Chọn ngày")) {
                    binding.togbtnSort.setChecked(false);
                    binding.togbtnFilter.setChecked(true);
                } else {
                    binding.togbtnSort.setChecked(false);
                    binding.togbtnFilter.setChecked(false);
                }

                bsb_request_filter.dismiss();
            }
        });

        bottomSheetListRequestFilterBinding.btnNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshForm();
                list_status = setValueForListStatus(list_togglebutton);
                binding.togbtnFilter.setChecked(false);

                bsb_request_filter.dismiss();
            }
        });
    }

    private List<String> setValueForListStatus(List<AppCompatToggleButton> list_togglebutton) {
        List<String> list = new ArrayList<>();
        for (AppCompatToggleButton btn : list_togglebutton) {
            if (btn.isChecked()) {
                String status = convertStatus(btn.getText().toString());
                Log.d("STATUS", btn.getText().toString());
                list.add(status);
            }
        }
        return list;
    }

    private String convertStatus(String status) {
        switch (status) {
            case "Đã lưu":
                status = "saved";
                break;
            case "Đã gửi":
                status = "sent";
                break;
            case "Đã xác nhận":
                status = "confirmed_at";
                break;
            case "Đang xử lý":
                status = "in_progress";
                break;
            case "Đã hoàn thành":
                status = "completed";
                break;
            case "Đã hủy":
                status = "cancelled";
                break;
        }
        return status;
    }

    private void RefreshForm() {
        for (AppCompatToggleButton btn : list_togglebutton) {
            btn.setChecked(false);
        }
        bottomSheetListRequestFilterBinding.edtListRequestFilterDateTo.setText("Chọn ngày");
        bottomSheetListRequestFilterBinding.edtListRequestFilterDateFrom.setText("Chọn ngày");
    }
}
