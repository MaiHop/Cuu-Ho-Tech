package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerNetworkReceiver();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, isDestroyed() + "/" + isFinishing(), Toast.LENGTH_SHORT).show();
        initialize();
    }

    private void initialize() {
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
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }

    public void registerNetworkReceiver() {
        networkReceiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private CustomDialog dialog;
    private void showNoConnectionDialog() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (dialog != null && !dialog.isAdded()) {
            dialog.show(getSupportFragmentManager(), "CustomDialog");
        } else if (dialog == null) {
            dialog = new CustomDialog()
                    .setType(CustomDialog.DISCONNECT)
                    .setTitle("Lỗi kết nối")
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
                                    dialog.dismiss();
                                }
                            });
                            btnOutline.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isNetworkConnected()) {
                                        dialog.dismiss();
                                    } else {
                                        dialog.dismiss();
                                        registerNetworkReceiver();
                                    }
                                }
                            });
                        }
                    });
            dialog.show(getSupportFragmentManager(), "CustomDialog");
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }
        return false;
    }

    @Override
    public void onNetworkDisconnected() {

    }

    @Override
    public void onNetworkConnected() {

    }
}