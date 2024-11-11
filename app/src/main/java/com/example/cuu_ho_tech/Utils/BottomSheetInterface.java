package com.example.cuu_ho_tech.Utils;

import androidx.fragment.app.FragmentManager;

import java.util.List;

public interface BottomSheetInterface {
    void dismiss();
    void show(FragmentManager manager, String tag);
    interface OnClickListener {
        void onClick(BottomSheetInterface bottomsheet);
    }
    interface OnClickListListener {
        void onClick(BottomSheetInterface bottomsheet, String data, int position);
    }
    interface OnClickEditListener {
        void onClick(BottomSheetInterface bottomsheet, String cart_detail, int position);
    }
    interface OnClickFilterListener {
        void onClick(BottomSheetInterface bottomsheet, List<String> list_status, String date_from, String date_to);
    }
}
