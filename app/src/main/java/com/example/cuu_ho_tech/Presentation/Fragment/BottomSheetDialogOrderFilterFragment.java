package com.example.cuu_ho_tech.Presentation.Fragment;//package com.example.cuu_ho.Presentation.Fragment;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatToggleButton;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.cuu_ho.Presentation.Adapter.ListItemAdapter;
//import com.example.cuu_ho.Presentation.Adapter.ListOptionAdapter;
//import com.example.cuu_ho.Utils.BottomSheetInterface;
//import com.example.cuu_ho.Utils.ClickListener;
//import com.example.cuu_ho.Utils.Method4UI;
//import com.example.cuu_ho.databinding.BottomSheetDialogCustomBinding;
//import com.example.cuu_ho.databinding.BottomSheetListRequestFilterBinding;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.google.android.material.datepicker.CalendarConstraints;
//import com.google.android.material.datepicker.DateValidatorPointBackward;
//import com.google.android.material.datepicker.DateValidatorPointForward;
//import com.google.android.material.datepicker.MaterialDatePicker;
//import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class BottomSheetDialogOrderFilterFragment extends BottomSheetDialogFragment implements BottomSheetInterface {
//    private BottomSheetDialog bottomSheetDialog;
//    private BottomSheetListRequestFilterBinding binding;
//    private List<String> list_status = new ArrayList<>();
//    private List<AppCompatToggleButton> list_togglebutton = new ArrayList<>();
//    private String date_from="", date_to="";
//
//    // Biến để lưu listener cho các nút
//    private String positiveButtonText;
//    private String negativeButtonText;
//    private OnClickFilterListener positiveButtonListener;
//    private OnClickFilterListener negativeButtonListener;
//
//    public BottomSheetDialogOrderFilterFragment(Context context) {
//        this.context = context;
//    }
//
//    public BottomSheetDialogOrderFilterFragment setNegativeButton(String text, OnClickFilterListener onClickListener) {
//        this.negativeButtonListener = onClickListener;
//        this.negativeButtonText = text;
//        return this;
//    }
//
//    public BottomSheetDialogOrderFilterFragment setPositiveButton(String text, OnClickFilterListener onClickListener) {
//        this.positiveButtonListener = onClickListener;
//        this.positiveButtonText = text;
//        return this;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//        binding = BottomSheetListRequestFilterBinding.inflate(getLayoutInflater());
//        bottomSheetDialog.setContentView(binding.getRoot().getRootView());
//
//        list_togglebutton.add(binding.tbListRequestFilterSaved);
//        list_togglebutton.add(binding.tbListRequestFilterSent);
//        list_togglebutton.add(binding.tbListRequestFilterConfirmedAt);
//        list_togglebutton.add(binding.tbListRequestFilterInProgress);
//        list_togglebutton.add(binding.tbListRequestFilterCompleted);
//        list_togglebutton.add(binding.tbListRequestFilterCancelled);
//
//        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
//        binding.llListRequestFilter.post(new Runnable() {
//            @Override
//            public void run() {
//                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });
//        Method4UI.hidehint(binding.edtListRequestFilterDateFrom, binding.tilListRequestFilterDateFrom, "Chọn ngày");
//        Method4UI.hidehint(binding.edtListRequestFilterDateTo, binding.tilListRequestFilterDateTo, "Chọn ngày");
//
//        //Mở dialog chọn ngày bắt đầu
//        binding.btnListRequestFilterDateFromPicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Đặt ngôn ngữ sang tiếng việt
//                Locale.setDefault(new Locale("vi"));
//                Configuration config = getResources().getConfiguration();
//                config.setLocale(new Locale("vi"));
//                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//
//                // Ngày hôm nay
//                Date todayDate = new Date();
//                long today = todayDate.getTime();
//
//                // Tính ngày kết thúc là 30 ngày trước ngày hôm nay
//                long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
//                long before = today - thirtyDaysInMillis;
//
//                // Tạo CalendarConstraints để giới hạn phạm vi ngày
//                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(today));
//
//                // Tạo MaterialDatePicker với giới hạn ngày
//                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày").setSelection(before).setCalendarConstraints(constraintsBuilder.build()).build();
//
//                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//                    @Override
//                    public void onPositiveButtonClick(Long selection) {
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM, yyyy", new Locale("vi"));
//                        String formattedDate = dateFormat.format(new Date(selection));
//
//                        binding.edtListRequestFilterDateFrom.setText(formattedDate);
//                        if(binding.edtListRequestFilterDateFrom.getText().toString().equals("Chọn ngày")){
//                            date_from = "";
//                        }else {
//                            date_from = formattedDate;
//                        }
//                    }
//                });
//                materialDatePicker.show(getActivity().getSupportFragmentManager(), "MaterialDatePicker");
//            }
//        });
//
//        //Mở dialog chọn ngày kết thúc
//        binding.btnListRequestFilterDateToPicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Đặt ngôn ngữ sang tiếng việt
//                Locale.setDefault(new Locale("vi"));
//                Configuration config = getResources().getConfiguration();
//                config.setLocale(new Locale("vi"));
//                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//
//                // Ngày hôm nay
//                Date todayDate = new Date();
//                long today = todayDate.getTime();
//
//                // Tạo CalendarConstraints để giới hạn phạm vi ngày
//                CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(today));
//
//                // Tạo MaterialDatePicker với giới hạn ngày
//                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày").setSelection(today).setCalendarConstraints(constraintsBuilder.build()).build();
//
//                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//                    @Override
//                    public void onPositiveButtonClick(Long selection) {
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM, yyyy", new Locale("vi"));
//                        String formattedDate = dateFormat.format(new Date(selection));
//
//                        binding.edtListRequestFilterDateTo.setText(formattedDate);
//                        if(binding.edtListRequestFilterDateTo.getText().toString().equals("Chọn ngày")){
//                            date_to = "";
//                        }else {
//                            date_to = formattedDate;
//                        }
//
//                    }
//                });
//                materialDatePicker.show(getActivity().getSupportFragmentManager(), "MaterialDatePicker");
//            }
//        });
//        return bottomSheetDialog;
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        innitt();
//    }
//
//
//    private void innitt() {
//        // Thiết lập các giá trị cho nút sau khi binding được khởi tạo
//        if (positiveButtonListener != null) {
//            binding.btnPositiveButton.setVisibility(View.VISIBLE);
//            binding.btnPositiveButton.setText(positiveButtonText);
//            binding.btnPositiveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list_status = setValueForListStatus(list_togglebutton);
//                    positiveButtonListener.onClick(BottomSheetDialogOrderFilterFragment.this, list_status, date_from, date_to);
//                }
//            });
//        }
//
//        if (negativeButtonListener != null) {
//            binding.btnNegativeButton.setVisibility(View.VISIBLE);
//            binding.btnNegativeButton.setText(negativeButtonText);
//            binding.btnNegativeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    RefreshForm();
//                    list_status = setValueForListStatus(list_togglebutton);
//                    positiveButtonListener.onClick(BottomSheetDialogOrderFilterFragment.this, list_status, date_from, date_to);
//                }
//            });
//        }
//    }
//
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//    }
//
//    @Override
//    public void show(FragmentManager manager, String tag) {
//        super.show(manager,tag);
//    }
//
//    private List<String> setValueForListStatus(List<AppCompatToggleButton> list_togglebutton) {
//        List<String> list = new ArrayList<>();
//        for (AppCompatToggleButton btn : list_togglebutton) {
//            if (btn.isChecked()) {
//                String status = convertStatus(btn.getText().toString());
//                list.add(status);
//            }
//        }
//        return list;
//    }
//
//    private String convertStatus(String status) {
//        switch (status) {
//            case "Đã lưu":
//                status = "saved";
//                break;
//            case "Đã gửi":
//                status = "sent";
//                break;
//            case "Đã xác nhận":
//                status = "confirmed_at";
//                break;
//            case "Đang xử lý":
//                status = "in_progress";
//                break;
//            case "Đã hoàn thành":
//                status = "completed";
//                break;
//            case "Đã hủy":
//                status = "cancelled";
//                break;
//        }
//        return status;
//    }
//
//    private void RefreshForm(){
//        for(AppCompatToggleButton btn : list_togglebutton){
//            btn.setChecked(false);
//        }
//        this.binding.edtListRequestFilterDateTo.setText("Chọn ngày");
//        this.binding.edtListRequestFilterDateFrom.setText("Chọn ngày");
//    }
//
//
//}
