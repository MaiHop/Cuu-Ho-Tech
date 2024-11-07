package com.example.cuu_ho_tech.Presentation.ConnectInternet;

import androidx.lifecycle.LifecycleObserver;

public class AppLifecycleObserver implements LifecycleObserver {

    private final Runnable onAppForeground;
    private final Runnable onAppBackground;

    public AppLifecycleObserver(Runnable onAppForeground, Runnable onAppBackground) {
        this.onAppForeground = onAppForeground;
        this.onAppBackground = onAppBackground;
    }

    public void onForeground() {
        onAppForeground.run();
    }

    public void onBackground() {
        onAppBackground.run();
    }
}
