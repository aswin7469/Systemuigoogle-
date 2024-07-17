package com.google.android.setupdesign.util;

import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
