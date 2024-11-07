package com.example.cuu_ho_tech.Custom;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomConstraintLayout extends ConstraintLayout {

    public CustomConstraintLayout(Context context) {
        super(context);
    }

    public CustomConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
