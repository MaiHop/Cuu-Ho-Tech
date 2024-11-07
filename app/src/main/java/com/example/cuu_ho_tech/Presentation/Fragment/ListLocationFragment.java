package com.example.cuu_ho_tech.Presentation.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.LocationAdapter;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.BottomSheetListLocationBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ListLocationFragment extends BottomSheetDialogFragment {
    private List<String> list;
    private ClickListener clickListener;
    private Context context;
    private String name;
    private String title;
    public ListLocationFragment(Context context,String tiltle, List<String> list, ClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
        this.context = context;
        this.title = tiltle;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        BottomSheetListLocationBinding binding = BottomSheetListLocationBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(binding.getRoot().getRootView());

        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llListLocation.post(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        binding.tvListLocationTitle.setText(title);
        Method4UI.hidehint(binding.edtListLocationSearch, binding.tilListLocationSearch,"Nhập tên");

        LocationAdapter adapter = new LocationAdapter(context,list, new ClickListener() {
            @Override
            public void clickItem(String tech) {
                name = tech;
                clickListener.clickItem(name);
                bottomSheetDialog.dismiss();
            }
        });
        binding.rvListLocation.setLayoutManager(new LinearLayoutManager(context));
        binding.rvListLocation.setAdapter(adapter);
        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();


        // Lấy BottomSheetBehavior từ BottomSheetDialog
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);

                // Cho phép dragging và thiết lập các trạng thái ban đầu
                behavior.setDraggable(true); // Đảm bảo cho phép kéo
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN); // Đặt trạng thái mặc định là collapsed
                behavior.setHideable(true);
                behavior.setPeekHeight(120);
            }
        }
    }

    // Thêm phương thức để đóng BottomSheet nếu cần thiết
    public void dismissBottomSheet() {
        dismiss();
    }

}
