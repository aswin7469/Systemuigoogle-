package com.google.android.setupdesign.util;

import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HeaderAreaStyler$1 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ImageView val$imageView;

    public HeaderAreaStyler$1(ImageView imageView) {
        this.val$imageView = imageView;
    }

    public final boolean onPreDraw() {
        this.val$imageView.getViewTreeObserver().removeOnPreDrawListener(this);
        if (this.val$imageView.getDrawable() == null || (this.val$imageView.getDrawable() instanceof VectorDrawable) || (this.val$imageView.getDrawable() instanceof VectorDrawableCompat)) {
            return true;
        }
        String str = Build.TYPE;
        if (!str.equals("userdebug") && !str.equals("eng")) {
            return true;
        }
        Log.w("HeaderAreaStyler", "To achieve scaling icon in SetupDesign lib, should use vector drawable icon from " + this.val$imageView.getContext().getPackageName());
        return true;
    }
}
