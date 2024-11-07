package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.cuu_ho_tech.R;

public class InternetUI {
    private static Handler handler;
    public static void NetworkDisconnected(Context context, TextView connect) {
        if(connect.getVisibility() != View.VISIBLE) {
            Log.d("onNetworkDisconnected", "onNetworkDisconnected: connect");
            connect.setText("Không có kết nối mạng");
            connect.setBackgroundColor(ContextCompat.getColor(context, R.color.danger_main));
            connect.setVisibility(View.VISIBLE);
            handler = new Handler();
            Runnable runnable = new Runnable() {
                private int dotCount = 0;
                @Override
                public void run() {
                    String text = connect.getText().toString();
                    if (dotCount < 3) {
                        connect.setText(text + ".");
                        dotCount++;
                    } else {
                        connect.setText(text.substring(0, text.length() - 3));
                        dotCount = 0;
                    }
                    handler.postDelayed(this, 500);
                }
            };
            handler.post(runnable);
        }
    }

    public static void NetworkConnected(Context context, TextView connect) {
        if(connect.getVisibility() != View.GONE) {
            handler.removeCallbacksAndMessages(null);
            connect.setText("Đang kết nối mạng");
            connect.setBackgroundColor(ContextCompat.getColor(context, R.color.warning_main));
            handler = new Handler();
            Runnable runnable = new Runnable() {
                private int dotCount = 0;
                @Override
                public void run() {
                    String text = connect.getText().toString();
                    if (dotCount < 3) {
                        connect.setText(text + ".");
                        dotCount++;
                    } else {
                        connect.setText(text.substring(0, text.length() - 3));
                        dotCount = 0;
                    }
                    handler.postDelayed(this, 500);
                }
            };
            handler.post(runnable);
            Handler handlerSuccess = new Handler();
            runnable = new Runnable() {
                private boolean isSuccess = false;
                @Override
                public void run() {
                    if(isSuccess) {
                        connect.setVisibility(View.GONE);
                        return;
                    } else isSuccess = true;
                    handler.removeCallbacksAndMessages(null);
                    connect.setText("Đã kết nối mạng");
                    connect.setBackgroundColor(ContextCompat.getColor(context, R.color.success_main));
                    handlerSuccess.postDelayed(this, 3000);
                }
            };
            handlerSuccess.postDelayed(runnable, 5000);
        }
    }
}
