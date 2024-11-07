package com.example.cuu_ho_tech.Presentation.Adapter;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Utils.OnDialogViewReadyListener;

public abstract class DialogViewReadyListenerAdapter implements OnDialogViewReadyListener {
    @Override
    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
        // Default empty implementation
    }

    @Override
    public void onViewReadyOneBtn(AppCompatTextView btnOutline) {
        // Default empty implementation
    }

    @Override
    public void onReadyListView(RecyclerView recyclerView) {
        // Default empty implementation
    }
}
