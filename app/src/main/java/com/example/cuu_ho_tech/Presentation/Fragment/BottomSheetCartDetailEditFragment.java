package com.example.cuu_ho_tech.Presentation.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.databinding.BottomSheetDialogEditCartDetailBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetCartDetailEditFragment extends BottomSheetDialogFragment implements BottomSheetInterface {
    private String cart_detail;
    private int position;
    private int currrent_quantity=0;
    private BottomSheetInterface.OnClickEditListener onClickEditListener;
    private Context context;
    public BottomSheetCartDetailEditFragment(Context context, String cart_detailn, int position) {
        this.context = context;
        this.position = position;
    }

    public BottomSheetCartDetailEditFragment setOnClickListener(BottomSheetInterface.OnClickEditListener onClickListener) {
        this.onClickEditListener = onClickListener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        BottomSheetDialogEditCartDetailBinding binding = BottomSheetDialogEditCartDetailBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(binding.getRoot().getRootView());

        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llBottomSheetDialogEdit.post(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        binding.tvTitle.setText("Chỉnh sửa");

        binding.tvCurrentQuantity.setText(""+currrent_quantity);
        binding.tvServiceRequestDetailQuantity.setText(""+currrent_quantity);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currrent_quantity += 1;
                binding.tvCurrentQuantity.setText(""+currrent_quantity);
                binding.tvServiceRequestDetailQuantity.setText(""+currrent_quantity);
            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currrent_quantity<=0){
                    currrent_quantity =0;
                }else {
                    currrent_quantity -= 1;
                }
                binding.tvCurrentQuantity.setText(""+currrent_quantity);
                binding.tvServiceRequestDetailQuantity.setText(""+currrent_quantity);
            }
        });

        binding.tvCurrentQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String quantityText = binding.tvCurrentQuantity.getText().toString();
                if (quantityText.isEmpty()) {
                    binding.tvCurrentQuantity.setText("0");
                    currrent_quantity = 0;
                } else {
                    currrent_quantity = Integer.parseInt(quantityText);
                }
                binding.tvServiceRequestDetailQuantity.setText(String.valueOf(currrent_quantity));
            }
        });

        if (onClickEditListener != null) {
            binding.btnPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEditListener.onClick((BottomSheetInterface) BottomSheetCartDetailEditFragment.this, cart_detail,currrent_quantity);
                }
            });
        }
        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    // Thêm phương thức để đóng BottomSheet nếu cần thiết
    public void dismissBottomSheet() {
        dismiss();
    }

}
