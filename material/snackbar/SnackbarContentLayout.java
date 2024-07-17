package com.google.android.material.snackbar;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SnackbarContentLayout extends LinearLayout {
    public TextView messageView;

    public SnackbarContentLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(2131363633);
        Button button = (Button) findViewById(2131363632);
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean z = true;
        if (getOrientation() != 1) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131165753);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131165752);
            Layout layout = this.messageView.getLayout();
            if (layout == null || layout.getLineCount() <= 1) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (getOrientation() != 0) {
                setOrientation(0);
            } else {
                z = false;
            }
            if (this.messageView.getPaddingTop() != dimensionPixelSize || this.messageView.getPaddingBottom() != dimensionPixelSize) {
                TextView textView = this.messageView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api17Impl.isPaddingRelative(textView)) {
                    ViewCompat.Api17Impl.setPaddingRelative(textView, ViewCompat.Api17Impl.getPaddingStart(textView), dimensionPixelSize, ViewCompat.Api17Impl.getPaddingEnd(textView), dimensionPixelSize);
                } else {
                    textView.setPadding(textView.getPaddingLeft(), dimensionPixelSize, textView.getPaddingRight(), dimensionPixelSize);
                }
            } else if (!z) {
                return;
            }
            super.onMeasure(i, i2);
        }
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
