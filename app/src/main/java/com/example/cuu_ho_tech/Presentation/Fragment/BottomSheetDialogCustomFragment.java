package com.example.cuu_ho_tech.Presentation.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.ListItemAdapter;
import com.example.cuu_ho_tech.Presentation.Adapter.ListOptionAdapter;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.BottomSheetDialogCustomBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListLocationBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogCustomFragment extends BottomSheetDialogFragment implements BottomSheetInterface {
    private BottomSheetDialogCustomBinding binding;
    private List<String> list_data = new ArrayList<>(), list_data_origin;
    private Context context;
    private String message;
    private String title;

    // Biến để lưu listener cho các nút
    private String positiveButtonText;
    private String negativeButtonText;
    private BottomSheetInterface.OnClickListener positiveButtonListener;
    private BottomSheetInterface.OnClickListener negativeButtonListener;

    //List item
    private ListItemAdapter adapter_item;
    private BottomSheetInterface.OnClickListListener ListItemListener;

    //List option
    private ListOptionAdapter adapter_option;
    private BottomSheetInterface.OnClickListListener ListOptionListener;

    //Search
    private boolean isSearch = false;

    public BottomSheetDialogCustomFragment(Context context) {
        this.context = context;
    }

    public BottomSheetDialogCustomFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public BottomSheetDialogCustomFragment setMessage(String message) {
        this.message = message;
        return this;
    }

    public BottomSheetDialogCustomFragment setNegativeButton(String text, BottomSheetInterface.OnClickListener onClickListener) {
        this.negativeButtonListener = onClickListener;
        this.negativeButtonText = text;
        return this;
    }

    public BottomSheetDialogCustomFragment setPositiveButton(String text, BottomSheetInterface.OnClickListener onClickListener) {
        this.positiveButtonListener = onClickListener;
        this.positiveButtonText = text;
        return this;
    }

    public BottomSheetDialogCustomFragment setListItem(List<String> list_data, BottomSheetInterface.OnClickListListener onClickListener) {
        this.ListItemListener = onClickListener;
        this.list_data_origin = list_data;
        return this;
    }

    public BottomSheetDialogCustomFragment setListOption(List<String> list_data, BottomSheetInterface.OnClickListListener onClickListener) {
        this.ListOptionListener = onClickListener;
        this.list_data_origin = list_data;
        return this;
    }

    public BottomSheetDialogCustomFragment setSearchListItem() {
        if (list_data_origin == null || list_data_origin.isEmpty()) {
            Log.e("BottomSheetDialogCustomFragment", "List data cannot be empty. Please provide data.");
            throw new IllegalStateException("List data cannot be empty. Please provide data.");
        }

        this.isSearch = true;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        binding = BottomSheetDialogCustomBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(binding.getRoot().getRootView());

        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
        binding.llBottomSheetDialog.post(new Runnable() {
            @Override
            public void run() {
                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        Method4UI.hidehint(binding.edtSearch, binding.tilSearch, "Nhập tên");

        return bottomSheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        innitt();
    }

    private void innitt() {
        binding.tvTitle.setText(this.title);
        if (this.message != null) {
            binding.tvMessage.setText(this.message);
            binding.tvMessage.setVisibility(View.VISIBLE);
        } else {
            binding.tvMessage.setVisibility(View.GONE);
        }
        // Thiết lập các giá trị cho nút sau khi binding được khởi tạo
        if (positiveButtonListener != null) {
            binding.btnPositiveButton.setVisibility(View.VISIBLE);
            binding.btnPositiveButton.setText(positiveButtonText);
            binding.btnPositiveButton.setOnClickListener(view -> positiveButtonListener.onClick(this));
        }

        if (negativeButtonListener != null) {
            binding.btnNegativeButton.setVisibility(View.VISIBLE);
            binding.btnNegativeButton.setText(negativeButtonText);
            binding.btnNegativeButton.setOnClickListener(view -> negativeButtonListener.onClick(this));
        }

        if (ListItemListener != null) {
            binding.llListItem.setVisibility(View.VISIBLE);
            binding.rvListItem.setVisibility(View.VISIBLE);
            adapter_item = new ListItemAdapter(context, list_data_origin, new ClickListener() {
                @Override
                public void clickItem(String tech) {
                    ListItemListener.onClick(BottomSheetDialogCustomFragment.this, tech);
                }
            });
            binding.rvListItem.setLayoutManager(new LinearLayoutManager(context));
            binding.rvListItem.setAdapter(adapter_item);
        }

        if (ListOptionListener != null) {
            binding.llListOption.setVisibility(View.VISIBLE);
            binding.rvListOption.setVisibility(View.VISIBLE);
            adapter_option= new ListOptionAdapter(context, list_data_origin, new ClickListener() {
                @Override
                public void clickItem(String tech) {
                    ListOptionListener.onClick(BottomSheetDialogCustomFragment.this, tech);
                }
            });
            binding.rvListOption.setLayoutManager(new LinearLayoutManager(context));
            binding.rvListOption.setAdapter(adapter_option);
        }

        if (isSearch) {
            binding.tilSearch.setVisibility(View.VISIBLE);
            binding.edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filterList(s.toString());
                }
            });
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void dismissBottomSheet() {
        dismiss();
    }

    private void filterList(String query) {
        list_data.clear();
        if (query.isEmpty()) {
            list_data.addAll(list_data_origin);
        } else {
            for (String item : list_data_origin) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    list_data.add(item);
                }
            }
        }
        adapter_item.setData(list_data);
    }

}
