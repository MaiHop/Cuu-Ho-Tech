package com.example.cuu_ho_tech.Utils;

public interface BottomSheetInterface {
    void dismiss();
    interface OnClickListener {
        void onClick(BottomSheetInterface bottomsheet);
    }
    interface OnClickListListener {
        void onClick(BottomSheetInterface bottomsheet, String data);
    }
}
