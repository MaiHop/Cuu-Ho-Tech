package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.cuu_ho_tech.Presentation.Adapter.DialogViewReadyListenerAdapter;
import com.example.cuu_ho_tech.Presentation.Dialog.CustomDialog;
import com.example.cuu_ho_tech.Utils.OnConnectListener;

public class BaseActivity extends AppCompatActivity implements OnConnectListener {
    private NetworkReceiver networkReceiver;
    private static OnConnectListener listener;

    public static void setOnConnectListener(OnConnectListener listener) {
        BaseActivity.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkReceiver.setOnNetworkChangeListener(new NetworkReceiver.OnNetworkChangeListener() {
            @Override
            public void onNetworkChange(boolean isConnected) {
                if (!isConnected) {
                    showNoConnectionDialog();
                    listener.onNetworkDisconnected();
                } else {
                    listener.onNetworkConnected();
                }
            }
        });
        registerNetworkReceiver();
    }

    private void registerNetworkReceiver() {
        networkReceiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private void showNoConnectionDialog() {
        CustomDialog dialog = new CustomDialog()
                .setType(CustomDialog.DISCONNECT)
                .setTitle("Lỗi")
                .setText("Không có kết nối Internet?")
                .setTextBtn("ĐÓNG")
                .setTextBtnOutline("THỬ LẠI")
                .setTypeLayoutBtn(CustomDialog.LEFT_RIGHT)
                .setOnDialogViewReadyListener(new DialogViewReadyListenerAdapter() {
                    @Override
                    public void onViewReadyTwoBtn(AppCompatTextView btn, AppCompatTextView btnOutline) {
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        btnOutline.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                });
        dialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    @Override
    public void onNetworkDisconnected() {

    }

    @Override
    public void onNetworkConnected() {

    }
}