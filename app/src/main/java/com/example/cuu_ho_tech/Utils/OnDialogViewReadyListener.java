package com.example.cuu_ho_tech.Utils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public interface OnDialogViewReadyListener {
    void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline);
    void onViewReadyOneBtn(AppCompatTextView btnOutline);
    void onReadyListView(RecyclerView recyclerView);
}