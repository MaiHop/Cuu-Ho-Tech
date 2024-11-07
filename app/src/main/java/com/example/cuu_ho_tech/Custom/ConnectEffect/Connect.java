package com.example.cuu_ho_tech.Custom.ConnectEffect;

import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class Connect {
    public static void animateWidth(View view, TextView textNote) {
        final int initialWidth = view.getLayoutParams().width;
        final int targetWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, view.getResources().getDisplayMetrics());
        ValueAnimator animator = ValueAnimator.ofInt(initialWidth, targetWidth);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        textNote.setVisibility(View.GONE);
        view.setPadding(0,0,0,0);
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = animatedValue;
            view.setLayoutParams(layoutParams);
        });
        animator.start();
    }
}
