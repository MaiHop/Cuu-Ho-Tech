package com.example.cuu_ho_tech.Utils;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;

import com.example.cuu_ho_tech.R;

public class DeviceUtils {
    // Phương thức rung điện thoại
    public static void vibratePhone(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) { // Kiểm tra nếu thiết bị hỗ trợ rung
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // API 26 trở lên
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // API thấp hơn 26
                vibrator.vibrate(500); // Rung trong 500 ms
            }
        }
    }

    public int dpToPx(float dp, Activity activity) {
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        return Math.round(dp * metrics.density);
    }

    public float pxToDp(float px, Activity activity) {
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        return px / metrics.density;
    }

    // Phương thức lắc màn hình
    public static void shakeScreen(View view) {
        // Tạo Animation lắc
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(100); // Thời gian cho mỗi lần lắc
        shake.setRepeatCount(5); // Số lần lặp
        shake.setRepeatMode(Animation.REVERSE); // Lặp lại ngược

        // Áp dụng animation vào View
        view.startAnimation(shake);
    }

    public static void playNotificationSound(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.media_notification);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release); // Giải phóng tài nguyên sau khi phát xong
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(View view, Context context) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
