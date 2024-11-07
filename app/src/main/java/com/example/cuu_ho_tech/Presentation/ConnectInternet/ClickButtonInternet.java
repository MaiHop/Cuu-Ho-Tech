package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;

public class ClickButtonInternet implements View.OnClickListener {

    private final Context context;
    private final View.OnClickListener originalClickListener;
    private final FragmentManager fragmentManager;


    public ClickButtonInternet(Context context, View.OnClickListener originalClickListener, FragmentManager fragmentManager) {
        this.context = context;
        this.originalClickListener = originalClickListener;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View v) {
        if (isInternetAvailable(context)) {
            originalClickListener.onClick(v);
        } else {
            showNoConnectionDialog();
        }
    }

    private boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities networkCapabilities = connectivityManager.getActiveNetwork() != null ?
                    connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()) : null;
            return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        } else {
            android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

    private void showNoConnectionDialog() {
        CustomDialog dialog = new CustomDialog().setLoading(true)
                .setType(CustomDialog.DISCONNECT)
                .setTitle("Lỗi")
                .setText("Không có kết nối Internet?")
                .setTextBtn("ĐÓNG")
                .setTextBtnOutline("THỬ LẠI")
                .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT);
        dialog.show(fragmentManager, "CustomDialog");
    }
}
