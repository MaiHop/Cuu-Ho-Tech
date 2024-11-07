package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;


public class NetworkReceiver extends BroadcastReceiver {

    public interface OnNetworkChangeListener {
        void onNetworkChange(boolean isConnected);
    }

    private static OnNetworkChangeListener onNetworkChangeListener;

    public static void setOnNetworkChangeListener(OnNetworkChangeListener listener) {
        onNetworkChangeListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = isNetworkAvailable(context);
        if (onNetworkChangeListener != null) {
            onNetworkChangeListener.onNetworkChange(isConnected);
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivityManager.getActiveNetwork() != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            }
            return false;
        } else {
            android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}

