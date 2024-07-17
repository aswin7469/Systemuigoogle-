package com.google.android.material.internal;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.core.view.WindowInsetsControllerCompat$Impl30;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class EdgeToEdgeUtils {
    public static void setLightStatusBar(Window window, boolean z) {
        window.getDecorView();
        WindowInsetsControllerCompat$Impl30 windowInsetsControllerCompat$Impl30 = new WindowInsetsControllerCompat$Impl30(window);
        Window window2 = windowInsetsControllerCompat$Impl30.mWindow;
        WindowInsetsController windowInsetsController = windowInsetsControllerCompat$Impl30.mInsetsController;
        if (z) {
            if (window2 != null) {
                View decorView = window2.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            windowInsetsController.setSystemBarsAppearance(8, 8);
            return;
        }
        if (window2 != null) {
            View decorView2 = window2.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & -8193);
        }
        windowInsetsController.setSystemBarsAppearance(0, 8);
    }
}
