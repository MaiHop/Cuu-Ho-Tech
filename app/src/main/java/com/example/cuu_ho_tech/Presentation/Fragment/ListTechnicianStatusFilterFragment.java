package com.example.cuu_ho_tech.Presentation.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianStatusFilterBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ListTechnicianStatusFilterFragment extends BottomSheetDialogFragment {
    private Context context;
    private String name;
    private ClickListener clickListener;
    public ListTechnicianStatusFilterFragment(Context context, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        BottomSheetListTechnicianStatusFilterBinding binding = BottomSheetListTechnicianStatusFilterBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(binding.getRoot().getRootView());

        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListTechnicianStatusFilter.post(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        binding.rgListRequestStatusFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == binding.rbListRequestStatusFilterDefault.getId()) {
                    clickListener.clickItem(binding.rbListRequestStatusFilterDefault.getText().toString());
                }else if (checkedId == binding.rbListRequestStatusFilterOpen.getId()) {
                    clickListener.clickItem(binding.rbListRequestStatusFilterOpen.getText().toString());
                }else if (checkedId == binding.rbListRequestStatusFilterClose.getId()) {
                    clickListener.clickItem(binding.rbListRequestStatusFilterClose.getText().toString());
                }
                bottomSheetDialog.dismiss();
            }
        });


        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}
